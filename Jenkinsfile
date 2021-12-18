pipeline {
  agent none
  stages {
    stage('Initialize') {
      steps {
        echo 'Initliazing the project'
      }
    }

    stage('Clone') {
      steps {
        git(url: 'https://github.com/kiranraj2208/test.git', branch: 'main')
      }
    }

    stage('Build') {
      agent {
        label 'maven'
      }
      steps {
        sh 'mvn clean compile'
      }
    }

    stage('Test') {
      agent {
        label 'maven'
      }
      steps {
        sh 'mvn test'
      }
    }

  }
}