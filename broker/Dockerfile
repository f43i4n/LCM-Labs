FROM maven

WORKDIR /root

COPY KEYS .

COPY apache-activemq-5.14.1-bin.tar.gz.asc .

RUN curl -o apache-activemq-5.14.1-bin.tar.gz -L 'http://www.apache.org/dyn/closer.cgi?filename=/activemq/5.14.1/apache-activemq-5.14.1-bin.tar.gz&action=download'

RUN gpg --import KEYS

RUN gpg --verify apache-activemq-5.14.1-bin.tar.gz.asc

RUN tar -xzf apache-activemq-5.14.1-bin.tar.gz

COPY conf conf

COPY routes routes

WORKDIR routes

RUN mvn package

RUN cp target/*.jar ../apache-activemq-5.14.1/lib/

WORKDIR /root/apache-activemq-5.14.1

EXPOSE 61616

CMD bin/activemq console xbean:../conf/activemq-demo.xml

