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

package me.heshamshy.ktane.expertcompanion;

import me.heshamshy.ktane.expertcompanion.cli.TerminalManager;
import me.heshamshy.ktane.expertcompanion.command.CommandManager;
import org.jline.reader.EndOfFileException;
import org.jline.reader.LineReader;
import org.jline.reader.LineReaderBuilder;
import org.jline.reader.UserInterruptException;
import org.jline.reader.impl.DefaultHighlighter;
import org.jline.reader.impl.DefaultParser;

import java.io.IOException;
import java.util.Properties;

public class Main {

    public static void main(String[] args) {
        final Properties properties = new Properties();
        try {
            properties.load(Main.class.getClassLoader().getResourceAsStream("application.properties"));
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        final String programName = properties.getProperty("program.name");
        final String programAuthor = properties.getProperty("program.author");
        final String programDescription = properties.getProperty("program.description");
        final String programVersion = properties.getProperty("program.version");

        TerminalManager terminalManager = new TerminalManager();

        terminalManager.clearConsole();
        terminalManager.printHeader(
                programName,
                programAuthor,
                2023,
                programDescription,
                programVersion

        );

        final CommandManager commandManager = new CommandManager(terminalManager, properties);

        LineReader lineReader = LineReaderBuilder.builder()
                .terminal(terminalManager.getTerminal())
                .appName("KTANE Expert Companion")
                .completer(commandManager.getCompleter())
                .highlighter(new DefaultHighlighter())
                .parser(new DefaultParser())
                .build();

        while (true) {
            String line;

            try {
                line = lineReader.readLine("KCE>> ");
            } catch (UserInterruptException e) {
                terminalManager.close();
                return;
            } catch (EndOfFileException e) {
                return;
            }

            if (line == null) continue;
            if (line.isBlank()) continue;

            if (line.strip().equalsIgnoreCase("exit")) {
                terminalManager.close();
                return;
            }

            try {
                commandManager.handle(line);
            } catch (IllegalArgumentException e) {
                terminalManager.outputTextLn(e.getMessage());
            }
        }
    }
}
