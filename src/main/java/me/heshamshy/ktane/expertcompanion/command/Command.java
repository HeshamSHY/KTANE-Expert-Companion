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

import org.jline.builtins.Completers.TreeCompleter.Node;

import javax.annotation.Nonnull;
import java.util.List;

public interface Command {

    @Nonnull
    String name();

    @Nonnull
    String description();

    @Nonnull
    String usage();

    @Nonnull
    List<Node> options();

    @Nonnull
    List<String> aliases();

    void handle(List<String> args);
}
