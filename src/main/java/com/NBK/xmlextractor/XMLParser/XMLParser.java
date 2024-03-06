package com.NBK.xmlextractor.XMLParser;

import com.NBK.xmlextractor.XMLConfiguration.XMLConfig;
import org.springframework.stereotype.Component;
import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBException;
import javax.xml.bind.Unmarshaller;
import java.io.File;

/**
 * Parses an XML configuration file and extracts data into a {@link XMLConfig} object.
 */
@Component
public class XMLParser {

    /**
     * Parses an XML configuration file into an {@link XMLConfig} representation.
     *
     * @param filePath The path to the XML configuration file.
     * @return An {@link XMLConfig} object populated with the parsed data.
     * @throws JAXBException If there's an error during the XML parsing process.
     */
    public XMLConfig parseConfig(String filePath) throws JAXBException {
        File configFile = new File(filePath);
        // 1. Create JAXBContext
        JAXBContext jaxbContext = JAXBContext.newInstance(XMLConfig.class);
        // 2. Create Unmarshaller
        Unmarshaller jaxbUnmarshaller = jaxbContext.createUnmarshaller();
        // 3. Unmarshal and Cast
        return (XMLConfig) jaxbUnmarshaller.unmarshal(configFile);
    }
}