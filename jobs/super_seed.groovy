// 1. Create a logical folder in Jenkins for the Super Seed to organize things
folder('automated-pipelines') {
    description('All pipelines in this folder are completely managed by the Super Seed.')
}

// 2. Instruct the Super Seed to scan your repository for team-specific DSL files
// This tells Jenkins: "Find every groovy file inside the 'teams' folder and run it"
steps {
    dsl {
        external('jobs/teams/**/*.groovy') 
        
        // Crucial: If a team deletes their file from Git, Jenkins deletes their pipeline automatically
        removeAction('DELETE') 
        
        // Crucial: If a team modifies a view/folder structure, update it automatically
        removeViewAction('DELETE')
    }
}
