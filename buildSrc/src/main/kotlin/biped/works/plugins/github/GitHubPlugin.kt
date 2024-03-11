package biped.works.plugins.github

import org.gradle.api.Plugin
import org.gradle.api.Project

class GitHubPlugin : Plugin<Project> {
    override fun apply(target: Project) {
        target.tasks.register("gitCommit", CommitTask::class.java)
    }
}