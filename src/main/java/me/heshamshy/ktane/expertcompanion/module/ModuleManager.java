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

package me.heshamshy.ktane.expertcompanion.module;

import lombok.Getter;
import lombok.NonNull;
import me.heshamshy.ktane.expertcompanion.Bomb;

import javax.annotation.Nullable;
import java.util.ArrayList;
import java.util.List;

public class ModuleManager {
    @Getter
    private final List<Module> modules = new ArrayList<>();

    public ModuleManager() {
    }

    private void addModule(@NonNull Module module) throws IllegalArgumentException {
        if (this.modules.stream().anyMatch(
                mod -> mod.name().equalsIgnoreCase(module.name())
        )) {
            throw new IllegalArgumentException("A module with the name `" + module.name() +"` already exists");
        }

        modules.add(module);
    }

    @Nullable
    public Module getModule(@NonNull String search) {
        final String searchLowerCase = search.toLowerCase();

        for (Module module : this.modules) {
            if (module.name().equals(searchLowerCase)) {
                return module;
            }
        }

        return null;
    }

    public void handle(@NonNull String moduleName, @NonNull Bomb bomb) {
        Module module = getModule(moduleName);
        if (module == null) throw new IllegalArgumentException("No module with the name `" + moduleName + "`");

        module.init(bomb);
        //TODO pass to the SolverManager
    }
}
