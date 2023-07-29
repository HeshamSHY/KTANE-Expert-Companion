# KTANE Expert Companion
An app that assists Keep Talking and Nobody Explodes experts on their mission of directing the defuser to defuse the bomb.

### This is a CLI app, no GUI version is available at the moment

#### Note: This is app is not meant to be used for cheating, make sure to discuss using this app with the group you are playing with.

## How to install

### Prerequisites

1. A Java Runtime Environment(JRE) version 17 or later. You can get one by installing any JDK/JRE 17 or later, refer to [this](https://whichjdk.com/) if you don't know what JDK/JRE to get.

### Download the app

You can download the `KTANE-expert-companion-{version}.zip` file from the [releases page](https://github.com/HeshamSHY/KTANE-Expert-Companion/releases) on the [GitHub repository](https://github.com/HeshamSHY/KTANE-Expert-Companion/); choosing whatever version you would like to install.

Or you can clone the repository to your device and build it your self.

### Extract the `zip` file

After downloading the zip file of the version you are trying to install, extract (unzip) the components of the zip file to whatever directory you like.

You can do this through whatever tool you like. Depending on the operating system you use, you can find documentation about how to extract/unzip `.zip` files online.

### Run the program

After extracting the file, you will find a jar file, you can run the program by running a terminal in the current directory. Then type in this command:
```shell
java -jar {jar-file-name}.jar
```

### (Optional) Finish the installation

#### Linux (Or Unix-like systems)

In most Linux distributions, you can create a bash file that runs the app and place it in one of the bin folders; so you can be able to run it from anywhere. To do this, You can run the following commands:
```bash
echo "#!/bin/bash" | sudo tee /usr/local/bin/ktane-expert-companion
echo "java -jar /path/to/file.jar" | sudo tee -a /usr/local/bin/ktane-expert-companion
sudo chmod +x /usr/local/bin/ktane-expert-companion
```
##### Note: using `java` might not always point to what we need, so if you encounter issues, try replacing `java` with the path to the java executable file we need.

Now, by typing `ktane-expert-companion` in a terminal window in any part of the file system it will run the app.

You can also register the app for your desktop environment by running the following commands:
##### Note: this expects that you ran the previous commands and the file `/usr/local/bin/ktane-expert-companion` was created. If not, make sure to replace the `Exec=ktane-expert-companion` part of the following command with `Exec=java -jar /path/to/file.jar`.
```bash
echo $'[Desktop Entry]\nName=KTANE Expert Companion\nExec=ktane-expert-companion\nTerminal=true\nType=Application' | tee ~/.local/share/applications/ktane-expert-companion.desktop
chmod +x ~/.local/share/applications/ktane-expert-companion.desktop
```

#### Windows

In Windows, you can create a batch/cmd file containing the following:
```shell
java -jar path/to/file.jar
```
##### Note: using `java` might not always point to what we need, so if you encounter issues, try replacing `java` with the path to the java executable file we need.

After that, you can [add that file to path](https://www.architectryan.com/2018/03/17/add-to-the-path-on-windows-10/), or [make shortcuts](https://www.computerhope.com/issues/ch000739.htm) to it on desktop or in [start menu](https://www.windowscentral.com/add-app-shortcuts-start-menu-manually-windows-10).

## Contributions

You are very welcomed and I encourage you to report issues and bugs, fork the repository, make pull requests, etc... I appreciate all your contributions to this project.

Make sure to check the license of this program.

## Credits

- Thanks to [All the Authors and Contributors](https://github.com/HeshamSHY/KTANE-Expert-Companion/graphs/contributors).
- Thanks to [The Project Lombok Authors and Contributors](https://github.com/projectlombok/lombok/graphs/contributors) for [Lombok](https://projectlombok.org/).
- Thanks to [FindBugs Development Team](https://findbugs.sourceforge.net/team.html) for JSR305.
- Thanks to [JLine 3 Authors and Contributors](https://github.com/jline/jline3/graphs/contributors) for [JLine 3](https://github.com/jline/jline3).

## License

KTANE Expert Companion is under [GNU General Public License v3.0](https://www.gnu.org/licenses/gpl-3.0.en.html); you can redistribute it and/or modify
it under the terms of the GNU General Public License as published by
the Free Software Foundation, either version 3 of the License, or
(at your option) any later version.

KTANE Expert Companion is distributed in the hope that it will be useful,
but WITHOUT ANY WARRANTY; without even the implied warranty of
MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
GNU General Public License for more details.