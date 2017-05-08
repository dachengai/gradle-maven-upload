package com.dacheng.maven.upload

import org.gradle.api.Plugin
import org.gradle.api.Project
import com.dacheng.maven.upload.task.MavenConfigTask

/**
 * Created by dacheng on 17-05-06.
 * maven upload pulgin
 */

class UploadPlugin implements Plugin<Project> {
    private static final String MAVEN_CONFIG_TASK_NAME = "uploadPlugin"

    @Override
    void apply(Project project) {
        configMaven(project)
    }

    private void configMaven(Project pProject) {
        if (!pProject.plugins.hasPlugin("maven")) {
            pProject.apply plugin: "maven"
        }

        pProject.extensions.create("uploadInfo", UploadExtension.class)

        pProject.uploadInfo {
            artifactId = pProject.name
            groupId = pProject.group
            version = pProject.version
        }

        pProject.project.getTasks().create(MAVEN_CONFIG_TASK_NAME, MavenConfigTask)

        pProject.tasks.findByName("uploadArchives").dependsOn MAVEN_CONFIG_TASK_NAME

        pProject.project.getTasks().create("uploadToMaven", UploadExecutor) {
            doLast {
                println "------------------------------upload complete------------------------------"
            }
        }.dependsOn "uploadArchives"
    }

}