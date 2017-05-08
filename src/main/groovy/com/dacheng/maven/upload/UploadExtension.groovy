package com.dacheng.maven.upload

import org.gradle.api.Action

/**
 * Created by dacheng on 17-05-06.
 */

class UploadExtension {
    String artifactId //工程 id
    String groupId //组 id
    String version  //版本号
    String packaging //类型
    Map<String, String> extMap  //额外参数

    UploadExtension() {
        artifactId = ""
        groupId = ""
        version = ""
        packaging = "aar"
        extMap = new HashMap<>()
    }

    void ext(Action<Map> pMapAction) {
        pMapAction.execute(extMap)
    }
}