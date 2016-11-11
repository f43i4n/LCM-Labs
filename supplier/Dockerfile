FROM maven:3.3.9-jdk-8-alpine

EXPOSE 8080

VOLUME /tmp

COPY /src  /install/src

COPY /pom.xml /install/pom.xml

RUN cd /install/ &&	mvn install

RUN sh -c 'mv /install/target/supplier-1.0.jar /supplier.jar' && \
	sh -c 'touch /supplier.jar' && \
	rm -rf /install/

ENTRYPOINT ["java","-Djava.security.egd=file:/dev/./urandom","-jar","/supplier.jar"]