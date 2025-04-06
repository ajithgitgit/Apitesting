pipeline {
    agent any

    tools {
        // Ensure these tool names match Jenkins Global Tool Configuration
        jdk 'jdk17'
        maven 'maven3'
    }

    environment {
        REPORT_DIR = 'target/karate-reports'
    }

    stages {

        stage('Checkout Code') {
            steps {
                echo "⬇️ Checking out repository..."
                checkout scm
            }
        }

        stage('Build & Run Karate Tests') {
            steps {
                echo "☕ Building project and running Karate tests..."
                sh 'mvn clean test'
            }
        }

        stage('Publish Karate Summary Report') {
            steps {
                echo "📊 Publishing Karate Summary Report..."
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

        stage('Publish Karate Extent Report') {
            steps {
                echo "📊 Publishing Karate Extent Report..."
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

        stage('Archive All Reports') {
            steps {
                echo "📦 Archiving all HTML reports..."
                archiveArtifacts artifacts: 'target/**/*.html', fingerprint: true
            }
        }
    }

    post {
        always {
            echo "🧹 Cleaning up workspace..."
            deleteDir()
        }
    }
}
