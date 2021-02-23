pipeline {
    agent {
    docker {
      image 'maven:3.6.3-jdk-11'
      args '-v /root/.m2:/root/.m2/calculator'
    }
  }
    stages {
        stage('Compile'){
                steps{
                    dir("/var/jenkins_home/workspace/pipeline-challenge/calculator"){
                     
                        sh 'echo Compile'
                         sh 'mvn  compile'
                    }
                }
            
        }
         stage('Test'){
                 steps {
                      dir("/var/jenkins_home/workspace/pipeline-challenge/calculator"){
                         sh 'echo Test'
                         sh 'mvn test'
                      }
                 }
                 post{
                     always {
                         junit '**/target/surefire-reports/TEST-*.xml'
                     }
                 }
            }
            stage('Build'){
            steps {
                dir("/var/jenkins_home/workspace/pipeline-challenge/calculator"){
                
                        sh 'echo Build'
                        sh 'mvn  package'
                }
                 
            }
            
            post {
                always {
                    //junit '**/target/surefire-reports/TEST-*.xml'
                    archiveArtifacts 'SimpleCalculator/target/*.jar'
                     }
                }
             }
        stage('Sonar Analysis'){
            steps{
                  dir("/var/jenkins_home/workspace/pipeline-challenge/calculator"){
                    withSonarQubeEnv('Sonar'){
                        withMaven(maven:'maven'){
                            sh 'mvn sonar:sonar'
                        }
                    }
                  }
                
            }
            
        }
    }
}
