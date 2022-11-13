pipeline {

    agent { label 'validation' }
    environment{ 
        DOCKERHUB_CREDENTIALS=credentials('dockerhub')
    }

    stages {
        stage ('Git checkout branch') {
            steps {
            echo "Getting Project from Git"; 
                git branch: "farjallah", 
                    url: "https://github.com/oumazou/devops_project_2.git";
            }
        }

        stage('Unit Testing : Test Dynamique Junit and  Mockito'){
            steps {
                sh "mvn clean test -Ptest";
            }
        }

        stage("SRC Analysis : Test Statique Sonar") {
            steps {
                sh 'mvn sonar:sonar -Dsonar.login="admin" -Dsonar.password="vagrant" -Ptest'
            }
        }

        stage("Build Artifact") {
            steps {
                sh "mvn clean package -Pprod";
            }
        }

        //stage("Build Docker image") {
          //  steps {
            //    sh "sudo docker build -t farjo/tpachat .";
            //}
        //}



        stage("Build Docker image from nexus repo") {
            steps {
                sh "sudo docker pull 192.168.1.50:8082/docker-hosted-validation/validation";
            }
        }
        
        stage('Deploy Artifact to Nexus') {
            steps {
                sh 'mvn deploy -Dmaven.test.skip=true -Pprod'
            }
        }

        //stage('Deploy Image to DockerHub') {
          //  steps {
			//	sh 'echo $DOCKERHUB_CREDENTIALS_PSW | sudo docker login -u $DOCKERHUB_CREDENTIALS_USR --password-stdin';
              //  sh 'sudo docker push farjo/tpachat';
            //}
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
            cleanWs()
        }
    }




}