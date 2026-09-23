// Define a standard pipeline job using Job DSL
pipelineJob('main-version-pipeline') {
    description('Automated the pipeline from main branch generated via Seed DSL.')
    
    // Group your jobs into folders logically by team or department
    logRotator {
        numToKeep(5)
        daysToKeep(1)
    }

    // Define the Git Repository where the actual application code lives
    definition {
        cpsScm {
            scm {
                git {
                    remote {
                        url('https://github.com/mosheilan942/email-filter.git')
                        // credentials('git-deploy-ssh-key') // Uses securely stored credentials
                    }
                    branches('main')
                }
            }
            // Points to the Jenkinsfile inside that application repository
            scriptPath('Jenkinsfile')
            lightweight(true)
        }
    }

    // Set up automation triggers (e.g., scan for changes)
    triggers {
        scm('H/5 * * * *') // Poll Git for changes every 5 minutes
    }
}
