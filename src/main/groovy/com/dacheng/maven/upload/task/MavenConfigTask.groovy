package com.dacheng.maven.upload.task

/**
 * Created by dacheng on 17-05-06.
 */

/**
 * Created by nelon on 17-3-19.
 */

public class MavenConfigTask extends AbstractConfigTask {

    def getReleaseRepositoryUrl() {
        return "http://localhost:8081/nexus/content/repositories/releases/"
    }

    def getSnapshotRepositoryUrl() {
        return  "http://localhost:8081/nexus/content/repositories/snapshots/"
    }

    def getRepositoryUsername() {
        return  "admin"
    }

    def getRepositoryPassword() {
        return "admin123"
    }

    @Override
    public void applyConfig() {
        project.tasks.findByName("uploadArchives").repositories {
            mavenDeployer {
                println "------------------------------ Upload Info ------------------------------"
                println "artifact :\t\t ${project.uploadInfo.artifactId}"
                println "group :\t\t ${project.uploadInfo.groupId}"
                println "version :\t\t ${project.uploadInfo.version}"
                println "ext :"
                Map<String, String> map = project.uploadInfo.extMap
                Set<String> keySet = map.keySet()
                keySet.each { key ->
                    println "\t${key} : ${map.get(key)}"
                }
                println "-------------------------------Upload Info -------------------------------"

                repository(url: getReleaseRepositoryUrl()) {
                    authentication(userName: getRepositoryUsername(), password: getRepositoryPassword())
                }
                snapshotRepository(url: getSnapshotRepositoryUrl()) {
                    authentication(userName: getRepositoryUsername(), password: getRepositoryPassword())
                }
                pom { p ->
                    p.version = project.uploadInfo.version
                    p.groupId = project.uploadInfo.groupId
                    p.artifactId = project.uploadInfo.artifactId
                    p.packaging = project.uploadInfo.packaging

                    keySet.each { key ->
                        p.withXml {
                            asNode().appendNode(key, map.get(key))
                        }
                    }

                }
            }
        }
    }
}