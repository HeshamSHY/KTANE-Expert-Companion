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

package me.heshamshy.ktane.expertcompanion.command;

import lombok.Getter;
import lombok.NonNull;
import me.heshamshy.ktane.expertcompanion.command.commands.*;
import org.jline.builtins.Completers.TreeCompleter;
import org.jline.builtins.Completers.TreeCompleter.Node;

import javax.annotation.Nullable;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Properties;

import static org.jline.builtins.Completers.TreeCompleter.node;

public class CommandManager {
    @Getter
    private final List<Command> commands = new ArrayList<>();

    @Getter
    private final TreeCompleter completer;

    public CommandManager() {
        final Properties properties = new Properties();
        try {
            properties.load(this.getClass().getClassLoader().getResourceAsStream("application.properties"));
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        final String programName = properties.getProperty("program.name");
        final String programVersion = properties.getProperty("program.version");
        final String programRepoLink = properties.getProperty("program.repository.link");

        addCommand(new HelpCommand(this));
        addCommand(new LicenseCommand(programName));
        addCommand(new VersionCommand(programName, programVersion));
        addCommand(new CreditsCommand());
        addCommand(new RepositoryCommand(programRepoLink));

        this.completer = generateCompleter(getCommands());
    }

    private TreeCompleter generateCompleter(List<Command> commands) {
        List<Node> nodes = new ArrayList<>();

        for (Command command : commands) {
            if (command.options().isEmpty()) {
                nodes.add(node(command.name()));
                for (String alias : command.aliases()) {
                    nodes.add(node(alias));
                }
                continue;
            }

            for (Node node : command.options()) {

                nodes.add(node(command.name(), node));

                for (String alias : command.aliases()) {
                    nodes.add(node(alias, node));
                }

            }
        }

        nodes.add(node("exit"));

        return new TreeCompleter(nodes);
    }

    private void addCommand(@NonNull Command command) {
        if (this.commands.stream().anyMatch(
                        cmd -> cmd.name().equalsIgnoreCase(command.name())
                )) {
            throw new IllegalArgumentException("A command with the name `" + command.name() +"` already exists");
        }

        commands.add(command);
    }

    @Nullable
    public Command getCommand(@NonNull String search) {
        final String searchLowerCase = search.toLowerCase();

        for (Command command : this.commands) {
            if (command.name().equals(searchLowerCase) || command.aliases().contains(searchLowerCase)) {
                return command;
            }
        }
        return null;
    }

    public void handle(String line) throws IllegalArgumentException {

        String[] lineSplit = line.split("\\s+");

        String invoke = lineSplit[0];
        Command command = this.getCommand(invoke);

        if (command == null) throw new IllegalArgumentException("Command not found");

        List<String> args = Arrays.asList(lineSplit).subList(1, lineSplit.length);
        command.handle(args);
    }
}
