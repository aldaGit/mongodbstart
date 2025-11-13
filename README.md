## Useful ressources for working with MongoDB (SE-1)
(Prof. Dr. Sascha Alda)

In this readme, you find various useful links especially for working with MongoDB together with the programming language Java. 


### Prerequisites

 Access to a **MongoDB** server is necessary. If you don't have one already, you should download the community server at:
 https://www.mongodb.com/try/download/community

or (even better) here:

https://www.mongodb.com/docs/manual/administration/install-community/

Community Version 4.2 and 7.0 (latest release) work fine with our examples.

For the SE-1 semester project, you can our MongoDB in our SEPP server environment. Look at the class
MongoDBControllerImpl in package org.hbrs.mongodb for further details. The DB can be used by using 
the interface MongoDBControllerInterface. See the Junit test for an example.
 
### Further nice links for supporting MongoDB
 
A fully comprehensive MongoDB Tutorial for Java:<br />
https://www.mongodb.com/docs/drivers/java/sync/current/quick-start/
 
A nice tutorial for MongoDB with a nice introduction and installation guidelines:<br />
https://www.baeldung.com/java-mongodb

A good overview how to implement queries and insert-operations in MongoDB can be found here: <br />
https://www.mongodb.com/docs/manual/crud/

 SQL to MongoDB Mapping Chart – another nice page :-!!<br />
 https://docs.mongodb.com/manual/reference/sql-comparison/
 
 There are obviously many tools for the administration of a MongoDB, but I personally like this one, since you find a lot of nice examples on how to define queries. Also, examples for JavaScript programs are given and well documented!<br /> 
 https://nosqlbooster.com/downloads
 
 One of the most mature tools for maintaining data in a MongoDB database is the MongoDB compass that can be downloaded for free (commercial version available):<br /> 
 https://www.mongodb.com/products/compass
 
 Please keep in mind that MongoDB does not possess a JOIN-operator. Instead, a lookup-Operator can be used to aggregate two collections. Here you find a nice demo with explanations:<br /> 
 https://www.stackchief.com/tutorials/$lookup%20Examples%20%7C%20MongoDB
 
 In general, be aware that there are many solutions for defining a concrete query!
 
 A simplified programming model to use MongoDB in Java is given by the platform Spring Boot, which simplifies the programming effort dramatically:<br />
 https://spring.io/guides/gs/accessing-data-mongodb/
 
 You need a CLI application? Here is another cool SpringBoot library that eases the implementation of an CLI:<br />
 https://spring.io/projects/spring-shell <br />
 https://www.baeldung.com/spring-shell-cli <br />

New in Junit? Check out this page: <br />
https://www.baeldung.com/junit-5
 

### Support
In some versions, the Java compiler from a fresh Maven project is set version 1.5, which will make the new version obsolete. You have to adjust the compiler level:<br />
https://stackoverflow.com/questions/30690295/removing-warning-messages-on-intellij-idea-while-building-java-project

### IDE
We recommend installing and using the IDE IntelliJ. As a member of the University you can register yourself easily in order to obtain the Ultimate-Version, which is a fully equipped version with the latest features. The necessary key expires after one year, but can easily be refreshed afterwards. <br>
https://www.jetbrains.com/idea/
