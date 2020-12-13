# Going Further with JPA

## Part 1: Queries
In this exercise you will be creating some JPL queries.

1.	Open the TestCompactDiscs class and add code to do the following:
    
    a.	Retrieve all CDs where the artist name starts with the letter ‘S’.

    b.	Retrieve the number of CDs.

    c.	Retrieve all CDs in a list in alphabetic order by title.

    d.	Retrieve all the tracks by the Spice Girls.

    e.	All tracks names and the album name for album with id of 16.

    f.	Create a parameterized query that will allow you to specify a title and the matching CDs will be returned.

2.	Test your examples, and review the generated SQL that is output by Log4J.


## Part 2: Understanding Detached Objects
In this exercise, you will see for yourself how the lifecycle of our objects can affect how your applications behave.

### Modifying Object Properties
1.	In your existing project, create a new Java class with a main method called LifecycleTest.

2.	Add the code required to begin a new transaction from an entity manager using the persistence context created earlier.

3.	Add a few blank lines and then add the code to commit the transaction and close the entity manager.

4.	In the gap between the blank lines, retrieve CD number 12 and then change the title to something else simply using the setTitle method on the CompactDisc. 

5.	Run the code and then query the database using the MySQL client to see if it has changed. 

You will see from the console that when you ran the code an update was run against the database and the value in the table has changed. This was because the object was in a  managed state.

6.	Now move the call to setTitle to below the closing of the entity manager. This will not now update the database. You can try it if you like to see it for yourself. Run the code, and the database will not change. After the entity manager is closed the object becomes detatched.

7.	After the code closing the entity manager, open a new entity manager and get a new transaction.

8.	Now merge your modified CompactDisc object and close the new entity manager and transaction. See if the database has changed. You will now see that it has. This is because a detatched entity has become managed again.

