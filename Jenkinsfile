pipeline {
  agent {
    kubernetes {
      defaultContainer 'jnlp'
      yamlFile 'build-pod.yaml'
    }

  }
  stages {
    stage('Clone') {
      agent any
      steps {
        git(url: 'https://github.com/kiranraj2208/test.git', branch: 'main')
      }
    }

    stage('Build') {
      agent {
        docker {
          image 'adoptopenjdk/maven-openjdk11'
        }

      }
      steps {
        sh 'mvn clean compile'
      }
    }

    stage('Test') {
      agent {
        docker {
          image 'adoptopenjdk/maven-openjdk11'
        }

      }
      steps {
        sh 'mvn test'
      }
    }

  }
}