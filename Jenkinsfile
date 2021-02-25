pipeline {
    agent {
    docker {
      image 'maven:3.6.3-jdk-11'
      args '-v /root/.m2:/root/.m2/calculator'
    }
  }
    
    stages {
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
        stage('Sonar Analysis'){
            agent any
            steps{
                 dir("/var/jenkins_home/workspace/pipeline-challenge/calculator"){
                    withSonarQubeEnv('sonar'){
                        withMaven(maven:'maven'){
                            sh 'mvn clean package sonar:sonar'
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
                    archiveArtifacts 'calculator/target/*.jar'
                     }
                }
               
             }
        stage('collect artifact'){
                steps{
                    archiveArtifacts artifacts: 'calculator/target/*.jar', followSymlinks: false
                 }
            }  
        stage('deploy to artifactory')
         {
            steps{
     
                rtUpload (
                    serverId: 'ARTIFACTORY-SERVER',
                    spec: '''{
                    "files": [
                         {
                             "pattern": "calculator/target/*.jar",
                             "target": "art-doc-dev-loc"
                        }
                     ]
                }''',
 
  

)
     }}
     
   
}
    post{
        sshagent(['4caf8f9d-4507-4358-a814-4a2866505100']){
                    sh 'scp -r /var/jenkins_home/workspace/pipeline-aws/CodingChallenge-2/target/*.jar ubuntu@18.216.159.12:/home/ubuntu/artifacts'
        }
        
        failure{
            echo 'I failed!'
           
        }

    }
}
