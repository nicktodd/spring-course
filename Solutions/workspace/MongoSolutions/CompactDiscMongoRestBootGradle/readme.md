# Manual Build Instructions Using Gradle

To build the fat jar (a fat jar contains all the libraries as well as your compiled code) for the project, run 

`gradlew clean bootJar`


# Build and Run Using Docker

To build the docker container run

```docker build -t cd/cd-server:1.0.0 .```

To run the mongo container with the name **mymongo**, run

```docker run -d --name mymongo mongo```

To run the Java container and link it to the Mongo container, run

``` docker run -d -p 8080:8080 --link mymongo:mymongo cd/cd-server:1.0.0```

Check in the Browser using http://localhost:8080


## Build and Run Using Docker Compose
To save running all those pesky Docker commands separately, you can use Docker compose which is a way to do it all in one config file.

To test:

```docker-compose up```

Check out the docker-compose file. You can see that it will configure the linking and port exposing for you.


