#!groovy
pipeline {
    agent any
    job('adding elastic timeout') {   
        wrappers {
            timeout {
                elastic(100, 3, 1)
            }
        }
    }
    stages {
        stage('Stage1') {
            steps {
                echo 'Building Future'
                sleep 75
            }
        }
    }
}
