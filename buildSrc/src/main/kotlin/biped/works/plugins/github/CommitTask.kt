package biped.works.github

import org.gradle.api.DefaultTask
import org.gradle.api.Task
import org.gradle.api.tasks.Exec
import org.gradle.api.tasks.TaskAction

class CommitTask : Exec() {

    @TaskAction
    override fun exec() {
        commandLine("echo", "hahahaha")
        super.exec()
    }
}