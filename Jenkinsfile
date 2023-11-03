pipeline {
    agent any

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
                         url :'https://github.com/Zied-Dahmani/DevOps.git';

                         // Utilisez les identifiants GitHub pour cloner le référentiel privé
                            /* checkout([$class: 'GitSCM',
                                 branches: [[name: 'main']],
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
            stage("Build") {
                                      steps{
                                            sh "mvn package"
                                      }
                                }


            stage("Build2") {
                            steps{
                                 sh "mvn install"
                            }
                         }

    }
}
