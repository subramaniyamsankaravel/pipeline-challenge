pipeline {
   agent any
   tools{
      maven 'maven'
      jdk 'JDK11'
  }
    stages{
        
        stage('Clean'){
            steps{
                dir("C:\Windows\System32\config\systemprofile\AppData\Local\Jenkins\.jenkins\workspace\CI-endtoend\calculator"){
                    sh 'echo Clean'
                    sh 'mvn  clean'
                }
            }
        }
        stage('Validate'){
                steps{
                     dir("C:\Windows\System32\config\systemprofile\AppData\Local\Jenkins\.jenkins\workspace\CI-endtoend\calculator"){
                
                        sh 'mvn  validate'
                     }
                }
            
         }
        stage('Compile'){
                steps{
                     dir("C:\Windows\System32\config\systemprofile\AppData\Local\Jenkins\.jenkins\workspace\CI-endtoend\calculator"){
                 
                        sh 'echo Compile'
                         sh 'mvn  compile'
                     }
                }
            
        }
        
        
             stage('Test'){
                 steps {
                      dir("C:\Windows\System32\config\systemprofile\AppData\Local\Jenkins\.jenkins\workspace\CI-endtoend\calculator"){
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
                 dir("C:\Windows\System32\config\systemprofile\AppData\Local\Jenkins\.jenkins\workspace\CI-endtoend\calculator"){
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
                 dir("C:\Windows\System32\config\systemprofile\AppData\Local\Jenkins\.jenkins\workspace\CI-endtoend\calculator"){
                
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
            sh 'scp -r C:\Windows\System32\config\systemprofile\AppData\Local\Jenkins\.jenkins\workspace\CI-endtoend\calculatortarget/calculator-0.0.1-SNAPSHOT.jar ubuntu@18.216.159.12:/home/ubuntu/artifacts'
            }
            }
            }

} 
}
