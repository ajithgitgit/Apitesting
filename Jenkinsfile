pipeline {
    agent any

    stages {
        stage('Checkout') {
            steps {
                git url: 'https://github.com/ajithgitgit/Apitesting.git', branch: 'main'
            }
        }

        stage('Test in Docker Container') {
            steps {
                script {
                    docker.image('maven:3.8.8-eclipse-temurin-17').inside('-v $HOME/.m2:/root/.m2') {
                        sh 'mvn clean test'
                    }
                }
            }
        }
    }
}
