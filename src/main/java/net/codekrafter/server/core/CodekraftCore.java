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

package net.codekrafter.server.core;

import net.codekrafter.server.core.commands.CommandServer;
import net.md_5.bungee.api.plugin.Plugin;

/**
 * @author codekrafter
 *
 */

/**
 * @author codekrafter
 *
 */
public class CodekraftCore extends Plugin
{

	public static CodekraftCore pl = null;

	@Override
	public void onEnable()
	{
		getProxy().getPluginManager().registerCommand(this,
				new CommandServer("agent"));
		getProxy().getPluginManager().registerCommand(this,
				new CommandServer("pvpkraft"));
		getProxy().getPluginManager().registerCommand(this,
				new CommandServer("hub", "You Already On The Hub!"));
		pl = this;
	}
}
