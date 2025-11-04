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

import lombok.RequiredArgsConstructor;
import me.heshamshy.ktane.expertcompanion.cli.TerminalManager;
import me.heshamshy.ktane.expertcompanion.command.Command;
import org.jline.builtins.Completers.TreeCompleter.Node;

import javax.annotation.Nonnull;
import java.util.List;

import static org.jline.builtins.Completers.TreeCompleter.node;

@RequiredArgsConstructor
public class LicenseCommand implements Command {

    private final String PROGRAM_NAME;
    private final TerminalManager terminalManager;


    @Override
    @Nonnull
    public String name() {
        return "license";
    }

    @Override
    @Nonnull
    public String description() {
        return "Shows information about the license of this program.";
    }

    @Override
    @Nonnull
    public String usage() {
        return name() + " [-w, --warranty]";
    }

    @Override
    @Nonnull
    public List<Node> options() {
        return List.of(node("-w", "--warranty"));
    }

    @Override
    @Nonnull
    public List<String> aliases() {
        return List.of();
    }

    @Override
    public void handle(List<String> args) {
        if (!args.isEmpty()) {
            if (args.contains("-w") || args.contains("--warranty")) {
                terminalManager.outputTextLn(WARRANTY_SECTION);
                terminalManager.outputTextLn("");
                terminalManager.outputTextLn(LIABILITY_SECTION);

                return;
            }
        }

        terminalManager.outputTextLn("        " + PROGRAM_NAME + " " + LICENSE_NOTICE);
    }

    private final String LICENSE_NOTICE =
            """
            is free software: you can redistribute it and/or modify
                    it under the terms of the GNU General Public License as published by
                    the Free Software Foundation, either version 3 of the License, or
                    (at your option) any later version.
            
                    This program is distributed in the hope that it will be useful,
                    but WITHOUT ANY WARRANTY; without even the implied warranty of
                    MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
                    GNU General Public License for more details.
            
                    You should have received a copy of the GNU General Public License
                    along with this program.  If not, see <https://www.gnu.org/licenses/>.
            """;

    private final String WARRANTY_SECTION =
            """
                    Disclaimer of Warranty.
            
                      THERE IS NO WARRANTY FOR THE PROGRAM, TO THE EXTENT PERMITTED BY
                    APPLICABLE LAW.  EXCEPT WHEN OTHERWISE STATED IN WRITING THE COPYRIGHT
                    HOLDERS AND/OR OTHER PARTIES PROVIDE THE PROGRAM "AS IS" WITHOUT WARRANTY
                    OF ANY KIND, EITHER EXPRESSED OR IMPLIED, INCLUDING, BUT NOT LIMITED TO,
                    THE IMPLIED WARRANTIES OF MERCHANTABILITY AND FITNESS FOR A PARTICULAR
                    PURPOSE.  THE ENTIRE RISK AS TO THE QUALITY AND PERFORMANCE OF THE PROGRAM
                    IS WITH YOU.  SHOULD THE PROGRAM PROVE DEFECTIVE, YOU ASSUME THE COST OF
                    ALL NECESSARY SERVICING, REPAIR OR CORRECTION.
            """;

    private final String LIABILITY_SECTION =
            """
                    Limitation of Liability.
            
                      IN NO EVENT UNLESS REQUIRED BY APPLICABLE LAW OR AGREED TO IN WRITING
                    WILL ANY COPYRIGHT HOLDER, OR ANY OTHER PARTY WHO MODIFIES AND/OR CONVEYS
                    THE PROGRAM AS PERMITTED ABOVE, BE LIABLE TO YOU FOR DAMAGES, INCLUDING ANY
                    GENERAL, SPECIAL, INCIDENTAL OR CONSEQUENTIAL DAMAGES ARISING OUT OF THE
                    USE OR INABILITY TO USE THE PROGRAM (INCLUDING BUT NOT LIMITED TO LOSS OF
                    DATA OR DATA BEING RENDERED INACCURATE OR LOSSES SUSTAINED BY YOU OR THIRD
                    PARTIES OR A FAILURE OF THE PROGRAM TO OPERATE WITH ANY OTHER PROGRAMS),
                    EVEN IF SUCH HOLDER OR OTHER PARTY HAS BEEN ADVISED OF THE POSSIBILITY OF
                    SUCH DAMAGES.
            """;
}
