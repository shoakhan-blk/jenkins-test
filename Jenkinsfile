#!groovy
pipeline {
    agent any
    options {        
        timeout(time: 3, unit: 'SECONDS', elastic: 1.5)
    }
    stages {
        stage('Stage1') {
            steps {
                echo 'Building Future'
                sleep 5
            }
        }
    }
}
