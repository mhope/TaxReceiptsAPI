package com.example.TaxReceiptsMessageHandler;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.util.xml.SimpleNamespaceContext;
import org.w3c.dom.Document;
import org.xml.sax.InputSource;
import org.xml.sax.SAXException;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import javax.xml.soap.*;
import javax.xml.xpath.*;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.StringReader;
import java.time.LocalDate;
import java.util.Optional;

@Component
public class MessageValidator {

    Logger logger = LoggerFactory.getLogger(MessageValidator.class);

    private MessageFactory messageFactory;
    private XPathFactory xPathFactory;

    @Autowired
    public MessageValidator(MessageFactory messageFactory, XPathFactory xPathFactory) {
        this.messageFactory = messageFactory;
        this.xPathFactory = xPathFactory;
    }

    Optional<String> validate(String xmlbody) throws XPathExpressionException {
        String reason = null;
        try {
            XPath xpath = getxPath();
            InputSource source = new InputSource(new StringReader(xmlbody));
            Integer year = Integer.valueOf(xpath.evaluate("//p:YearEnd", source));
            if (year > LocalDate.now().getYear()) {
                reason = "Year cannot be in future";
            }
        } catch (XPathExpressionException e) {
            // error in this method, not recoverable
            logger.error(e.getMessage());
            throw e;
        } catch (NumberFormatException e) {
            logger.error(e.getMessage());
            reason = "Year end not a valid number";
        }

        String fault = null;
        try {
            if (reason != null) {
                fault = soapFaultOf(reason);
            }
        } catch (SOAPException | IOException e) {
            logger.error(e.getMessage());
            fault = "Could not build SOAP fault. Reason is " + reason;
        }
        return Optional.ofNullable(fault);
    }

    private XPath getxPath() {
        XPath xpath = xPathFactory.newXPath();
        SimpleNamespaceContext nsContext = new SimpleNamespaceContext();
        nsContext.bindNamespaceUri("p", "http://ws.wso2.org/dataservice");
        nsContext.bindNamespaceUri("soapenv", "http://www.w3.org/2003/05/soap-envelope");
        xpath.setNamespaceContext(nsContext);
        return xpath;
    }

    private String soapFaultOf(String reason) throws SOAPException, IOException {
        SOAPMessage message = messageFactory.createMessage();
        Name code = message.getSOAPPart().getEnvelope().createName("Sender", null, SOAPConstants.URI_NS_SOAP_1_2_ENVELOPE);
        message.getSOAPBody().addFault(code, reason);
        ByteArrayOutputStream out = new ByteArrayOutputStream();
        message.writeTo(out);
        return new String(out.toByteArray());
    }
}
