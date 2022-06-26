import local.jenkins.methods.Common
import local.jenkins.constants.Variables

def call() {
  def common = new Common()
  Variables consts = Variables.instance
	pipeline {
			agent {
				node {
					label 'docker'
				}
			}
			options {
				timeout(time: 3, unit: 'HOURS')
				disableConcurrentBuilds()
			}
	    stages {
				stage('Checkout') {steps{script{common.checkout()}}}
				stage('Build') {steps{script{common.buildImage()}}}
				stage('Push') {steps{script{common.pushImage()}}}
	    }
	}
}
