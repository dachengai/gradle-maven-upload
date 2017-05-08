package com.dacheng.maven.upload.task

import org.gradle.api.DefaultTask

/**
 * Created by dacheng on 17-3-19.
 */

public abstract class AbstractConfigTask extends DefaultTask {

    public AbstractConfigTask() {
        doLast {
            applyConfig()
        }
    }

    public abstract void applyConfig()

}