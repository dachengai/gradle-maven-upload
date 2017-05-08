# Gradle Android Maven Publish Plugin

#### USAGE

1、In your project `build.gradle` file

``` groovy

buildscript {
    repositories {
        jcenter()
        maven { url "your private maven url" }
    }
    dependencies {
        classpath 'com.android.tools.build:gradle:2.0.0'
        classpath 'com.dacheng.maven:upload:0.0.1'
    }
}

```

2、In your app/build.gradle

```
apply plugin: 'upload'


uploadInfo {
    groupId = "com.dacheng.test"
    artifactId = "test"
    version = "0.0.1"
    ext {
        extProp1 = "额外属性1"
    }

}
```

3、terminal run :

> gradle uploadToMaven



