pipeline {
    agent any

    stages {
        stage('Checkout') {
            steps {
                git url: 'https://github.com/ajithgitgit/Apitesting.git', branch: 'main'
            }
        }
        stage('Build') {
            steps {
                sh 'mvn clean test'
            }
        }
    }
}
