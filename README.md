These instructions will get you a copy of the project up and running on your local machine for development and testing purposes.

Prerequisites 
  1. Setup Rabbit MQ server in your local
  2. Register with twilio free trail account, 
  
Add below Rabbit MQ details in application.properties or application.yml file. 
spring.rabbitmq.host=localhost
spring.rabbitmq.port=5672
spring.rabbitmq.username=guest
spring.rabbitmq.password=guest
jsa.rabbitmq.exchange=message.exchange
jsa.rabbitmq.queue=message.queue

Register with twilio’s free account and note down below details from dashboard and add in application.yml file. 
twilio:
  account_sid: ACd4c2ccf9a7aa966b1d9b5aa668f1c302
  auth_token: XXXXXXXXXXXXXXXXX
  trial_number: +1XXXXXXXXX
spring:
  rabbitmq:
    listener:
      simple:
        retry:
          enabled: true
          initial-interval: 2s
          max-attempts: 5
          max-interval: 10s
          multiplier: 2
          
<dependency>
 			<groupId>com.twilio.sdk</groupId>
 			<artifactId>twilio</artifactId>
 			<version>8.2.0</version>
		</dependency>

Import attached spring boot project as maven project, update maven dependencies and run as spring boot application.
test through Postman or UI app [https://github.com/kspkemp/message-app]    
    curl -X POST \
  http://localhost:8080/message/api/v1/send \
  -H 'cache-control: no-cache' \
  -H 'content-type: application/json' \
  -H 'postman-token: ab62b339-6c03-04de-704d-696aaf743f65' \
  -d '{
	"message":"Message as JSON",
	"phoneNo":"918861964553"
}'

Response: 
{
    "response": "Message Placed in Queue Successfully",
    "message": "success"
}

