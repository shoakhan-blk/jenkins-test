#!groovy
//Consider test-local-v2 as PR branch and jenkins-v2 as master branch
@Library("timeoutLib")
Integer numberOfBuilds=5
Double totalBuildTime=0.0
echo "before calling scripted library"
def buildTimeFetched=0
node {
def workspace = pwd() 
echo "workspace is ${workspace}"
def pipeline = load "${workspace}@libs/timeoutLib/vars/jenkins-timeout.groovy"
buildTimeFetched=pipeline.call("jenkins-v2",5,1.5)
}
echo "build time fetched from groovy value is ${buildTimeFetched}"
echo "after caling scripted library"
Float buildtime=buildTimeFetched
pipeline {
    agent any
    options {
        timeout(time: buildtime, unit: 'MINUTES')
    }
    stages {
        stage('Stage1') {
            steps {
                echo 'Building Future'
                sleep 70
            }
        }
    }
}
