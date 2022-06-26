FROM jenkins/ssh-agent:jdk11
USER root
RUN apt-get update && \
		apt-get install -y ca-certificates \
    curl gnupg lsb-release && \
		mkdir -p /etc/apt/keyrings && \
		curl -fsSL https://download.docker.com/linux/debian/gpg |\
		gpg --dearmor -o /etc/apt/keyrings/docker.gpg && \
		echo "deb [arch=$(dpkg --print-architecture) signed-by=/etc/apt/keyrings/docker.gpg] https://download.docker.com/linux/debian \
		$(lsb_release -cs) stable" | tee /etc/apt/sources.list.d/docker.list > /dev/null && \
		apt-get update && apt-get install -y docker-ce-cli

RUN apt-get install -y git

USER jenkins

ENV JENKINS_AGENT_SSH_PUBKEY 'ssh-rsa AAAAB3NzaC1yc2EAAAADAQABAAABgQDMeSwqRiWXzo1BCOkwXShLsFOjD6YHWWVX+8/oe6SCaw9X5cxnLL3YxI3ivsstqORyHGjnXw5bX3emzUF21/L7SiAO2ZtgQPw4qi3hULdSO/Zpq9a55D+NnDalNjt2v1r8N+q0ryomyN4ksBn3EqiBvkPZ4NPoTS+Di6CDDNx2J6848YN1wJcSe4ObXM+2fqG4+Yg40sdUH9lnjudl7lpcPLYM9Dwmqmf5Jd5cXHlbOFP9ViEvPgKNiHynNFXTSD6hX7lcSZTruJTC/gCC7KsfvWaT1HoBMdt9k1JZPXnDcgG1P8lZ0deS6VCUyzWjEFC7VTFCTkLIk/Q3DG6idcLs4hL3KklfznIsSe6DINjlpUpHrDGv0ZZLtwh0dYUxOtB4GrAJndscVA4xx2VtC5yTqjCrDKjB/zVas//7FLhyFEnwZDQjLc5ePYHQbbsQQDiNjZjT9TIocC1v+P73int62A3oZR8ZDKTp6KdWTzWyuXf2CmMkAYwslZi6mfmNrRc= jenkins@agent'

RUN mkdir -p ~/.ssh && echo $JENKINS_AGENT_SSH_PUBKEY >> ~/.ssh/authorized_keys
