pipeline {
    agent any
       environment {
            SONAR_LOGIN = "admin"
            SONAR_PASSWORD = "sonarqube12"
      }
    stages {
        stage('Hello') {
            steps {
                echo 'Hello World'
            }
        }
          stage('Git') {
                     steps {
                         echo 'git';
                        git branch:'emnaMekniToujeni-5SIM2-G',
                         url :'https://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@github.com/Zied-Dahmani/DevOps.git', credentialsId:1234;

                         // Utilisez les identifiants GitHub pour cloner le référentiel privé
                            /* checkout([$class: 'GitSCM',
                                 branches: [[name: 'emnaMekniToujeni-5SIM2-G']],
                                 doGenerateSubmoduleConfigurations: false,
                                 extensions: [[$class: 'CleanBeforeCheckout'], [$class: 'CloneOption', depth: 0, noTags: false, reference: '', shallow: true]],
                                 userRemoteConfigs: [[url: 'https://github.com/EmnaEmna/DevopsProject1.git', credentialsId: 'PrivateTestingDevops1']]])*/
                     }
                 }
          stage('Unit Tests') {
                     steps {
                                 sh 'mvn test'
                             }
                         }
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
           /* stage("Build") {
                                      steps{
                                            sh "mvn package"
                                      }
                                }
*/

            stage("Build2") {
                            steps{
                                 sh "mvn install"
                            }
                         }
             stage("Sonarqube") {
                             steps{
                                   sh "mvn sonar:sonar -Dsonar.login=${env.SONAR_LOGIN} -Dsonar.password=${env.SONAR_PASSWORD}"
                                   }
                              }
             stage("Nexus/Livrable/Deploy") {
                              steps{
                                     sh "mvn deploy"
                                    }
                              }
               stage("Docker Image") {
                  steps{
                        sh "docker build -t tayssi/imageback:1.0 ."
                  }
            }
             stage("Docker Hub") {
                       steps{
                             sh "docker login -u tayssi -p dckr_pat_weuStVmERIcJ8CK36R4aclagWY8"
                             sh "docker push tayssi/imageback:1.0"
                       }
               }
            stage("Docker Compose") {
                  steps{
                        sh "docker compose up -d"
                  }
            }
    }
}
