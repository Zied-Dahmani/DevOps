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
      }
}
