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
                        sh "docker build -t seifeddineabdelkader-5sim2-g2-devops:1.0 ."
                  }
            }

            stage("Docker Hub") {
                    steps{
                          sh "docker login -u fun0st -p dckr_pat_j2LVb7pRP_KoeMTeoSaSSrzBUdM"
                          sh "docker tag seifeddineabdelkader-5sim2-g2-devops:1.0 fun0st/seifeddineabdelkader-5sim2-g2-devops:1.0"
                          sh "docker push fun0st/seifeddineabdelkader-5sim2-g2-devops:1.0"
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
            mail(
                bcc: '',
                body: '''Validation Pipeline has completed successfully! Greetings from the DEVOPS Team SIGMA VI.''',
                cc: '',
                from: '',
                replyTo: '',
                subject: 'Pipeline Status Update',
                to: 'seifeddine.abdelkader@esprit.tn'
            )
        }
    }
}
      }
}
