import local.jenkins.methods.Common
import local.jenkins.constants.Constants

def call() {
  def common = new Common()
	def constants = new Constants()
	pipeline {
			agent {
				node {
					//label 'revenga'
				}
			}
			options {
				timeout(time: 3, unit: 'HOURS')
				disableConcurrentBuilds()
			}
	    stages {
				stage('Checkout') {script{common.checkout()}}
				stage('Build') {script{common.buildImage()}}
				stage('Push') {script{common.pushImage()}}
	    }

	}
}
