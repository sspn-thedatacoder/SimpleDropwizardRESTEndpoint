# SimpleDropwizardRESTEndpoint

Notes  
Dropwizard (Jersey, Jackson, Jetty, SL4J, Logback) example with simple REST API  
Used the Maven Archetype quickstart project with Eclipse Neon  
Uses the shade plugin to package Jetty as an embedded web server as part of a fat JAR  


Workspaces are typically created in documents folder on Mac  
mvn -e -X clean install (Gives full debug information of why the JAR is not generated) 
.m2 is mavens local repository on your machine (typically located at ~ or home folder of the user) 
Got LOC Bad signature error (It was resolved by cleaning up all the content of the .m2 folder)  


Go to your project folder where the pom.xml resides and run  
mvn -e -X clean install (Rather than using Eclipse build use this for debugging)  


Jetty server can be started from command line using java -jar DropWizardTest-0.0.1.jar  server
