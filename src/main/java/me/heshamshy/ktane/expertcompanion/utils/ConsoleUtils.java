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

package me.heshamshy.ktane.expertcompanion.utils;

import lombok.NonNull;
import lombok.experimental.UtilityClass;
import me.heshamshy.ktane.expertcompanion.cli.TerminalManager;
import org.jline.utils.InfoCmp;

@UtilityClass
public class ConsoleUtils {

    public void clearConsole() {
        TerminalManager.perform(terminal -> {
            terminal.puts(InfoCmp.Capability.clear_screen);
            terminal.flush();
        });
    }

    public void printHeader(String programName, String authorName, int year, String programDescription , String programVersion) {

        printSeparator();

        printCentered(programName + " " + programVersion);
        printCentered(programDescription);

        printCentered("");
        printCentered("type 'help' for list of commands");
        printCentered("type 'exit' to exit the app");

        printCentered("\n");
        printCopyrightNotice(programName, year, authorName);

        printSeparator();

    }

    public void printCopyrightNotice(String programName, int year, String authorName) {
        printCentered(String.format("%s Copyright (C) %s  %s", programName, year, authorName));
        printCentered("This program comes with ABSOLUTELY NO WARRANTY; for details type `license -w'.");
        printCentered("This is free software, and you are welcome to redistribute it");
        printCentered("under certain conditions; type `license' for details.");
    }

    public void printCentered(@NonNull String text) {
        TerminalManager.perform(terminal -> {
            final int terminalWidth = terminal.getWidth();
            final int textHalfLength = text.length() / 2;

            final int prefixLength = terminalWidth / 2 - textHalfLength;
            String whitespacesToPrefix = " ".repeat(Math.max(prefixLength, 0));

            TerminalManager.outputTextLn(whitespacesToPrefix + text);
        });
    }

    public void printSeparator() {
        TerminalManager.perform(terminal -> {
            final int terminalWidth = terminal.getWidth();

            String separator = "-".repeat(Math.max(terminalWidth, 1));
            TerminalManager.outputTextLn(separator);
        });
    }
}
