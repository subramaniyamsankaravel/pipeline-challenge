pipeline {
    agent {
    docker {
      image 'maven:3.6.3-jdk-11'
      args '-v /root/.m2:/root/.m2/calculator'
    }
  }
    
    stages {
     
   stage ('ssh-test'){
        steps{
          sshagent(['4caf8f9d-4507-4358-a814-4a2866505100']){
             sh 'scp -r /var/jenkins_home/workspace/pipeline-challenge/calculator/target/*.jar ubuntu@18.216.159.12:/home/ubuntu/artifacts'
            }
            }
            }
}
    
}
