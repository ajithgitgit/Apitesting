pipeline {
    agent any

    tools {
        // Make sure these match Jenkins → Manage Jenkins → Global Tool Configuration
        jdk 'jdk17'         // Java name configured in Jenkins
        maven 'maven3'      // Maven name configured in Jenkins
    }

    environment {
        REPORT_DIR = 'target/karate-reports'
    }

    stages {
        stage('Checkout Code') {
            steps {
                echo "⬇️ Checking out repository..."
                git url: 'https://github.com/ajithgitgit/Apitesting.git', branch: 'main'
                // If private repo:
                // git url: 'https://github.com/ajithgitgit/Apitesting.git', branch: 'main', credentialsId: 'github-creds'
            }
        }

        stage('Build & Run Tests') {
            steps {
                echo "☕ Running tests with Maven..."
                sh 'mvn clean test'
            }
        }

        stage('Publish Karate Report') {
            steps {
                echo "📊 Publishing Karate Report..."
                publishHTML(target: [
                    reportDir: "${env.REPORT_DIR}",
                    reportFiles: 'karate-summary.html',
                    reportName: 'Karate Summary Report',
                    allowMissing: true,
                    keepAll: true,
                    alwaysLinkToLastBuild: true
                ])
            }
        }

        stage('Publish Extent Report') {
            steps {
                echo "📊 Publishing Extent Report..."
                publishHTML(target: [
                    reportDir: 'target',
                    reportFiles: 'karate-extent-report.html',
                    reportName: 'Extent Report',
                    allowMissing: true,
                    keepAll: true,
                    alwaysLinkToLastBuild: true
                ])
            }
        }

        stage('Archive Reports') {
            steps {
                echo "📦 Archiving reports..."
                archiveArtifacts artifacts: 'target/**/*.html', fingerprint: true
            }
        }
    }

    post {
        always {
            echo "🧹 Cleaning workspace..."
            deleteDir()
        }
    }
}
