package neu.lab.autoexe.abandon;

import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;

public class PomWriter {
	PrintWriter printer;
	public PomWriter(String pomPath) throws IOException {
		 printer = new PrintWriter(new BufferedWriter(new FileWriter(pomPath)));
	}
	public void writePom(String groupId,String artifactId,String version){
		
		printer.println("<project xmlns=\"http://maven.apache.org/POM/4.0.0\" xmlns:xsi=\"http://www.w3.org/2001/XMLSchema-instance\"");
		printer.println("	xsi:schemaLocation=\"http://maven.apache.org/POM/4.0.0 http://maven.apache.org/xsd/maven-4.0.0.xsd\">");
		printer.println("	<modelVersion>4.0.0</modelVersion>");
		printer.println("	<groupId>neu.lab</groupId>");
		printer.println("	<artifactId>findBug</artifactId>");
		printer.println("	<version>0.0.1-SNAPSHOT</version>");
		printer.println("	<packaging>jar</packaging>");
		printer.println("	<name>findBug</name>");
		printer.println("	<url>http://maven.apache.org</url>");
		printer.println("	<properties>");
		printer.println("		<project.build.sourceEncoding>UTF-8</project.build.sourceEncoding>");
		printer.println("	</properties>");
		printer.println("	<dependencies>");
		printer.println("	</dependencies>");

		printer.println("</project>");
		printer.close();
	}
	public void printDependency(String groupId,String artifactId,String version) {
//	<dependency>
//		<groupId>org.slf4j</groupId>
//		<artifactId>slf4j-jdk14</artifactId>
//		<version>1.7.25</version>
//	</dependency>	
	printer.println("		<dependency>");
	printer.println("			<groupId>"+groupId+"</groupId>");
	printer.println("		    <artifactId>"+artifactId+"</artifactId>");
	printer.println("		    <version>"+version+"</version>");
	printer.println("		</dependency>");
	}
}
