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

            stage("JUnit/Mockito") {
                  steps{
                        sh "mvn test"
                  }
            }

            stage("SonarQube") {
                  steps{
                       sh "mvn sonar:sonar -Dsonar.login=${env.SONAR_LOGIN} -Dsonar.password=${env.SONAR_PASSWORD}"
                  }
            }

            stage("Nexus") {
                  steps{
                        sh "mvn deploy"
                  }
            }

            stage("Docker Image") {
                  steps{
                        sh "docker build -t louaylabidi-5sim2-g2-devops:1.0 ."
                  }
            }

            stage("Docker Hub") {
                    steps{
                          sh "docker login -u louayyy69 -p dckr_pat_s4bthkcjix1AKw0Ptu3DW06gg-s"
                          sh "docker tag louaylabidi-5sim2-g2-devops:1.0 louayyy69/louaylabidi-5sim2-g2-devops:1.0"
                          sh "docker push louayyy69/louaylabidi-5sim2-g2-devops:1.0"
                    }
            }

            stage("Docker Compose") {
                  steps{
                        sh "docker compose up -d"
                  }
            }
            post{
              always{
                  sh 'docker logout'
              }
              success {            
                  mail to :'louay.labidi@esprit.tn',
                  subject : 'Successful Build of Jenkins Pipeline',
                  body : 'Great news! Springboot application was built successfully',
                  from : 'louulabidi@gmail.com'
              }
              failure {
                  mail to :'louay.labidi@esprit.tn',
                  subject : 'Jenkins Build Of The Backend Failed',
                  body : 'Unfortunately, the Jenkins build of the spring boot backend has encountered an issue and failed.',
                  from : 'louulabidi@gmail.com'
              }
        }
      }
}
