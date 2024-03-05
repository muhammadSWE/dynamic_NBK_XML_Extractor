package com.NBK.xmlextractor.XMLParser;


import com.NBK.xmlextractor.XMLConfiguration.Configuration;
import org.springframework.stereotype.Component;

import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBException;
import javax.xml.bind.Unmarshaller;
import java.io.File;

@Component
public class XMLParser {

    public Configuration parseConfig(String filePath) throws JAXBException {
        File configFile = new File(filePath);
        JAXBContext jaxbContext = JAXBContext.newInstance(Configuration.class);
        Unmarshaller jaxbUnmarshaller = jaxbContext.createUnmarshaller();
        return (Configuration) jaxbUnmarshaller.unmarshal(configFile);
    }
}

