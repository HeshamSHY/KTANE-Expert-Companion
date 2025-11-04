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

import lombok.NonNull;
import lombok.RequiredArgsConstructor;
import me.heshamshy.ktane.expertcompanion.cli.TerminalManager;
import me.heshamshy.ktane.expertcompanion.command.Command;
import me.heshamshy.ktane.expertcompanion.command.CommandManager;
import org.jline.builtins.Completers.TreeCompleter.Node;

import javax.annotation.Nonnull;
import java.util.ArrayList;
import java.util.List;

import static org.jline.builtins.Completers.TreeCompleter.node;

@RequiredArgsConstructor
public class HelpCommand implements Command {

    private final TerminalManager terminalManager;
    private final CommandManager commandManager;

    @Override
    @Nonnull
    public String name() {
        return "help";
    }

    @Override
    @Nonnull
    public String description() {
        return "Provides information about available commands";
    }

    @Override
    @Nonnull
    public String usage() {
        return name() + " [command]";
    }

    @Override
    @Nonnull
    public List<Node> options() {
        List<Node> list = new ArrayList<>();

        for (Command command: commandManager.getCommands()) {
            list.add(
                    node(command.name())
            );
        }

        return list;
    }

    @Override
    @Nonnull
    public List<String> aliases() {
        return List.of("list", "h");
    }

    @Override
    public void handle(List<String> args) {
        StringBuilder stringBuilder = new StringBuilder();

        // In case the command had no args, this runs and prints a list of available commands in the app
        // with the aliases and the description of the command for each.
        if (args.isEmpty()) {
            final List<Command> commands = commandManager.getCommands();

            for (Command command : commands) {
                if (!stringBuilder.isEmpty()) stringBuilder.append("\n");
                stringBuilder.append(printableCommandInfo(command));
            }

            terminalManager.outputTextLn(stringBuilder.toString());
            return;
        }

        // In case the command had args, this runs and takes the first arg considering it's a command,
        // then prints the command with its aliases, description and usage.
        final Command command = commandManager.getCommand(args.get(0));

        if (command == null) {
            stringBuilder.append("  Command `").append(args.get(0)).append("` not found");

            terminalManager.outputTextLn(stringBuilder.toString());
            return;
        }

        stringBuilder.append(printableCommandInfo(command));
        stringBuilder.append("\n").append("    Usage: `").append(command.usage()).append("`");

        terminalManager.outputTextLn(stringBuilder.toString());
    }

    private String printableCommandInfo(@NonNull Command command) {
        final StringBuilder stringBuilder = new StringBuilder();

        stringBuilder.append("  - ").append(command.name());

        for (String alias : command.aliases()) {
            stringBuilder.append(", ").append(alias);
        }

        stringBuilder.append(" --- ").append(command.description());

        return stringBuilder.toString();
    }
}
