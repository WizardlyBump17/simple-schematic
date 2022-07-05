# simple-schematic
## A schematic system meant to be simple to use
<br>

## 1. Add the repository
### Gradle
```groovy
repositories {
    maven {
        url = uri('https://maven.pkg.github.com/WizardlyBump17/simple-schematic')
        credentials {
            username = project.findProperty('gpr.user') ?: System.getenv('USERNAME')
            password = project.findProperty('gpr.key') ?: System.getenv('TOKEN')
        }
    }
}
```
### Maven
```xml
<repositories>
    <repository>
        <id>github</id>
        <url>https://maven.pkg.github.com/WizardlyBump17/simple-schematic</url>
        <snapshots>
            <enabled>true</enabled>
        </snapshots>
    </repository>
</repositories>

<servers>
    <server>
        <id>github</id>
        <username>USERNAME</username>
        <password>TOKEN</password>
    </server>
</servers>
```
<br>

## 2. Add the dependency
### Gradle
```groovy
dependencies {
    compileOnly('com.wizardlybump17.simple-schematic:simple-schematic:1.0.0')
}
```
### Maven
```xml
<dependency>
    <groupId>com.wizardlybump17.simple-schematic</groupId>
    <artifactId>simple-schematic</artifactId>
    <version>1.0.0</version>
</dependency>
```