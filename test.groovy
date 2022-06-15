node ("JenkinsSlave1") {
    stage ("List Current Dir"){
        sh "pwd"
        sh "ls -lh"
    }
}