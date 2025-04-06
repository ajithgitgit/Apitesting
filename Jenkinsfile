pipeline {
    agent any

    environment {
        MAVEN_IMAGE = 'maven:3.9.6-eclipse-temurin-17'
    }

    stages {
        stage('📥 Checkout Code') {
            steps {
                echo 'Cloning the repository...'
                git url: 'https://github.com/ajithgitgit/Apitesting.git', branch: 'main'
            }
        }

        stage('🐳 Build & Test in Docker') {
            steps {
                echo 'Pulling Docker image and running tests inside container...'
                script {
                    docker.image("${MAVEN_IMAGE}").inside('-v $HOME/.m2:/root/.m2') {
                        sh 'mvn clean test'
                    }
                }
            }
        }

        stage('✅ Post-Build Actions') {
            steps {
                echo 'Post-build steps go here (e.g., archiving reports, notifications)'
                // Example:
                // junit '**/target/surefire-reports/*.xml'
            }
        }
    }

    post {
        success {
            echo '🎉 Build succeeded!'
        }
        failure {
            echo '❌ Build failed!'
        }
    }
}
