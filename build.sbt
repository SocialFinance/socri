name := """play2torial"""

version := "1.0-SNAPSHOT"

lazy val root = (project in file(".")).enablePlugins(PlayJava)

scalaVersion := "2.11.1"

libraryDependencies ++= Seq(
  javaJdbc,
  cache,
  javaWs,
  javaJpa,
  "org.springframework" % "spring-context" % "4.1.1.RELEASE",
  "org.springframework" % "spring-orm" % "4.1.1.RELEASE",
  "org.springframework" % "spring-aop" % "4.1.1.RELEASE",
  "org.aspectj" % "aspectjrt" % "1.8.7",
  "org.aspectj" % "aspectjweaver" % "1.8.7",
  "mysql" % "mysql-connector-java" % "5.1.39",
  "org.hibernate" % "hibernate-entitymanager" % "5.1.0.Final",
  "javax.inject" % "javax.inject" % "1",
  "org.webjars" % "jquery" % "1.11.2",
  "org.webjars" % "bootstrap" % "3.3.4"
)
