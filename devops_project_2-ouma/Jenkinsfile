pipeline {
    agent any
    
      tools {
        // Install the Maven version configured as "M3" and add it to the path.
        maven "M2_HOME"
    }

     environment {
       
       registryCredential = 'dockerhub'
       registry="oumazou/tpachatproject"
       dockerImage = ''
        }
        
    stages {
      
        stage('Project Preparation') { 
            steps{
                git url:'https://github.com/DevopsTeamGroupe2/devops_project_2.git', branch : 'ouma'
             }
       }
        
        stage('Testing maven') {
            steps {
                sh "mvn -version"
            }
        }
        
        stage('clean project maven '){
            steps{
                echo 'Cleaning Project '
                echo "Maven Version "
                sh "mvn -Dmaven.test.failure.ignore=true clean package"
              }
      }
      
       stage('Building Project with maven'){
            steps{
                script{
                        sh 'mvn clean install -DskipTests'
                }
            }
        }
           
        
        stage("Runing Tests with Mockito") {
            steps{
                sh 'mvn test'
                }
        }
        
        stage("SRC Analysis Testing with SonarQube") {
            steps{
                sh 'mvn sonar:sonar -Dsonar.login=admin -Dsonar.password=1234 -DskipTests'
            }
        }
        
        stage("Deploy artifact to Nexus"){
            steps{
               sh 'mvn deploy -DskipTests'
          }
      }
   
    stage('Build Docker image'){
            steps{
                script{
                        dockerImage = docker.build("oumazou/tpachatproject")
                }
            }
        }
        
 /*   stage('Deploy image to DockerHub') {
        steps{
            script {
            docker.withRegistry( '', registryCredential ) {
            dockerImage.push()
}
}
}
}*/

     
    stage('Start Containers '){
        steps{
            script{
                sh 'docker-compose up -d'
                }
            }
        }
        
    }
}