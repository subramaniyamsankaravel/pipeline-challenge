pipeline {
   agent any
   tools{
      maven 'maven'
      jdk 'JDK'
  }
    stages{
        
        stage('Clean'){
            steps{
                dir("C:/WINDOWS/system32/config/systemprofile/AppData/Local/Jenkins/.jenkins/workspace/CI-endtoend/calculator"){
                    bat 'echo Clean'
                    bat 'mvn  clean'
                }
            }
        }
        stage('Validate'){
                steps{
                     dir("C:/WINDOWS/system32/config/systemprofile/AppData/Local/Jenkins/.jenkins/workspace/CI-endtoend/calculator"){
                
                        bat 'mvn  validate'
                     }
                }
            
         }
        stage('Compile'){
                steps{
                     dir("C:/WINDOWS/system32/config/systemprofile/AppData/Local/Jenkins/.jenkins/workspace/CI-endtoend/calculator"){
                 
                        bat 'echo Compile'
                         bat 'mvn  compile'
                     }
                }
            
        }
        
        
             stage('Test'){
                 steps {
                      dir("C:/WINDOWS/system32/config/systemprofile/AppData/Local/Jenkins/.jenkins/workspace/CI-endtoend/calculator"){
                         bat 'echo Test'
                         bat 'mvn test'
                      }
                 }
      
            }

            
            stage('Build'){
            steps {
                 dir("C:/WINDOWS/system32/config/systemprofile/AppData/Local/Jenkins/.jenkins/workspace/CI-endtoend/calculator"){
                
                        bat 'echo Build'
                        bat 'mvn  package -Dbuild.number=-${BUILD_NUMBER}'
                 }
            }
            
            post {
                always {
                    //junit '**/target/surefire-reports/TEST-*.xml'
                    archiveArtifacts 'calculator/target/*.jar'
                     }
                }
             }
       stage('Docker build'){
            steps{
                  dir("C:/WINDOWS/system32/config/systemprofile/AppData/Local/Jenkins/.jenkins/workspace/CI-endtoend/calculator"){
                    bat 'docker image prune -a --force'
                    bat 'docker build .'
                  }
                
                
            }
        }
        stage('Pushing images to docker hub'){
            steps{
                

               
                           
                   bat "docker login -u subramaniyam -p Jp9.kumaran"

                
                
                bat "docker push subramaniyam/todo-app:v${env.BUILD_ID}"

            }
        }
     
   

} 
}
