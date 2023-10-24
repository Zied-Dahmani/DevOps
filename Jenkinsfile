pipeline {

      agent any

      stages {
            stage("MVN SonarQube"){
                  steps{
                        sh 'sonar:sonar'
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
