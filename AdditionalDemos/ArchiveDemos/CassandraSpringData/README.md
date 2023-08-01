# Accessing Cassandra database

An integration example how to access data from Cassandra database using a 
Spring Boot application using Vaadin UI.

## Preparations

[Install and run Cassandra](http://wiki.apache.org/cassandra/GettingStarted) 
locally (or configure the app to connect to a non localhost address). The app 
connects to "mykeyspace". Create it with e.g. following CQL command with cqlsh:

    CREATE KEYSPACE mykeyspace WITH REPLICATION = { 'class' : 'SimpleStrategy', 'replication_factor' : 1 };

## Execute the app

As this is an integration example, you probably want to run this from IDE 
and play with it. Just import the project properly to your favorite IDE 
and execute the main method from Application class. If you are new to Maven, see 
[these instructions](https://vaadin.com/blog/-/blogs/the-maven-essentials-for-the-impatient-developer).

Once the application starts up, a demo table (customer) is created and 
some demo data inserted. A simple Vaadin based UI can be used with browser from
 http://localhost:8080/

