pipeline {
  agent any
  stages {
    stage('Build') {
      steps {
        echo 'Starting Indigo test automation build'
        sh 'mvn -f ./pom.xml clean compile'
      }

    post {
    notBuilt {
     cleanWs()
        dir("${env.WORKSPACE}@tmp") {
                    deleteDir()
                }
                dir("${env.WORKSPACE}@script") {
                    deleteDir()
                }
                dir("${env.WORKSPACE}@script@tmp") {
                    deleteDir()
                }
    }
    aborted {
     cleanWs()
     dir("${env.WORKSPACE}@tmp") {
                    deleteDir()
                }
                dir("${env.WORKSPACE}@script") {
                    deleteDir()
                }
                dir("${env.WORKSPACE}@script@tmp") {
                    deleteDir()
                }
    }
    }
    }
      stage('test') {
      steps {
        echo "profile to be used is : ${params.profile}"
        echo "Starting Indigo test automation for :  ${params.testsuite}"
        echo "BS cloud value is :  ${params.on_bs_cloud}"
        echo "Env value is :  ${params.env}"
        echo "BROWSER value is :  ${params.browser}"
        echo "Profile value is :  ${params.profile}"
        echo "TC value is :  ${params.testcase}"
        sh "mvn clean test  -DRUN_ON_BROWSERSTACK=${params.run_on_cloud} -DRUN_ON_ENV=${params.env} -DBROWSER_TO_USE=${params.browser} -DTESTCASE_TO_RUN=${params.testcase}  -P ${params.profile}"

        }
      }
  }

  options {
  buildDiscarder logRotator(artifactDaysToKeepStr: '10', artifactNumToKeepStr: '10', daysToKeepStr: '10', numToKeepStr: '10')
   }


  tools {
    maven 'maven'
  }
  parameters {
    choice(choices: ['select cloud','true','false'], description: 'Please select the true/false to execute tests on Browser stack.By default true if left blank', name: 'run_on_cloud')
    choice(choices: ['select env','Dev','QA','UAT','Pre-Prod'], description: 'Please select the env', name: 'env')
    choice(choices: ['select browser','chrome', 'firefox', 'edge'], description: 'Please select the browser', name: 'browser')
    choice(choices: ['select profile','cloud-test', 'bs-local-test', 'local-test'], description: 'Please select profile', name: 'profile')
    choice(choices: ['select testcase','regression','smoke', 'homePage', 'SRP','passengerEdit','add-ons','seatMap','paymentPage','itineraryPage'], description: 'Please select TC',name: 'tc_to_run')


  }
}