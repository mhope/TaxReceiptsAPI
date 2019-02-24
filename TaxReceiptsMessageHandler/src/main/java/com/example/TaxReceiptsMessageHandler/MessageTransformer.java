package com.example.TaxReceiptsMessageHandler;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import javax.xml.transform.Transformer;
import javax.xml.transform.TransformerException;
import javax.xml.transform.TransformerFactory;
import javax.xml.transform.stream.StreamResult;
import javax.xml.transform.stream.StreamSource;
import java.io.File;
import java.io.StringReader;
import java.io.StringWriter;


@Component
public class MessageTransformer {

    private TransformerFactory transformerFactory;

    @Autowired
    public MessageTransformer(TransformerFactory transformerFactory) {
        this.transformerFactory = transformerFactory;
    }

    String transform(String xmlbody) throws TransformerException {
        File stylesheet = new File(this.getClass().getClassLoader().getResource("templates/receipts.xsl").getPath());
        StreamSource stylesource = new StreamSource(stylesheet);
        StreamSource xmlsource = new StreamSource(new StringReader(xmlbody));
        Transformer transformer = transformerFactory.newTransformer(stylesource);
        StreamResult xmlOutput = new StreamResult(new StringWriter());
        transformer.transform(xmlsource, xmlOutput);

        return xmlOutput.getWriter().toString();

    }
}
