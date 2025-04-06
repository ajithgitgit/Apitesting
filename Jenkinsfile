pipeline {
    agent any

    environment {
        IMAGE_NAME = 'karate-reqres-tests'
        REPORT_DIR = 'karate-report'
    }

    stages {

        stage('Checkout Code') {
            steps {
                echo "🔄 Checking out source code..."
                checkout scm
            }
        }

        stage('Build Docker Image') {
            steps {
                echo "🐳 Building Docker image for Karate tests..."
                sh "docker build -t ${IMAGE_NAME} ."
            }
        }

        stage('Run Tests in Docker') {
            steps {
                echo "🚀 Running Karate tests inside container..."
                sh """
                    mkdir -p ${REPORT_DIR}
                    docker run --rm -v "\$(pwd)/${REPORT_DIR}:/app/${REPORT_DIR}" ${IMAGE_NAME}
                """
            }
        }

        stage('Publish Karate Report') {
            steps {
                echo "📊 Publishing Karate Summary Report..."
                publishHTML(target: [
                    reportDir: "${REPORT_DIR}",
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
                    reportDir: "${REPORT_DIR}",
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
                echo "📦 Archiving test reports..."
                archiveArtifacts artifacts: "${REPORT_DIR}/**/*.*", fingerprint: true
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
