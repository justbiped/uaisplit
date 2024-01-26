package biped.works.plugins.github

import org.gradle.api.tasks.Exec
import org.gradle.api.tasks.TaskAction

class UpdateVersionsFileTask : Exec() {

    @TaskAction
    override fun exec() {
        commandLine("sh", "$rootDir/bla.sh", version.toString())
        super.exec()
    }

}