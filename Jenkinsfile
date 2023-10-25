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
                          sh "docker login -u louayyy69 -p dckr_pat_s4bthkcjix1AKw0Ptu3DW06gg-s"
                          sh "docker tag devops:1.0 louayyy69/devops:1.0"
                          sh "docker push louayyy69/devops:1.0"
                    }
            }

            stage("Docker Compose") {
                  steps{
                        sh "docker compose up -d"
                  }
            }
      }
}
