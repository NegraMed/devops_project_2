pipeline {

    agent { label 'maven' }


    stages {
        stage ('GIT') {
            steps {
               echo "Getting Project from Git"; 
                git branch: "shili_branch", url: "https://github.com/ahmed-shili/DevopsProject.git";
            }
        }
        stage("Build") {
            steps {
                sh "mvn clean package";
            }
        }
        
        stage("Sonar") {
            steps {
                sh 'mvn sonar:sonar -Dsonar.login="admin" -Dsonar.password="sonar"'
            }
        }
        
        stage("Build artifact") {
            steps {
                sh "sudo docker build -t tpachat .";
            }
        }
        
        stage("docker compose up") {
            steps {
                sh "sudo docker compose up -d";
            }
        }
        stage('Deployment') {
            steps {
                sh 'mvn deploy -Dmaven.test.skip=true'
            }
        }

        stage("docker compose down") {
            steps {
                sh "sudo docker compose down";
            }
        }
    }
   
    post {
        always {
            cleanWs()
        }
    }
    
}