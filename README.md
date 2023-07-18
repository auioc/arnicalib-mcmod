<h1 align="center">ArnicaLib</h1>

<div align="center">

Shared library for AH's Minecraft mods.

[![GitHub license](https://img.shields.io/github/license/auioc/arnicalib-mcmod?style=flat-square)](/LICENSE)
[![GitHub release](https://img.shields.io/github/v/release/auioc/arnicalib-mcmod?style=flat-square)](https://github.com/auioc/arnicalib-mcmod/releases/latest)
[![GitHub Workflow Status](https://img.shields.io/github/actions/workflow/status/auioc/arnicalib-mcmod/dev-build.yml?branch=1.20-forge&label=dev%20build&style=flat-square)](https://github.com/auioc/arnicalib-mcmod/actions/workflows/dev-build.yml)
<br/>
![Minecraft](https://img.shields.io/static/v1?label=Minecraft&message=1.20.1&color=00aa00&style=flat-square)
[![Forge](https://img.shields.io/static/v1?label=Forge&message=47.1.0&color=e04e14&logo=Conda-Forge&style=flat-square)](http://files.minecraftforge.net/net/minecraftforge/forge/index_1.20.1.html)
[![Parchment](https://img.shields.io/static/v1?label=Parchment&message=2023.07.16&color=00aa00&style=flat-square)](https://github.com/ParchmentMC/Parchment/tree/versions/1.20.x)
<br/>
![OpenJDK](https://img.shields.io/static/v1?label=OpenJDK&message=17&color=brightgreen&logo=java&style=flat-square)
[![Gradle](https://img.shields.io/static/v1?label=Gradle&message=8.1.1&color=brightgreen&logo=gradle&style=flat-square)](https://docs.gradle.org/8.1.1/release-notes.html)

</div>

## For Developers

There are two ways to use this mod in your workspace:

### Maven (Recommended)

Add the following to your `build.gradle`:

#### Repositories

Add any of the following maven repository to the `repositories` section:

- GitHub Packages

    ```groovy
    maven {
        url 'https://maven.pkg.github.com/auioc/arnicalib-mcmod'
        credentials {
            username = "<GITHUB_USERNAME>"
            password = "<GITHUB_TOKEN>"
        }
    }
    ```

    **Notice:** You must provide a valid GitHub username and token to access the GitHub Packages. See [official documentation](https://docs.github.com/en/packages/working-with-a-github-packages-registry/working-with-the-gradle-registry#using-a-published-package) for more information.

- AUIOC Maven Repository

    ```groovy
    maven {
        url 'https://repo.auioc.com/maven/releases'
        content { includeGroup 'org.auioc.mcmod' }
    }
    ```

#### Dependencies

Add the following to the `dependencies` section:

```groovy
implementation "org.auioc.mcmod:arnicalib:<MINECRAFT_VERSION>-<ARNICALIB_VERSION>:deobf"
```

### Local JAR File

If you do not want to or can not use the maven repository:

1. Download the deobf jar of the version you want from [Releases](https://github.com/auioc/arnicalib-mcmod/releases), then put them into `libs` folder.

2. Add the following to the `dependencies` section in your `build.gradle`:

    ```groovy
    implementation files("libs/arnicalib-<VERSION>-deobf.jar")
    ```

## Maintainers

- [@WakelessSloth56](https://github.com/WakelessSloth56)
- [@LainIO24](https://github.com/lainio24)

## Credits

- [AUIOC](https://www.auioc.com)
- [CamHex](https://forums.minecraftforge.net/profile/187809-camhex/)
- [HaruhiFanClub](https://github.com/HaruhiFanClub)
- [Libellule505](https://github.com/Libellule505)
- [bgxd9592](https://github.com/bgxd9592)

## Contributing

Any type of contribution is welcome, here are some examples of how you may contribute to this project:

- Submit [issues](https://github.com/auioc/arnicalib-mcmod/issues) to report bugs or ask questions.
- Propose [pull requests](https://github.com/auioc/arnicalib-mcmod/pulls) to improve our code.

## License

ArnicaLib is licensed under the **GNU General Public License v3.0**.
The full license is in the [LICENSE](/LICENSE) file.
