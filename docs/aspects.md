# Aspect Oriented Programming
## Aims

In this exercise you will create some aspects, pointcuts, and advices. You will also see how XML can be used to configure your beans as well in this exercise.

## Create a new Project

1.	Create a new Maven Project called AOP as you have been doing with the following properties:

`Group ID`	com.conygre.training

`Artifact ID`	AOP

`Project Name`	AOP

2. Add the following dependencies to your project:

```
<dependencies>
    <dependency>
      <groupId>org.springframework</groupId>
      <artifactId>spring-aop</artifactId>
      <version>3.0.5.RELEASE</version>
      <scope>compile</scope>
    </dependency>
    <dependency>
      <groupId>org.springframework</groupId>
      <artifactId>spring-beans</artifactId>
      <version>3.0.5.RELEASE</version>
      <scope>compile</scope>
    </dependency>
    <dependency>
      <groupId>org.springframework</groupId>
      <artifactId>spring-context</artifactId>
      <version>3.0.5.RELEASE</version>
      <scope>compile</scope>
    </dependency>
    <dependency>
      <groupId>org.springframework</groupId>
      <artifactId>spring-core</artifactId>
      <version>3.0.5.RELEASE</version>
      <scope>compile</scope>
    </dependency>
    <dependency>
    	<groupId>org.springframework</groupId>
    	<artifactId>spring-aspects</artifactId>
    	<version>3.0.5.RELEASE</version>
    </dependency>
    <dependency>
    	<groupId>org.aspectj</groupId>
    	<artifactId>aspectjrt</artifactId>
    	<version>1.6.10</version>
    	<type>jar</type>
    	<scope>compile</scope>
    </dependency>
    <dependency>
    	<groupId>org.aspectj</groupId>
    	<artifactId>aspectjweaver</artifactId>
    	<version>1.6.10</version>
    </dependency>
  </dependencies>
```

## Creating the Configured bean

1.	Using Windows Explorer, drag the <LAB_HOME>\labs\aop\com folder into your AOP projects src/main/java directory.

2.	Review the BarTender class in the in the package com.conygre.training.aop. You will be creating advice for this class.

3.	In the src/main/resources folder, create a new file called beans.xml. At the New Spring Definition File dialog where it prompts you for various namespaces, you will need to select the aop namespace.

4.	Configure a bean called barTender to be an instance of this class.

## Create an Aspect with Pointcuts and Advices

1.	Create a new class called com.conygre.training.aspects.BarTenderAspect.

2.	Annotate the class as @Aspect.

3.	Using the @Pointcut annotation, define 3 pointcuts:

    a.	For methods called pullPint, create a pointcut called pullingPints.

    b.	For methods containing the word makeHotCoffee, create a pointcut called hotDrinks.

    c.	For methods starting with the word serveWhiskey, create a pointcut called spirits.

4.	Define the following advice methods that will output simple Strings to the console:
    a.	When a pint is pulled, check the lines afterwards.

    b.	When a hot drink is served, boil the water first, and warn the customer it’s hot afterwards.

    c.	When a spirit is served, put ice in first.

## Create Test Harness
1.	Create a class called TestAOP that has a main method.

2.	In the method, look up your Spring bean (using the IBarTender interface as the variable type) and then try calling your bartender methods. Are the advices run? If not, why not?

3.	To enable the Advice to run, we need to add a couple of extra entries into beans.xml. One to enable the annotations to work, and then another to configure our aspect as a managed bean. You will notice various arrows appearing in your Eclipse tool at the relevant lines of code and entries in the beans.xml informing you that aspects are now set to run on these methods.

```
<bean name="barTenderAspect" class="com.conygre.training.aspects.BarTenderAspect"/>

<aop:aspectj-autoproxy/>
```

4.	Now run the test again and you will see the aspects being invoked.

## Optional: Experiment by creating your own aspects

If you have finished and there is time, feel free to experiment with your own additional aspects. For example you could try aspects that fire on the throwing of exceptions, or you could define more complex pointcuts.

