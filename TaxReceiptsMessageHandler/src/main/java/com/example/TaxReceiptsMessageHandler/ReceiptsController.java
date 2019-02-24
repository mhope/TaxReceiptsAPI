package com.example.TaxReceiptsMessageHandler;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.ResponseBody;
import org.xml.sax.SAXException;

import javax.xml.parsers.ParserConfigurationException;
import javax.xml.soap.SOAPException;
import javax.xml.transform.TransformerException;
import javax.xml.xpath.XPathExpressionException;
import java.io.IOException;


@Controller
public class ReceiptsController {

    static final String APPLICATION_SOAP_XML = "application/soap+xml";
    private MessageTransformer messageTransformer;
    private MessageValidator messageValidator;

    @Autowired
    public ReceiptsController(MessageTransformer messageTransformer, MessageValidator messageValidator) {
        this.messageTransformer = messageTransformer;
        this.messageValidator = messageValidator;
    }

    Logger logger = LoggerFactory.getLogger(ReceiptsController.class);

    @PostMapping(path = "/transform",
            consumes = MediaType.TEXT_XML_VALUE,
            produces = MediaType.TEXT_XML_VALUE)
    @ResponseBody
    public String transform(@RequestBody String xmlbody) throws TransformerException {
        logger.info(xmlbody);
        return messageTransformer.transform(xmlbody);
    }

    @PostMapping(path = "/validate",
            consumes = APPLICATION_SOAP_XML,
            produces = APPLICATION_SOAP_XML)
    @ResponseBody
    public String validate(@RequestBody String xmlbody, @RequestHeader HttpHeaders httpHeaders) throws IOException, SOAPException, XPathExpressionException, ParserConfigurationException, SAXException {

        String faultOrOriginalRequest = messageValidator.validate(xmlbody).orElse(xmlbody);
        logger.info(faultOrOriginalRequest);
        return faultOrOriginalRequest;
    }


}
