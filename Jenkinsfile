pipeline {

      agent any
      environment {
            SONAR_LOGIN = "admin"
            SONAR_PASSWORD = "sonar"
      }


      stages {
            stage("Clean") {
                  steps{
                        sh "mvn clean"
                  }
            }
            
            stage("Compile") {
                  steps{
                        sh "mvn compile"
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
                          sh "docker login -u souhailkrs -p dckr_pat_T6nf5NYfJYP8JJ9jihqZBMe5j78"
                          sh "docker tag devops:1.0 souhailkrs/devops:1.0"
                          sh "docker push souhailkrs/devops:1.0"
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
      }
}
