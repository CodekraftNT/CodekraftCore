/*
 * 	CodekraftCore
 * 	The Core Plugin For Codekraft
 *
 *     Copyright (C) 2015  codekrafter
 *
 *     This program is free software: you can redistribute it and/or modify
 *     it under the terms of the GNU General Lesser License as published by
 *     the Free Software Foundation, either version 3 of the License, or
 *     (at your option) any later version.
 *
 *     This program is distributed in the hope that it will be useful,
 *     but WITHOUT ANY WARRANTY; without even the implied warranty of
 *     MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 *     GNU General Lesser License for more details.
 *
 *     You should have received a copy of the GNU General Lesser License
 *     along with this program.  If not, see <http://www.gnu.org/licenses/>.
 */
/**
 * @author codekrafter
 *
 */

package net.codekrafter.server.core.commands;

import net.codekrafter.server.core.CodekraftCore;
import net.codekrafter.server.core.ServerFinder;
import net.md_5.bungee.api.Callback;
import net.md_5.bungee.api.ChatColor;
import net.md_5.bungee.api.CommandSender;
import net.md_5.bungee.api.chat.ComponentBuilder;
import net.md_5.bungee.api.config.ServerInfo;
import net.md_5.bungee.api.connection.ProxiedPlayer;
import net.md_5.bungee.api.plugin.Command;

/**
 * @author codekrafter
 *
 */
public class CommandServer extends Command
{

	private String name;
	private String alreadyMessage;
	private boolean inDev;

	/**
	 * @author codekrafter
	 *
	 * @param name
	 */
	public CommandServer(String name)
	{
		this(name, "You Are Already Playing "
				+ name.substring(0, 1).toUpperCase() + name.substring(1) + "!",
				false);
	}

	/**
	 * @author codekrafter
	 *
	 * @param name
	 */
	public CommandServer(String name, String alreadyMessage)
	{
		this(name, alreadyMessage, false);
	}

	public CommandServer(String name, String alreadyMessage, boolean inDev)
	{
		super(name);
		this.name = name;
		this.alreadyMessage = alreadyMessage;
		this.inDev = inDev;
	}

	@Override
	public void execute(CommandSender s, String[] args)
	{
		if (s instanceof ProxiedPlayer)
		{
			ServerInfo target = ServerFinder.getServer(name);

			ProxiedPlayer p = (ProxiedPlayer) s;

			if (!p.getServer().getInfo().getName().startsWith(name))
			{
				if (!p.hasPermission("server.connect.indev") && inDev)
				{
					return;
				}

				p.connect(target, new Callback<Boolean>()
					{

						public void done(Boolean result, Throwable error)
						{
							if (!result)
							{
								CodekraftCore.pl
										.getLogger()
										.info("There Was A Error Proforming A CommandServer");
							}
						}

					});
			}
			else
			{
				p.sendMessage(new ComponentBuilder(alreadyMessage).color(
						ChatColor.RED).create());
			}
		}
		else
		{
			s.sendMessage(new ComponentBuilder(
					"This command can only be run by a player!").color(
					ChatColor.RED).create());
		}
	}

}
