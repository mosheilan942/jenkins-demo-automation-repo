// 1. Optional folder structure
folder('automated-pipelines') {
    description('All pipelines in this folder are completely managed by the Super Seed.')
}

// 2. The Master Scanning Job definition
freeStyleJob('Master-Super-Seed') {
    description('This job automatically clones the Git repo and scans for team-specific DSL scripts.')

    // FIX: Tell this generated job where to pull its files from!
    scm {
        git {
            remote {
                url('https://github.com/mosheilan942/jenkins-demo-automation-repo.git')
            }
            branch('main') // or */master depending on your repository
        }
    }

    // Now when the job runs, it clones the repo first, making this path valid!
    steps {
        dsl {
            external('jobs/teams/**/*.groovy') 
            removeAction('DELETE') 
            removeViewAction('DELETE')
        }
    }
}
