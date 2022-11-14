pipeline {

    agent { label 'maven' }
    environment{ 
        DOCKERHUB_CREDENTIALS=credentials('dockerhub')
    }

    stages {
        stage ('GIT') {
            steps {
               echo "Getting Project from Git"; 
                git branch: "shili", url: "https://github.com/DevopsTeamGroupe2/devops_project_2";
            }
        }
        stage('Unit Testing : Test Dynamique Junit and  Mockito'){
            steps {
                sh "mvn clean test -Ptest";
            }
        }

        stage("SRC Analysis : Test Statique Sonar") {
            steps {
                sh 'mvn sonar:sonar -Dsonar.login="admin" -Dsonar.password="sonar" -Ptest'
            }
        }

        stage("Build Artifact") {
            steps {
                sh "mvn clean package -Pprod";
            }
        }

        //stage("Build Docker image") {
        //    steps {
        //        sh "sudo docker build -t ahmedshili/tpachat .";
        //    }
        //}

        stage("Push Docker image to nexus Private Repo") {
            steps {
                sh "sudo docker login -u admin -p nexus 192.168.1.100:8082/repository/docker-hosted-validation/";
                sh "sudo docker tag ahmedshili/tpachat 192.168.1.100:8082/docker-hosted-validation/validation";
                sh "sudo docker push 192.168.1.100:8082/docker-hosted-validation/validation";
            }
        }
        
        stage('Deploy Artifact to Nexus') {
            steps {
                sh 'mvn deploy -Dmaven.test.skip=true -Pprod'
            }
        }

        stage("Build Docker image from nexus repo") {
            steps {
                sh "sudo docker pull 192.168.1.100:8082/docker-hosted-validation/validation";
            }
        }
        
        //stage('Deploy Image to DockerHub') {
        //    steps {
		//		sh 'echo $DOCKERHUB_CREDENTIALS_PSW | sudo docker login -u $DOCKERHUB_CREDENTIALS_USR --password-stdin';
        //        sh 'sudo docker push ahmedshili/tpachat';
        //    }
        //}

        stage("Start Containers : with docker compose") {
            steps {
                sh "sudo docker compose up -d";
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
            emailext body: 'Jenkins Pipeline done successfully', recipientProviders: [[$class: 'ahmed.shili@esprit.tn']], subject: 'Jenkins Pipeline'
            cleanWs()
        }
    }
}