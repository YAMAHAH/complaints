module axondemo  {
    requires java.logging;
    requires java.scripting;
    requires java.persistence;
    requires spring.boot;
    requires spring.boot.autoconfigure;
    requires spring.context;
    requires spring.web;
    requires spring.data.jpa;
    requires spring.amqp;
    requires org.axonframework.amqp;
    requires org.axonframework.core;
    requires org.axonframework.spring;
    requires spring.beans;
    requires slf4j.api;
    requires spring.data.commons;
    requires spring.rabbit;
    requires com.rabbitmq.client;
    requires javax.transaction.api;
    requires tomcat.embed.core;
    requires fastjson;
    requires spring.core;
    requires spring.data.rest.core;
}