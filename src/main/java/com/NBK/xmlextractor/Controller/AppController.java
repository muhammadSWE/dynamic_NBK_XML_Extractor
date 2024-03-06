package com.NBK.xmlextractor.Controller;


import com.NBK.xmlextractor.Service.AppService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import javax.xml.bind.JAXBException;

/**
 * REST controller responsible for handling XML generation requests.
 */
@RestController
@RequestMapping("/xml-generator") // Base path for endpoints
public class AppController {
    private final AppService appService;

    @Autowired
    public AppController(AppService appService) {
        this.appService = appService;
    }

    /**
     * Handles GET requests to '/generate', triggers XML generation logic,
     * and returns an XML response.
     *
     * @return ResponseEntity containing the generated XML, with the appropriate content type.
     * @throws JAXBException if there's an error during XML parsing or generation.
     */
    @GetMapping("/generate")
    public ResponseEntity<String> generateXml() throws JAXBException {
        String filePath = "src/main/resources/XMLConfiguration.xml"; // Adjust if needed
        String xmlOutput = appService.generateXmlOutput(filePath);

        return ResponseEntity.ok()
                .contentType(MediaType.APPLICATION_XML)
                .body(xmlOutput);
    }
}