<h1 align="center">ArnicaLib</h1>

<div align="center">

Shared library for AH's Minecraft mods.

[![GitHub license](https://img.shields.io/github/license/WakelessSloth56/arnicalib-mcmod?style=flat-square)](/LICENSE)
[![GitHub release (latest SemVer)](https://img.shields.io/github/v/release/WakelessSloth56/arnicalib-mcmod?style=flat-square)](https://github.com/WakelessSloth56/arnicalib-mcmod/releases/latest)
[![GitHub Workflow Status](https://img.shields.io/github/workflow/status/WakelessSloth56/arnicalib-mcmod/auto-release?label=release%20build&style=flat-square)](https://github.com/WakelessSloth56/arnicalib-mcmod/actions/workflows/auto-release.yml)
[![GitHub Workflow Status](https://img.shields.io/github/workflow/status/WakelessSloth56/arnicalib-mcmod/dev-build?label=dev%20build&style=flat-square)](https://github.com/WakelessSloth56/arnicalib-mcmod/actions/workflows/dev-build.yml)
<br/>
![Minecraft](https://img.shields.io/static/v1?label=Minecraft&message=1.18.1&color=00aa00&style=flat-square)
[![Forge](https://img.shields.io/static/v1?label=Forge&message=39.0.19&color=e04e14&logo=Conda-Forge&style=flat-square)](http://files.minecraftforge.net/net/minecraftforge/forge/index_1.18.1.html)
[![AdoptiumOpenJDK](https://img.shields.io/static/v1?label=AdoptiumOpenJDK&message=17.0.1%2B12&color=brightgreen&logo=java&style=flat-square)](https://adoptium.net/?variant=openjdk17&jvmVariant=hotspot)
[![Gradle](https://img.shields.io/static/v1?label=Gradle&message=7.3&color=brightgreen&logo=gradle&style=flat-square)](https://docs.gradle.org/7.3/release-notes.html)

</div>

## For Developers

There are two ways to use this mod in your workspace:

### GitHub Package (Recommended)

Add the following to your `build.gradle`:

```groovy
repositories {
    maven {
        url "https://maven.pkg.github.com/wakelesssloth56/arnicalib-mcmod"
        credentials {
            username = "<GITHUB_USERNAME>"
            password = "<GITHUB_TOKEN>"
        }
    }
}

dependencies {
    implementation "org.auioc.mods.arnicalib:arnicalib-<MINECRAFT_VERSION>:<ARNICALIB_VERSION>:forgelib"
}
```

#### Notices

1. Mod version can be found in the [Packages](https://github.com/WakelessSloth56/arnicalib-mcmod/packages/) of this repository.
2. You must provide a valid GitHub username and token to access the GitHub Packages.
    - See [official documentation](https://docs.github.com/en/packages/working-with-a-github-packages-registry/working-with-the-gradle-registry#using-a-published-package) for more information.

### Local JAR File

If you do not want to or can not use the GitHub Packages:

1. Download the forgelib jar of the version you want from [Releases](https://github.com/WakelessSloth56/arnicalib-mcmod/releases), then put them into `libs` folder.

2. Add the following to your `build.gradle`:

    ```groovy
    dependencies {
        implementation files("libs/arnicalib-<VERSION>-forgelib.jar")
    }
    ```

## Documentation

- `zh-CN`: <https://wiki.auioc.com/view/Minecraft:Mod/ArnicaLib>

## Maintainers

- [@WakelessSloth56](https://github.com/WakelessSloth56)

## Credits

- [AUIOC](https://www.auioc.com)
- [bgxd9592](https://github.com/bgxd9592)
- [HaruhiFanClub](https://github.com/HaruhiFanClub)

## Contributing

Any type of contribution is welcome, here are some examples of how you may contribute to this project:

- Submit [issues](https://github.com/WakelessSloth56/arnicalib-mcmod/issues) to report bugs or ask questions.
- Propose [pull requests](https://github.com/WakelessSloth56/arnicalib-mcmod/pulls) to improve our code.

## License

ArnicaLib is licensed under the **GNU General Public License v3.0**.
The full license is in the [LICENSE](/LICENSE) file.
