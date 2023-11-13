pipeline {

      agent any
      environment {
            SONAR_LOGIN = "admin"
            SONAR_PASSWORD = "sonar"
      }
      stages {

            stage("Build") {
                  steps{
                        sh "mvn package"
                  }
            }

            stage("SonarQube") {
                  steps{
                       sh "mvn sonar:sonar -Dsonar.login=${env.SONAR_LOGIN} -Dsonar.password=${env.SONAR_PASSWORD}"
                  }
            }

            stage("JUnit/Mockito") {
                  steps{
                        sh "mvn test"
                  }
            }

            stage("Nexus") {
                  steps{
                        sh "mvn deploy"
                  }
            }

            stage("Docker Image") {
                  steps{
                        sh "docker build -t devops:1.0 ."
                  }
            }

           stage("Docker Hub") {
                    steps{
                          sh "docker login -u jaouayoussef -p dckr_pat_cjrXbePwF3f9ixs-c1rIkacIvlU"
                          sh "docker tag devops:1.0 jaouayoussef/devops:1.0"
                          sh "docker push jaouayoussef/devops:1.0"
                    }
            }

            stage("Docker Compose") {
                  steps{
                        sh "docker compose up -d"
                  }
            }
                        stage('Grafana') {
    steps {
        sh 'docker run -d -p 4003:3000 grafana/grafana'
    }
}


stage('Prometheus') {
    steps {
        sh 'docker run -d -p 9094:9090 prom/prometheus'
    }
}

            stage('Email Notification') {
            steps {
                script {
                    mail bcc: '', body: '''Build complete,''', cc: '', from: '', replyTo: '', subject: 'Email Notification', to: 'souhail.krissaane@esprit.tn'
                }
            }
        }
      }
}
