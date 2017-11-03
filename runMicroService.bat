call mvn clean
call mvn package

cd target
java -jar service-1.0-SNAPSHOT-spring-boot.jar