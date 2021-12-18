pipeline {
  agent any
  stages {
    stage('Initialize') {
      agent any
      steps {
        echo 'Initliazing the project'
      }
    }

    stage('Clone') {
      agent {
        node {
          label 'default'
        }

      }
      steps {
        git(url: 'https://github.com/kiranraj2208/test.git', branch: 'main')
      }
    }

    stage('Build') {
      agent {
        node {
          label 'maven'
        }

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