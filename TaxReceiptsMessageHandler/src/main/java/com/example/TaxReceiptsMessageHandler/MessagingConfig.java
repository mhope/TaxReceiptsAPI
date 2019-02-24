package com.example.TaxReceiptsMessageHandler;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.soap.MessageFactory;
import javax.xml.soap.SOAPConstants;
import javax.xml.soap.SOAPException;
import javax.xml.transform.TransformerFactory;
import javax.xml.xpath.XPathFactory;

@Configuration
public class MessagingConfig {

    @Bean
    public TransformerFactory transformerFactory(){
        return TransformerFactory.newInstance();
    }

    @Bean
    public MessageFactory messageFactory() throws SOAPException {
        return MessageFactory.newInstance(SOAPConstants.SOAP_1_2_PROTOCOL);
    }

    @Bean
    public XPathFactory xPathFactory() {
        return XPathFactory.newInstance();
    }

}
