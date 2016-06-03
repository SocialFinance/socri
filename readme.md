#SoCri
##A social approach to crime
![A funny picture](/public/images/crime1.jpg?raw=true "CRIME ONLINE")

Seriously though, this is just a sample webapp to get hands on practice with Play 2.3, Spring, Hibernate, JPA, and MySQL.

### Requirements
Getting this project up and running requires:
* SBT
* Java 8+
* Scala?
* MySQL
* A computer
* Two cats
* Nosferatu

### Setup
Once done, run these to get started:
```bash
mysql -u root -p < mysql_init.sql
sbt clean compile
```

### Running
```bash
sbt ~run
```

### Tests
```bash
sbt test
```
