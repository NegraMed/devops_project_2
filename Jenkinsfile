pipeline {
    agent {label 'maven'}
    environment{ 
        DOCKERHUB_CREDENTIALS=credentials('dockerhub')
    }

    stages {
        stage('Hello') {
            steps {
                sh 'mvn --version'
                sh 'ls /home/vagrant'
            }
        }
        stage ('GIT') {
            steps {
               echo "Getting Project from Git"; 
                git branch: "Amine", 
                    url: "https://github.com/DevopsTeamGroupe2/devops_project_2.git";
            }
        }
          stage('Unit Testing : Test Dynamique Junit and  Mockito'){
            steps {
                sh "mvn clean test -Ptest";
            }
        }
               
        stage("Build Artifact") {
            steps {
                sh "mvn clean package -Pprod";
            }
            }
            
        stage("Build Docker image from nexus repo") {
            steps {
                sh "sudo docker pull 192.168.1.20:8082/docker-hosted-validation/validation";
            }
        }

        
        stage("Sonar") {
            steps {
                sh 'mvn sonar:sonar -Dsonar.login="admin" -Dsonar.password="amine" -Ptest'
            }
        }
        
        //stage("Build artifact") {
            //steps {
              //  sh "sudo docker build -t tpachato .";
            //}
        //}
        stage('Deploy Artifact to Nexus') {
            steps {
                sh 'mvn deploy -Dmaven.test.skip=true -Pprod'
            }
        }
        stage('Deploy Image to DockerHub') {
            steps {
				sh 'echo $DOCKERHUB_CREDENTIALS_PSW | sudo docker login -u $DOCKERHUB_CREDENTIALS_USR --password-stdin';
                sh 'sudo docker push aminenessah/tpachat';
            }
        }

        
        stage("docker compose") {
            steps {
                sh "sudo docker compose up -d";
            }
        }
    
    post {
        always {
            cleanWs()
        }
    }
     
        
    }
}
