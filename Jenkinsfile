pipeline {
    agent any

    tools {
        // Ensure Java 17 and Maven are installed via Jenkins Global Tool Configuration
        jdk 'jdk17'     // This must match the name configured in Jenkins
        maven 'maven3'  // This must match the name configured in Jenkins
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

        stage('Build & Run Tests') {
            steps {
                echo "☕ Setting up Java & Maven environment..."
                sh 'mvn clean test'
            }
        }

        stage('Publish Karate Report') {
            steps {
                echo "📁 Publishing Karate Report..."
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

        stage('Publish Extent Report (Optional)') {
            steps {
                echo "📁 Publishing Extent Report..."
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
                echo "📦 Archiving reports..."
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
