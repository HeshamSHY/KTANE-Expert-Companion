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

package me.heshamshy.ktane.expertcompanion.solver;

import lombok.Getter;
import lombok.NonNull;
import me.heshamshy.ktane.expertcompanion.module.Module;

import javax.annotation.Nullable;
import java.util.ArrayList;
import java.util.List;

public class SolverManager {
    @Getter
    private final List<Solver> solvers = new ArrayList<>();

    public SolverManager() {
    }

    private void addSolver(@NonNull Solver solver) throws IllegalArgumentException {
        if (solvers.stream().anyMatch(
                solv -> solv.module().equals(solver.module())
        )) {
            throw new IllegalArgumentException("A solver with the module `" + solver.module().getSimpleName() + "` already exists");
        }

        solvers.add(solver);
    }

    @Nullable
    public Solver getSolver(@NonNull Class<? extends Module> moduleClass) {
        for (Solver solver : solvers) {
            if (solver.module().getName().equals(moduleClass.getName())) {
                return solver;
            }
        }


        return null;
    }

    public void handle(@NonNull Module module) {
        final Class<? extends Module> moduleClass = module.getClass();
        final Solver solver = getSolver(moduleClass);

        if (solver == null) throw new IllegalArgumentException("No solver found for module `" + moduleClass.getName() + "`");

        solver.solve(module);
    }
}
