# Introduction to JPA (Java Persistence API)

- **Definition**: JPA is a specification for accessing, persisting, and managing data between Java objects/classes and a relational database.
- **Core Concepts**:
  - **Entity**: A lightweight, persistent domain object.
  - **Entity Manager**: Interface used to interact with the persistence context.
  - **Persistence Unit**: A set of entity classes and configuration that are managed by the entity manager.

## Setting Up the Environment
- **Prerequisites**:
  - Java Development Kit (JDK) 17 or later
  - Maven 3.6.0 or later
  - Eclipse IDE for Enterprise Java Developers
  - Database (e.g., MySQL, PostgreSQL)

## Project Structure
- **Maven Project Structure**:
  ```
  jpa-eclipselink-demo/
  ├── src/
  │   ├── main/
  │   │   ├── java/
  │   │   │   └── com/
  │   │   │       └── mahendra/
  │   │   │           └── model/
  │   │   │               ├── Main.java
  │   │   │               └── MyEntity.java
  │   │   └── resources/
  │   │       └── META-INF/
  │   │           └── persistence.xml
  └── pom.xml
  ```

#### Creating the Maven Project
1. **Generate the Maven Project**:
   ```bash
   mvn archetype:generate -DgroupId=com.mahendra -DartifactId=jpa-eclipselink-demo -DarchetypeArtifactId=maven-archetype-quickstart -DinteractiveMode=false
   ```

2. **Add Dependencies in `pom.xml`**:
   ```xml
   <dependencies>
       <dependency>
           <groupId>javax.persistence</groupId>
           <artifactId>javax.persistence-api</artifactId>
           <version>2.2</version>
       </dependency>
       <dependency>
           <groupId>org.eclipse.persistence</groupId>
           <artifactId>eclipselink</artifactId>
           <version>2.7.7</version>
       </dependency>
       <dependency>
           <groupId>org.slf4j</groupId>
           <artifactId>slf4j-api</artifactId>
           <version>1.7.30</version>
       </dependency>
       <dependency>
           <groupId>org.slf4j</groupId>
           <artifactId>slf4j-simple</artifactId>
           <version>1.7.30</version>
       </dependency>
   </dependencies>

   <build>
       <plugins>
           <plugin>
               <groupId>org.apache.maven.plugins</groupId>
               <artifactId>maven-compiler-plugin</artifactId>
               <version>3.8.1</version>
               <configuration>
                   <source>17</source>
                   <target>17</target>
               </configuration>
           </plugin>
       </plugins>
   </build>
   ```

#### Configuring JPA with EclipseLink

1. **Persistence Configuration**:
   - `persistence.xml` in `src/main/resources/META-INF/`:
     ```xml
     <?xml version="1.0" encoding="UTF-8"?>
     <persistence xmlns="http://xmlns.jcp.org/xml/ns/persistence" version="2.1">
         <persistence-unit name="jpa-eclipselink-demo" transaction-type="RESOURCE_LOCAL">
             <provider>org.eclipse.persistence.jpa.PersistenceProvider</provider>
             <class>com.mahendra.model.MyEntity</class>
             <properties>
                 <property name="javax.persistence.jdbc.driver" value="com.mysql.cj.jdbc.Driver"/>
                 <property name="javax.persistence.jdbc.url" value="jdbc:mysql://localhost:3306/mydatabase"/>
                 <property name="javax.persistence.jdbc.user" value="root"/>
                 <property name="javax.persistence.jdbc.password" value="password"/>
                 <property name="eclipselink.logging.level" value="FINE"/>
                 <property name="eclipselink.ddl-generation" value="create-tables"/>
             </properties>
         </persistence-unit>
     </persistence>
     ```

2. **Creating Entity Class**:
   - `MyEntity.java`:
     ```java
     package com.mahendra.model;

     import javax.persistence.Entity;
     import javax.persistence.GeneratedValue;
     import javax.persistence.GenerationType;
     import javax.persistence.Id;

     @Entity
     public class MyEntity {

         @Id
         @GeneratedValue(strategy = GenerationType.IDENTITY)
         private Long id;
         private String name;

         // Getters and Setters
         public Long getId() {
             return id;
         }

         public void setId(Long id) {
             this.id = id;
         }

         public String getName() {
             return name;
         }

         public void setName(String name) {
             this.name = name;
         }
     }
     ```

3. **Main Class to Interact with JPA**:
   - `Main.java`:
     ```java
     package com.mahendra.model;

     import

     javax.persistence.EntityManager;
     import javax.persistence.EntityManagerFactory;
     import javax.persistence.Persistence;

     public class Main {

         public static void main(String[] args) {
             EntityManagerFactory emf = Persistence.createEntityManagerFactory("jpa-eclipselink-demo");
             EntityManager em = emf.createEntityManager();

             em.getTransaction().begin();

             MyEntity entity = new MyEntity();
             entity.setName("Sample Name");

             em.persist(entity);
             em.getTransaction().commit();

             em.close();
             emf.close();
         }
     }
     ```

#### Running the Application in Eclipse
1. **Import Maven Project**:
   - Open Eclipse IDE.
   - File -> Import -> Existing Maven Projects -> Select the project directory.

2. **Build the Project**:
   - Right-click on the project -> Run As -> Maven build... -> Goals: `clean install` -> Run.

3. **Run the Main Class**:
   - Right-click on `Main.java` -> Run As -> Java Application.

#### Advanced Topics

1. **JPQL (Java Persistence Query Language)**:
   - Example Query:
     ```java
     List<MyEntity> entities = em.createQuery("SELECT e FROM MyEntity e WHERE e.name = :name", MyEntity.class)
                                  .setParameter("name", "Sample Name")
                                  .getResultList();
     ```

2. **Criteria API**:
   - Example Query:
     ```java
     CriteriaBuilder cb = em.getCriteriaBuilder();
     CriteriaQuery<MyEntity> cq = cb.createQuery(MyEntity.class);
     Root<MyEntity> root = cq.from(MyEntity.class);
     cq.select(root).where(cb.equal(root.get("name"), "Sample Name"));

     List<MyEntity> entities = em.createQuery(cq).getResultList();
     ```

3. **Transactions and Concurrency**:
   - Managing Transactions:
     ```java
     em.getTransaction().begin();
     // perform operations
     em.getTransaction().commit();
     ```

4. **Entity Relationships**:
   - One-to-One:
     ```java
     @OneToOne
     private MyOtherEntity otherEntity;
     ```
   - One-to-Many:
     ```java
     @OneToMany(mappedBy = "myEntity")
     private List<MyOtherEntity> otherEntities;
     ```
   - Many-to-Many:
     ```java
     @ManyToMany
     private List<MyOtherEntity> otherEntities;
     ```


## **Further Reading**: Suggested materials for advanced learning.
  - Official [JPA Documentation](https://docs.oracle.com/javaee/7/tutorial/persistence-intro.htm)
  - EclipseLink [User Guide](https://eclipse.dev/eclipselink/documentation/2.7/concepts/toc.htm)
  - Pro JPA 2 in Java EE 8 by Mike Keith and Merrick Schincariol
