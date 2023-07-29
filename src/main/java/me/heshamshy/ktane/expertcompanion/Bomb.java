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

package me.heshamshy.ktane.expertcompanion;

import lombok.Builder;
import lombok.Data;
import lombok.NonNull;
import me.heshamshy.ktane.expertcompanion.utils.StringUtils;

import java.util.ArrayList;
import java.util.List;

@Data
@Builder
public class Bomb {

    private String serialNum;
    private int batteriesCount;
    private final List<String> labels = new ArrayList<>();

    public void addLabels(String... label) {
        for (String s : label) {
            try {
                addLabel(s);
            } catch (NullPointerException e) {
                System.out.println("a label seems to be null");
            } catch (IllegalAccessException e) {
                System.out.println(e.getMessage());
            }
        }
    }

    private void addLabel(@NonNull String label) throws NullPointerException, IllegalAccessException {

        if (label.length() != 3 || !StringUtils.isAlpha(label)) throw new IllegalAccessException("Invalid label: " + label.toUpperCase());
        if (labels.contains(label.toUpperCase())) return;

        labels.add(label);
    }

}
