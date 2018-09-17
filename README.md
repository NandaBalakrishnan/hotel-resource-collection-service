# hotel-resource-collection-service
## Prerequisites
 * Run the my-hotel-service project available at https://github.com/NandaBalakrishnan/my-hotel-service.git in the same machine where this project to be deployed or deploy it anywhere and configure the url host in appliction.proprties
 * Run the hotel-resource-collection-service project available at https://github.com/NandaBalakrishnan/hotel-resource-collection-service.git  in the same machine where this project to be deployed or deploy it anywhere and configure the url host in appliction.proprties
 * Zookeeper setup in on premise
    * Default Zookeeper port in zoo.cfg file (Default port 2181).
    * Run Zookeeper by opening a new cmd and type zkserver, Zookeeper is up and running on port 2181!
 * kafka setup in on premise
    *  If your Zookeeper is running on some other machine or cluster you can edit “zookeeper.connect:2181” to your custom IP and port. For this demo we are using same machine so no need to change. 
    Also Kafka port & broker.id are configurable in this file. Leave other settings as it is.
    Your Kafka will run on default port 9092 & connect to zookeeper’s default port which is 2181.
    * Go to your Kafka installation directory C:\kafka_2.11-2.0.0
    * Now type .\bin\windows\kafka-server-start.bat .\config\server.properties and press Enter , everything wnet fine, kafka is up and running.
    * creating topics
      * kafka-topics.bat --create --zookeeper localhost:2181 --replication-factor 1 --partitions 1 --topic test
 * Elastic search (ELK) work instruction for windows os
   * download zip file for elastic search, kibana and logstash 
   * unzip all files and set environmental variable for above tools 
 * Elastic search
   * navigate to C:\elasticsearch-6.4.0 and start elasticsearch.bat using this cmd .\bin\elasticsearch.bat
   * it will be up and running in "http://localhost:9200"
 * kibana 
   * navigate to kibana installtion folder (C:\kibana-6.4.0-windows-x86_64\bin)and configure the elasticsearch.url: "http://localhost:9200" in kibana.yml file 
   * start the kibana.bat file then it will be up and running 
 * logstash
   * navigate to logstash installtion folder(C:\logstash-6.4.0\) and configure the logstash-sample.conf by setting the input, output amnd filter as per the requirements
   * run the logstash by using the cmd .\bin\logstash -f .\config\logstash-sample.conf
    
## Deployment Instructions    
1) Replace the credentials of my-hotel-service in the application.properties file
2) Configure the KAFKA bootstrap-servers,consumer-group-id and kafka topic in application properties
3) Configure server port in application properties.
4) From command prompt navigate to the folder where the pom.xml file located
5) Run the command mvn clean package
6) Run the mvn install will create the jar under ./target/**.jar 
7) Jar will be created in the folder ./target/**.jar
8) Run the jar by the command java -jar target/**.jar
9) In case of IDE, run the project as spring boot app


# Configuration
 * port no : 8086 (configurable in application.properties)    
 * Basic Authentication: username and password can be configurable.
 * authorization header is Basic (base64encode(username:password)

## This API exposes the following endpoints

## POST  /hotels/hotel

Description : This will send a hotel with its resource usage

Required Headers : Accept = "application/json" Authorization = "Basic(base64encode(username:password)" Content-Type = "application/json"

Request Body :{
		"hotelId": 9299,
		"hotelName": "Gateway",
		"electricityUsage": 3.8,
		"electricityUnit": "kWH",
		"waterUsage": 5.5,
		"waterUnit": "L",
		"waste": 30.4,
		"wasteUnit": "kg"
	}

## static assets
  * json file 
     * It contains sample hotel resource usage.
     * command line runner in ThgHotelApplication class will read the static assets(json file)and send the data to kafka topic provided in application.properties
 
## ELK Screenshots
 *  Please find the ELK output screenshots in the ELK_Dashboard_screenshots.docx 

## logstash configuration File
 * please find the logstash configuration file in the logstash-sample.txt
 
   
