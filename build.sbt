name := """play2torial"""

version := "1.0-SNAPSHOT"

lazy val root = (project in file(".")).enablePlugins(PlayJava)

scalaVersion := "2.11.1"

libraryDependencies ++= Seq(
  javaJdbc,
  cache,
  javaWs,
  javaJpa,
  "com.h2database" %            "h2" % "1.4.181",
  "mysql" %                     "mysql-connector-java" % "5.1.39",
  "org.springframework" %       "spring-context" % "4.2.6.RELEASE",
  "org.springframework" %       "spring-orm" % "4.2.6.RELEASE",
  "org.springframework" %       "spring-aop" % "4.2.6.RELEASE",
  "org.springframework.data" %  "spring-data-commons" % "1.12.1.RELEASE",
  "org.springframework.data" %  "spring-data-jpa" % "1.10.1.RELEASE",
  "org.springframework" %       "spring-test" % "4.2.6.RELEASE",
  "org.aspectj" %               "aspectjrt" % "1.8.7",
  "org.aspectj" %               "aspectjweaver" % "1.8.7",
  "org.hibernate" %             "hibernate-entitymanager" % "5.1.0.Final",
  "javax.inject" %              "javax.inject" % "1",
  "org.webjars" %               "jquery" % "1.11.2",
  "org.webjars" %               "bootstrap" % "3.3.4",
  "org.webjars" %               "font-awesome" % "4.2.0"
)
