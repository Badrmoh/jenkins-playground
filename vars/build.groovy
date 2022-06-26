import local.jenkins.methods.Common
import local.jenkins.constants.Variables

def call() {
  def common = new Common()
	def consts = Variables.getInstance()
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
//				stage('Check variables') {steps{script{echo "${consts.TEST_VAR1}\n${consts.TEST_VAR2}"}}
				stage('Build') {steps{script{common.buildImage()}}}
				stage('Push') {steps{script{common.pushImage()}}}
	    }

	}
}
