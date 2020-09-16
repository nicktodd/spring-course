To build the fat jar for the project, run `gradlew clean bootJar`

To build the docker container run

```docker build -t cd/cd-server:1.0.0 .```

To run the mongo container, run

```docker run -d --name mymongo mongo```

To run the Java container, run

``` docker run -d -p 8080:8080 --link mymongo:mymongo cd/cd-server:1.0.0```

Check in the Browser using http://localhost:8080


## Using Docker Compose
To save running all those commands separately, you can use Docker compose which is a way to do it all in one config file.

To test:

```docker-compose up```




