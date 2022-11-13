pipeline {
    agent {label 'maven'}

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

        
        stage("Sonar") {
            steps {
                sh 'mvn sonar:sonar -Dsonar.login="admin" -Dsonar.password="amine" -Ptest'
            }
        }
        
        stage("Build artifact") {
            steps {
                sh "sudo docker build -t tpachato .";
            }
        }
        
        stage("docker compose") {
            steps {
                sh "sudo docker compose up -d";
            }
        }
        stage('Deployment') {
            steps {
                sh 'mvn deploy -Dmaven.test.skip=true'
            }
        }
        
    }
}
