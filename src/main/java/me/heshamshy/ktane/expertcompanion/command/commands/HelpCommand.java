/*
 *     KTANE Expert Companion - An app that assists Keep Talking and Nobody Explodes experts on their mission of directing the defuser to defuse the bomb
 *     Copyright (C) 2023  HeshamSHY
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
import me.heshamshy.ktane.expertcompanion.cli.TerminalManager;
import me.heshamshy.ktane.expertcompanion.command.Command;
import me.heshamshy.ktane.expertcompanion.command.CommandManager;
import org.jline.builtins.Completers.TreeCompleter.Node;

import javax.annotation.Nonnull;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

import static org.jline.builtins.Completers.TreeCompleter.node;

public class HelpCommand implements Command {

    private final CommandManager commandManager;

    public HelpCommand(CommandManager commandManager) {
        this.commandManager = commandManager;
    }

    @Override
    @Nonnull
    public String name() {
        return "help";
    }

    @Override
    @Nonnull
    public String description() {
        return "Provides information about (a) command(s).";
    }

    @Override
    @Nonnull
    public String usage() {
        return name() + " [command]...";
    }

    @Override
    @Nonnull
    public List<Node> options() {
        List<Node> list = new ArrayList<>();

        for (LinkedList<String> combination : generateEveryPossibleCommandCombination(commandManager.getCommands(), new LinkedList<>())) {
            list.add(generateNodeFromLinkedList(combination, 0));
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
        if (args.size() == 0) {
            final List<Command> commands = commandManager.getCommands();

            for (Command command : commands) {
                if (!stringBuilder.isEmpty()) stringBuilder.append("\n");
                stringBuilder.append(printableCommandInfo(command));
            }

            TerminalManager.outputTextLn(stringBuilder.toString());
            return;
        }

        // In case the command had args, this runs and takes every arg considering it's a command,
        // then prints the command with its aliases, description and usage.
        for (String arg : args) {
            final Command command = commandManager.getCommand(arg);
            if (command == null) {
                if (!stringBuilder.isEmpty()) stringBuilder.append("\n\n");
                stringBuilder.append("  Command `").append(arg).append("` not found");

                continue;
            }

            if (!stringBuilder.isEmpty()) stringBuilder.append("\n\n");
            stringBuilder.append(printableCommandInfo(command));
            stringBuilder.append("\n").append("    Usage: `").append(command.usage()).append("`");
        }

        TerminalManager.outputTextLn(stringBuilder.toString());
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

    private LinkedList<LinkedList<String>> generateEveryPossibleCommandCombination(@NonNull List<Command> commands, LinkedList<String> currentCombination) {
        LinkedList<LinkedList<String>> list = new LinkedList<>();
        if (commands.size() == 1) {
            currentCombination.add(commands.get(0).name());

            list.add(currentCombination);
            return list;
        }

        for (Command command : commands) {
            List<Command> commandsToPass = new LinkedList<>(commands);
            LinkedList<String> combinationToPass = new LinkedList<>(currentCombination);

            commandsToPass.remove(command);
            combinationToPass.add(command.name());

            list.addAll(generateEveryPossibleCommandCombination(commandsToPass, combinationToPass));
        }

        return list;
    }

    private Node generateNodeFromLinkedList(LinkedList<String> list, int currentIndex) {
        if (list.size()-1 == currentIndex) {
            return node(list.get(currentIndex));
        }

        return node(list.get(currentIndex), generateNodeFromLinkedList(list, currentIndex+1));
    }
}
