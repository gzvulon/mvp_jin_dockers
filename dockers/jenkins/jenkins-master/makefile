image = jenkins-master

help:
	@echo "build " $(image)

build:
	@docker build -t $(image) .

clean:
	@docker rmi $(image)
