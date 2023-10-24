pipeline {

      agent any
      environment {
            SONAR_LOGIN = "admin"
            SONAR_PASSWORD = "sonar"
      }
      stages {
            stage("MVN Clean") {
                  steps{
                        sh "mvn clean"
                  }
            }
            
            stage("MVN Compile") {
                  steps{
                        sh "mvn compile"
                  }
            }
            
            stage("MVN SonarQube") {
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
