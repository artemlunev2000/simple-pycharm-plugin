# simple-pycharm-plugin

Description:
Plugin that allows to create int annotations in pycharm.

Click on variable with left mouse button then right click and choose "add int annotation" and variable annotation become int.

If you have already installed pycharm, then change version in build.gradle to yours, so that it does not install again.

## installation and running with IDEA (recommended)

install [intellij idea](https://www.jetbrains.com/idea/)

clone repo:

```
git clone https://github.com/artemlunev2000/simple-pycharm-plugin.git
```

Open Idea then File -> Open -> choose downloaded project -> click import changes ( to import gradle project ).

Then in gradle tool window open Tasks -> intellij -> runIde ( to run pycharm ide instance with this plugin ).

Or just click on the play icon to the right of the build icon.

## installation and running with gradle

Install [gradle](https://gradle.org/install/).

clone repo:

```
git clone https://github.com/artemlunev2000/simple-pycharm-plugin.git
```

build project:

```
gradle build
```

run pycharm ide instance with this plugin:
```
gradle runIde
```

