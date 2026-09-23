// 1. Optional: Create a logical folder in Jenkins to organize your generated pipelines
folder('automated-pipelines') {
    description('All pipelines in this folder are completely managed by the Super Seed.')
}

// 2. Corrected Wrapper: Explicitly declare the master job that does the scanning
freeStyleJob('Master-Super-Seed') {
    description('This job automatically scans the Git repo for team-specific DSL scripts.')

    // Tell this job where to execute the scan step
    steps {
        dsl {
            // Scans the repo for any team scripts located inside jobs/teams/
            external('jobs/teams/**/*.groovy') 
            
            // Keeps Jenkins clean: deletes pipelines if their Groovy file is deleted from Git
            removeAction('DELETE') 
            removeViewAction('DELETE')
        }
    }
}
