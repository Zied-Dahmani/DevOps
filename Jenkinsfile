pipeline {

      agent any
      environment {
            SONAR_LOGIN = credentials('admin')
            SONAR_PASSWORD = credetials('sonar')
      }
      stages {
            stage("MVN SonarQube"){
                  steps{
                       sh "mvn sonar:sonar -Dsonar.login=${env.SONAR_LOGIN} -Dsonar.password=${env.SONAR_PASSWORD}"
                  }
            }
            
         stage("build") {

             steps{
              echo 'building the application...'
            }
          }

  stage("test") {

             steps{
              echo 'testing the application...'
            }
          }

  stage("deploy") {

             steps{
              echo 'deploying the application...'
            }
          }
      }
}
