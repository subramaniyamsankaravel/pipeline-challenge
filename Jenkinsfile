pipeline {
   agent any
   tools{
      maven 'maven'
      jdk 'JDK'
  }
    stages{
        
        stage('Clean'){
            steps{
                dir("C:/Users/Welcome/jenkins/CI-endtoend/calculator"){
                    bat 'echo Clean'
                    bat 'mvn  clean'
                }
            }
        }
        stage('Validate'){
                steps{
                     dir("C:/Users/Welcome/jenkins/CI-endtoend/calculator"){
                
                        bat 'mvn  validate'
                     }
                }
            
         }
        stage('Compile'){
                steps{
                     dir("C:/Users/Welcome/jenkins/CI-endtoend/calculator"){
                 
                        bat 'echo Compile'
                         bat 'mvn  compile'
                     }
                }
            
        }
        
        
             stage('Test'){
                 steps {
                      dir("C:/Users/Welcome/jenkins/CI-endtoend/calculator"){
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
                 dir("C:/Users/Welcome/jenkins/CI-endtoend/calculator"){
                    withSonarQubeEnv('sonar'){
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
                 dir("C:/Users/Welcome/jenkins/CI-endtoend/calculator"){
                
                        sh 'echo Build'
                        sh 'mvn  package -Dbuild.number=-${BUILD_NUMBER}'
                 }
            }
            
            post {
                always {
                    //junit '**/target/surefire-reports/TEST-*.xml'
                    archiveArtifacts 'calculator/target/*.jar'
                     }
                }
             }
     
   stage ('ssh-test'){
        steps{
          sshagent(['4caf8f9d-4507-4358-a814-4a2866505100']){
            //sh 'ssh -o StrictHostKeyChecking=no ubuntu@18.216.159.12 pwd'
            sh 'scp -r C:/Users/Welcome/jenkins/CI-endtoend/calculator/target/calculator-0.0.1-SNAPSHOT.jar ubuntu@18.216.159.12:/home/ubuntu/artifacts'
            }
            }
            }

} 
}
