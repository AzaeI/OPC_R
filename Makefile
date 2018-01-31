build :
	mvn clean compile assembly:single
	java -jar target/OPC-jar-with-dependencies.jar
