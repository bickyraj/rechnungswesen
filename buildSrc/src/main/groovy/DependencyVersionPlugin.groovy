import org.gradle.api.Plugin
import org.gradle.api.Project
import org.gradle.api.GradleException

class DependencyVersionPlugin implements Plugin<Project> {

    @Override
    void apply(Project project) {

        project.tasks.register('checkDependencyVersion') {

            group = 'help'
            description = 'Checks the latest stable version of a Maven dependency.'

            doLast {

                def dependency = project.providers
                        .gradleProperty('dependency')
                        .orNull

                if (!dependency) {
                    throw new GradleException(
                            'Please provide a dependency. Example: -Pdependency=com.zaxxer:HikariCP'
                    )
                }

                def parts = dependency.split(':')

                if (parts.length != 2) {
                    throw new GradleException(
                            'Invalid format. Use group:artifact, for example com.zaxxer:HikariCP'
                    )
                }

                def group = parts[0]
                def artifact = parts[1]

                def targetProjectPath = project.providers
                        .gradleProperty('targetProject')
                        .getOrElse(':app')

                def targetProject = project.project(targetProjectPath)

                def latestConfig = targetProject.configurations.detachedConfiguration(
                        targetProject.dependencies.create("${group}:${artifact}:latest.release")
                )

                latestConfig.resolutionStrategy
                        .cacheDynamicVersionsFor(0, 'seconds')

                def latest = latestConfig.resolvedConfiguration
                        .firstLevelModuleDependencies
                        .first()
                        .moduleVersion

                def current = targetProject.configurations.runtimeClasspath
                        .incoming.resolutionResult
                        .allComponents
                        .find {
                            it.moduleVersion?.group == group &&
                                    it.moduleVersion?.name == artifact
                        }?.moduleVersion?.version

                println ''
                println "Dependency: ${group}:${artifact}"
                println "Current version: ${current ?: 'Not found in runtimeClasspath'}"
                println "Latest stable version: ${latest}"
                println ''
            }
        }
    }
}