# 🍃 Spring Framework Training Course

**Master Enterprise Spring & Spring Boot Development**

[![Spring Boot](https://img.shields.io/badge/Spring%20Boot-3.x-brightgreen.svg)](https://spring.io/projects/spring-boot)
[![Java](https://img.shields.io/badge/Java-17%2B-orange.svg)](https://openjdk.org/)
[![MongoDB](https://img.shields.io/badge/MongoDB-6.x-green.svg)](https://www.mongodb.com/)
[![MySQL](https://img.shields.io/badge/MySQL-8.x-blue.svg)](https://www.mysql.com/)

Welcome to the comprehensive Spring Framework training repository! This collection contains hands-on demonstrations, practical exercises, and complete solutions used in **Watchelm's Professional Spring Training Courses**.

## 🎯 About This Course

This repository provides everything you need to master enterprise Spring development, from foundational concepts to advanced Spring Boot applications. Whether you're building RESTful APIs, working with databases, or implementing microservices, these materials will guide you through real-world scenarios.

### 🏆 Professional Training Available

**Ready to accelerate your Spring expertise?** 

Join our instructor-led training course: **[Building Enterprise SpringBoot Applications](https://watchelm.com/course/CONSPRING/Building%20Enterprise%20SpringBoot%20Applications)**

✨ **What you'll get:**
- Expert-led instruction from industry professionals
- Hands-on coding exercises and real-world projects  
- Certificate of completion
- Ongoing support and resources
- Small class sizes for personalized attention

---

## 📚 Course Content Overview

### 🎯 Core Topics Covered

- **Spring Fundamentals** - Dependency Injection, IoC Container, Configuration
- **Spring Boot** - Auto-configuration, Starters, Actuator, DevTools
- **Data Access** - JPA, Hibernate, Spring Data, Repositories
- **Web Development** - REST APIs, Spring MVC, Error Handling
- **Database Integration** - MySQL, MongoDB, Connection Pooling
- **Security** - Authentication, Authorization, JWT
- **Testing** - Unit Testing, Integration Testing, Test Slices
- **Advanced Topics** - AOP, JMS, Remoting, WebServices
- **Containerization** - Docker deployment strategies

---

## 🚀 Getting Started

### Prerequisites
- Java 17 or higher
- Maven 3.6+ or Gradle 7+
- Your favorite IDE (IntelliJ IDEA, Eclipse, VS Code)
- Docker (for containerization exercises)

### Quick Setup
```bash
# Clone this repository
git clone https://github.com/nicktodd/spring-course.git
cd spring-course

# Choose your preferred build tool and database
# Option 1: MySQL-based exercises
cd Labs/BasicSpring
mvn clean compile

# Option 2: MongoDB-based exercises  
cd Labs/CompactDiscReadyForDocker
./gradlew build
```

---

## 📁 Repository Structure

### 🧪 **Labs/** - Hands-On Exercises
Practice your skills with progressive exercises:

| Exercise | Description | Technologies |
|----------|-------------|--------------|
| **BasicSpring** | Foundation concepts - DI, IoC, Configuration | Spring Core, Maven/Gradle |
| **CompactDiscReadyForDocker** | Production-ready app with containerization | Spring Boot, Docker, JPA |
| **aop/** | Aspect-Oriented Programming examples | Spring AOP, AspectJ |
| **hibernate/** | Direct Hibernate integration | Hibernate, JPA |
| **mysql/** | Database schema and setup scripts | MySQL, SQL |
| **webservices/** | SOAP and REST web services | JAX-WS, REST |

### ✅ **Solutions/** - Complete Implementations
Reference implementations for all exercises:

| Solution | Focus Area | Key Features |
|----------|------------|--------------|
| **BasicSpring** | Core Spring concepts | Configuration, Bean management |
| **CompactDiscDaoWithJpa** | JPA integration | Repositories, Transactions |
| **CompactDiscDaoSpringDataMongo** | MongoDB with Spring Data | NoSQL, Document databases |
| **CompactDiscDaoWithRestAndBoot** | RESTful APIs | HTTP endpoints, JSON handling |
| **AOPSolution** | Cross-cutting concerns | Logging, Security aspects |

### 🎓 **AdditionalDemos/** - Advanced Examples
Extended demonstrations of enterprise patterns:

| Demo | Technology Stack | Use Case |
|------|------------------|----------|
| **BasicRESTApplication** | Spring Boot, REST | API development |
| **CompactDiscDaoBootWithSecurity** | Spring Security, JWT | Authentication & Authorization |
| **SpringJMS** | JMS, Message Queues | Asynchronous messaging |
| **SpringRemotingHessian** | Hessian, RMI | Distributed systems |
| **SocketsExample** | WebSockets | Real-time communication |

### 📖 **docs/** - Learning Resources
Comprehensive guides and references:

- **[GettingStartedWithSpring.md](docs/GettingStartedWithSpring.md)** - Your first Spring application
- **[spring-jpa.md](docs/spring-jpa.md)** - Database integration with JPA
- **[springboot-jpa.md](docs/springboot-jpa.md)** - Spring Boot data access
- **[aspects.md](docs/aspects.md)** - Aspect-Oriented Programming guide
- **[WorkingWithMongodb.md](docs/WorkingWithMongodb.md)** - NoSQL database integration

---

## 🛠 Two Learning Paths

### Path 1: MySQL + Spring Boot
**Perfect for:** Traditional enterprise applications, relational data modeling

**Key Projects:**
- CompactDisc catalog with MySQL backend
- RESTful API development  
- JPA repository patterns
- Transaction management

### Path 2: MongoDB + Spring Data
**Perfect for:** Modern web applications, flexible document storage

**Key Projects:**  
- Document-based data modeling
- Spring Data MongoDB repositories
- Aggregation pipelines
- Reactive programming patterns

---

## 🎯 Learning Objectives

By completing this course, you will:

✅ **Master Spring Boot** - Build production-ready applications quickly  
✅ **Implement REST APIs** - Create robust, scalable web services  
✅ **Integrate Databases** - Work confidently with SQL and NoSQL databases  
✅ **Apply Security** - Secure your applications with Spring Security  
✅ **Write Tests** - Implement comprehensive testing strategies  
✅ **Deploy Applications** - Containerize and deploy with Docker  
✅ **Follow Best Practices** - Apply enterprise development patterns  

---

## 🚀 Start Your Journey

1. **Explore the Labs** - Start with `BasicSpring` to understand core concepts
2. **Follow the Documentation** - Each exercise includes detailed instructions
3. **Run the Solutions** - Compare your implementation with provided solutions
4. **Experiment** - Modify examples to deepen your understanding
5. **Join Our Training** - Accelerate your learning with expert instruction

---

## 💡 Need Help?

- 📖 **Documentation** - Check the `/docs` folder for detailed guides
- 🎓 **Professional Training** - [Enroll in our instructor-led course](https://watchelm.com/course/CONSPRING/Building%20Enterprise%20SpringBoot%20Applications)
- 🌐 **Watchelm Learning** - Visit [watchelm.com](https://watchelm.com) for more courses

---

## 📝 License

This educational content is provided for learning purposes. Please respect intellectual property rights and use responsibly.

---

**Ready to become a Spring expert? Start coding and join thousands of developers who have mastered Spring with Watchelm Training!** 🚀

