
pipeline {
    agent any

    options {
        skipDefaultCheckout(true)
    }

    stages {
        stage('Checkout') {
            steps {
                checkout scm
            }
        }

        stage('Compile') {
            steps {
                bat '''
                    if not exist build mkdir build
                    if not exist dist mkdir dist
                    javac -d build src\\*.java
                '''
            }
        }

        stage('Test') {
            steps {
                bat '''
                    javac -cp build -d build test\\*.java
                    java -cp build EmployeeManagerTest
                '''
            }
        }

        stage('Package') {
            steps {
                bat '''
                    jar --create --file dist\\employee-management.jar --main-class Main -C build .
                '''
            }
        }

        stage('Deploy') {
            steps {
                bat '''
                    if not exist deployment mkdir deployment
                    copy /Y dist\\employee-management.jar deployment\\employee-management.jar
                    java -jar deployment\\employee-management.jar --healthcheck
                '''
            }
        }
    }

    post {
        success {
            echo 'SUCCESS: Build, tests, packaging and deployment completed.'
        }

        failure {
            echo 'FAILED: Check the Jenkins console output.'
        }

        always {
            archiveArtifacts artifacts: 'dist/employee-management.jar',
                             allowEmptyArchive: true
        }
    }
}
