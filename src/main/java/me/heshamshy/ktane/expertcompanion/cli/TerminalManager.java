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

package me.heshamshy.ktane.expertcompanion.cli;

import lombok.Getter;
import lombok.NonNull;
import lombok.experimental.UtilityClass;
import org.jline.terminal.Terminal;
import org.jline.terminal.TerminalBuilder;

import java.io.IOException;
import java.util.function.Consumer;

@UtilityClass
public class TerminalManager {

    @Getter
    private Terminal terminal;

    public void init() {
        try {
            terminal = TerminalBuilder.terminal();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    public void outputTextLn(@NonNull String text) {
        perform( terminal1 ->
                terminal1.writer().println(text)
        );
    }

    public void outputText(@NonNull String text) {
        perform( terminal1 ->
            terminal1.writer().print(text)
        );
    }

    public void close() {
        try {
            terminal.close();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    public void perform(@NonNull Consumer<Terminal> action) {
        action.accept(terminal);
    }
}
