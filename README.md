# adidas
Project based on the adidas technical challenge which is based on the following architecture 

![image](https://user-images.githubusercontent.com/16137987/174175103-8b0e47d8-e264-46af-b010-9e1c522c078b.png)

As part of the requirements, the public service is the one that keeps the security based on JWT and Spring Gateway

![AdidasChallenge](https://user-images.githubusercontent.com/16137987/174185152-b4e2c49b-2c07-46f2-8dcb-fa8e1cc19739.png)

# Microservice architecture
This challenge project demonstrates how to organize an application using microservices following a Doman Driven Design architecture (only the Subscription Service has it implemented). 
Adding a new microservice is easy, as the discovery server will automatically discover new services running on the cluster.

# Service discovery
This project contains one discovery service using Netflix Eureka. Every Micro service will register into the Eureka server and Eureka server knows all the client applications running on each port and IP address

# API gateway
Using this strategy, the subscription service will be protected as being filtered by the gateway. This gateway uses JSON Web Tokens to ensure that the end-user would route a subscription request trough the gateway only by using a token given by the authentication service. The public gateway service will automatically discover and route API requests to the service that owns the route. This proxying technique is equally helpful when developing user interfaces, as the full API of the platform is available through its own host as a proxy.


