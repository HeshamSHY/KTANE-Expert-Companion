/*
 *     KTANE Expert Companion - An app that assists Keep Talking and Nobody Explodes experts on their mission of directing the defuser to defuse the bomb
 *     Copyright (C) 2023, 2025  Hesham H.
 *
 *     This file is part of KTANE Expert Companion.
 *
 *     KTANE Expert Companion is free software: you can redistribute it and/or modify
 *     it under the terms of the GNU General Public License as published by
 *     the Free Software Foundation, either version 3 of the License, or
 *     (at your option) any later version.
 *
 *     This program is distributed in the hope that it will be useful,
 *     but WITHOUT ANY WARRANTY; without even the implied warranty of
 *     MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 *     GNU General Public License for more details.
 *
 *     You should have received a copy of the GNU General Public License
 *     along with this program.  If not, see <https://www.gnu.org/licenses/>.
 */

package me.heshamshy.ktane.expertcompanion.command.commands;

import me.heshamshy.ktane.expertcompanion.cli.TerminalManager;
import me.heshamshy.ktane.expertcompanion.command.Command;
import org.jline.builtins.Completers.TreeCompleter.Node;

import javax.annotation.Nonnull;
import java.util.List;

public class CreditsCommand implements Command {
    private final TerminalManager terminalManager;

    public CreditsCommand(TerminalManager terminalManager) {
        this.terminalManager = terminalManager;
    }

    @Override
    @Nonnull
    public String name() {
        return "credits";
    }

    @Override
    @Nonnull
    public String description() {
        return "A command that shows the credits of this app";
    }

    @Override
    @Nonnull
    public String usage() {
        return name();
    }

    @Override
    @Nonnull
    public List<Node> options() {
        return List.of();
    }

    @Override
    @Nonnull
    public List<String> aliases() {
        return List.of();
    }

    @Override
    public void handle(List<String> args) {
        terminalManager.outputTextLn(credits);
    }

    private final String credits =
            """
             - Thanks to All the Authors and Contributors.
             - Thanks to The Project Lombok Authors and Contributors for Lombok.
             - Thanks to FindBugs Development Team for JSR305.
             - Thanks to JLine 3 Authors and Contributors for JLine 3.""";
}
