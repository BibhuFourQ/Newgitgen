node () {
    stage ("List Current Dir"){
        sh "pwd"
        sh "ls -lh"
    }
}
