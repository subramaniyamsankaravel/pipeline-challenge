pipeline {
    agent {
    docker {
      image 'maven:3.6.3-jdk-11'
    }
  }
    
 stages
        
        stage('Clean'){
            steps{
                dir("/var/jenkins_home/workspace/pipeline-challenge/calculator"){
                    sh 'echo Clean'
                    sh 'mvn  clean'
                }
            }
        }
        stage('Validate'){
                steps{
                     dir("/var/jenkins_home/workspace/pipeline-challenge/calculator"){
                
                        sh 'mvn  validate'
                     }
                }
            
         }
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

        stage('SonarQube Quality Gate') { 
            steps{
                timeout(time: 1, unit: 'HOURS') { 
                    script{
                        def qg = waitForQualityGate() 
                        if (qg.status != 'OK') {
                            error "Pipeline aborted due to quality gate failure: ${qg.status}"
                         }
                    }
                    
                }
            }
        }
            stage('Build'){
            steps {
                 dir("/var/jenkins_home/workspace/pipeline-challenge/calculator"){
                
                        sh 'echo Build'
                        sh 'mvn  package -Dbuild.number=-${BUILD_NUMBER}'
                 }
            }
            
            post {
                always {
                    //junit '**/target/surefire-reports/TEST-*.xml'
                    archiveArtifacts 'pipeline-challenge/calculator/target/*.jar'
                     }
                }
             }
     
   stage ('ssh-test'){
        steps{
          sshagent(['4caf8f9d-4507-4358-a814-4a2866505100']){
             sh 'scp -r /var/jenkins_home/workspace/pipeline-challenge/calculator/target/*.jar ubuntu@18.216.159.12:/home/ubuntu/artifacts'
            }
            }
            }
}
    
}
