pipeline {
    agent any

    stages {
        stage('Checkout') {
            steps {
                git url: 'https://github.com/ajithgitgit/Apitesting.git', branch: 'main'
            }
        }

        stage('Test in Container') {
            agent {
                docker {
                    image 'maven:3.8.8-openjdk-17'  // or whatever image fits your need
                    args '-v $HOME/.m2:/root/.m2'  // to reuse local Maven cache
                }
            }
            steps {
                sh 'mvn clean test'
            }
        }
    }
}
