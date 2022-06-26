package local.jenkins.methods

def checkout() {
	echo "======> Checking out ${env.BRANCH_NAME}"
	checkout scm
	
	if (params.COMMIT != "" && params.COMMIT != "latest"){
	  def commit_id = params.COMMIT
		script {
			sh (
					script: "git checkout ${params.COMMIT}",
      		returnStdout: true
			).trim()
		}
	else if (params.COMMIT == "latest") {
		echo "======> Testing latest commit"
		script {
			sh (
      			script: "git checkout refs/remotes/origin/${env.BRANCH_NAME}",
      			returnStdout: true
      		).trim()
		}
	}
	}
}

def buildImage() {
	echo "=======> Building image"
}

def pushImage() {
	echo "=======> Pushing image"
}
