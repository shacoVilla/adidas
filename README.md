# ADIDAS
Project based on the adidas technical challenge which is based on the following architecture 

![image](https://user-images.githubusercontent.com/16137987/174175103-8b0e47d8-e264-46af-b010-9e1c522c078b.png)

As part of the requirements, the public service is the one that keeps the security based on JWT and Spring Gateway

![AdidasChallenge](https://user-images.githubusercontent.com/16137987/174185152-b4e2c49b-2c07-46f2-8dcb-fa8e1cc19739.png)

# Microservice architecture
This challenge project demonstrates how to organize an application using microservices following a Doman Driven Design architecture (only the Subscription Service has it implemented). 
Adding a new microservice is easy, as the discovery server will automatically discover new services running on the cluster.

# Architecture design
As part of creating a scalable approach, the subscription service has been designed using the Domain Driven Design pattern. DDD is a strategy based on modeling your business using OOP, implementing business requirements directly within the model. DDD helps building software more effective, by allowing better mutual understanding between software programmers and business experts.

## Domain layer
Domain layer is a layer of business logic that should implement reality-reflecting business processes. By using a clear and understandable representation of the business model, new employees in a given project can be easily implemented. In a transparent form, we can also talk about the details of the application with non-technical people. Each business model should have a clearly defined Bounded Context.

## Infrastructure layer
The infrastructure layer supports communication between layers. It is responsible for communication with external websites, access to data (persistance storage) or to other resources, e.g. rabbitmq, elasticsearch etc.

## Application Layer
Provides communication with Domain Layer. Due to the fact that domain objects cannot leak outside of the domain area, domain objects created at the DL level are wrapped in DTO (Data Transfer Object) and in this form returns them to the UI layer.

![image](https://user-images.githubusercontent.com/16137987/174503057-5d98dc56-56e3-4306-98fd-279b51e81e7e.png)


# Service discovery
This project contains one discovery service using Netflix Eureka. Every Micro service will register into the Eureka server and Eureka server knows all the client applications running on each port and IP address

# API gateway
Using this strategy, the subscription service will be protected as being filtered by the gateway. This public gatewayserv ice  uses JSON Web Tokens to ensure that the end-user would have access to the subscription service  only by using a token given by the authentication service. The public gateway service will automatically discover and route API requests to the service that owns the route. This proxying technique is equally helpful when developing user interfaces, as the full API of the platform is available through its own host as a proxy.

# CI/CD Pipeline
In general purpose, every microservice would be localted in a repository controlled by git. *Git* would allow me to version and define my semantic versioning for releases.
Each microservice contains a *Dockerfile* which Jenkins would use to create and contenirize Docker images.
Each microservice contains a *Deployment YAML* file to define the creation of pods and replicas that runs this docker image
![Pipeline](https://user-images.githubusercontent.com/16137987/174551361-9ffbf7cb-42c0-4834-8256-9ea20fb8bb33.png)


# Docker Compose
In the root path there is a docker-compose.yml which is defined to start all the services with their respective dependency and bridge network (adidas-net)

# How to start the project
* Initialize the terminal command on your SO
* Execute *mvn clean install* on all the services except the *subscrition service* (which will need to pass an extra argument to define the profile *docker*)
`mvn clean install -Pdocker`
* Execute `docker-compose up` to build all the docker images, starts their containers
![image](https://user-images.githubusercontent.com/16137987/174502384-7251a918-4aa9-4a76-83d9-ef621a8ba06d.png)
* Open Postman and consume the authentication service (http://localhost:8080/authentication/login) to obtain the token that will allow to consume the subscription service
![image](https://user-images.githubusercontent.com/16137987/174502444-fa71e70e-a872-4cd2-89f1-bbe12dd08e8b.png)
Once you have the token, create a new Postman request with the header *Authorization* passing the token as value. The gateway service will handle the token to ensure that you are authorized.
![image](https://user-images.githubusercontent.com/16137987/174502484-2ab8f973-28d7-4365-9e43-d4e197523cc4.png)

# How to deploy your docker images to Kubernetes cluster
In this case i will use minikube as a local Kubernetes so:

* Install Kubectl tool https://kubernetes.io/docs/tasks/tools/install-kubectl-windows/ Is a command line tool for Kubernetes
* Check your docker images in your local repo (take note of your image name and tag to deploy in kubernetes) i.e: the email service
![image](https://user-images.githubusercontent.com/16137987/174675321-affa7089-0528-440b-b906-a825121a4e4e.png)
## Minikube Instalation ##
### Requisites ###
![image](https://user-images.githubusercontent.com/16137987/174675596-f2810176-860d-4d94-a224-478e785b8d94.png)

* Run this in a command terminal `winget install minikube`. Wail till the instalation finished

* Start minikube with the command `minikube start`

* Create a deployment.yml file with the necessary information for the docker image you are going to run in a kubernetes pod:

``` 
apiVersion: v1 # Kubernetes API version
kind: Service # Kubernetes resource kind we are creating
metadata: # Metadata of the resource kind we are creating
  name: email-service
spec:
  selector:
    app: email-service
  ports:
    - protocol: "TCP"
      port: 8087 # The port that the service is running on in the cluster
      targetPort: 8087 # The port exposed by the service
  type: LoadBalancer # type of the service. LoadBalancer indicates that our service will be external.
---
apiVersion: apps/v1
kind: Deployment # Kubernetes resource kind we are creating
metadata:
  name: email-service
spec:
  selector:
    matchLabels:
      app: email-service
  replicas: 2 # Number of replicas that will be created for this deployment
  template:
    metadata:
      labels:
        app: email-service
    spec:
      containers:
        - name: adidas/email-service
          image: adidas/email-service:latest # Image that will be used to containers in the cluster
          imagePullPolicy: IfNotPresent
          ports:
            - containerPort: 8087 
```

* In the same location of thsi file, run this command `kubectl run email-service --image=adidas/email-service:latest --image-pull-policy=Never`
this command specifies the name of the pod to be created, the image what you are going to use and the policy for kubernetes to handle the image

# Image Pull Policy TIP #
If imagePullPolicy is set to Always, Kubernetes will always pull the image from the Repository. With IfNotPresent, Kubernetes will only pull the image when it does not already exist on the node. While with imagePullPolicy set to Never, Kubernetes will never pull the image

* Now, after running the kubectl run command, just execute this command and you will see your pod running `kubectl get pods`
![image](https://user-images.githubusercontent.com/16137987/174677095-f0744cbe-c3ba-47ee-a922-7896c278bb99.png)


