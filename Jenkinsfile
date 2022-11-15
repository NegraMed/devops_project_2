pipeline {
    agent any
    
      environment {
		registryCredential = 'docker' 
	}
      tools {
        // Install the Maven version configured as "M3" and add it to the path.
        maven "M2_HOME"
    }
    

    stages {
        stage('Preparation') { 
            steps{
                git url:'https://github.com/DevopsTeamGroupe2/devops_project_2.git', branch: 'fatma1'
             }
       }
        
        stage('Testing maven') {
            steps {
                sh "mvn -version"
            }
        }
         stage('clean project  '){
            steps{
                echo 'Cleaning Project '
                echo "Maven Version "
                sh "mvn -Dmaven.test.failure.ignore=true clean package"
              }
      }
      
       stage('Building Project'){
            steps{
                script{
                        sh 'mvn clean install -DskipTests'
                }
            }
        }
        
        stage("Building Tests with Mockito"){
            steps{
                sh 'mvn test'
          }
      }
        
        
        stage(" SonarQube") {
            steps{
                sh 'mvn sonar:sonar -Dsonar.login=admin -Dsonar.password=sonar -DskipTests'
            }
        }
        
        stage("Nexus"){
            steps{
               sh 'mvn deploy -DskipTests'
          }
      }
		
        
        stage('Build Docker image'){
            steps{
                script{
                        dockerImage = docker.build("khalfallah25/tpachatproject")
                }
            }
        }
    
        stage('Deploy our image') {
            steps{
            script {
            docker.withRegistry( '', registryCredential ) {
            dockerImage.push()
}
}
}
}
    
        stage('docker compose ') { 
            steps { 
                sh "docker-compose up -d"
                

                
            }
        }    

        
        
    }
}
