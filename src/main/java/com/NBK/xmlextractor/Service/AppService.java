package com.NBK.xmlextractor.Service;

import com.NBK.xmlextractor.XMLParser.XMLParser;
import com.NBK.xmlextractor.XMLConfiguration.XMLConfig;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;
import javax.xml.bind.JAXBException;
import java.util.List;
import java.util.Map;

/**
 * Service responsible for parsing XML configuration, executing database queries,
 * and generating the final XML output.
 */
@Component
@Service
public class AppService {

    private final XMLParser xmlParser;
    private final JdbcTemplate jdbcTemplate;

    @Autowired
    public AppService(XMLParser xmlParser, JdbcTemplate jdbcTemplate) {
        this.xmlParser = xmlParser;
        this.jdbcTemplate = jdbcTemplate;
    }

    /**
     * Entry point for XML generation. Parses the configuration, executes queries,
     * and constructs the final XML response.
     *
     * @param filePath Path to the XML configuration file.
     * @return The generated XML output as a String.
     * @throws JAXBException  If there's an error during XML parsing.
     */
    public String generateXmlOutput(String filePath) throws JAXBException {

        XMLConfig config = xmlParser.parseConfig(filePath);

        // Execute Queries
        StringBuilder xmlOutput = new StringBuilder("<ReportFields>");

        for (String queryConfig : config.getQueries()) {
            List<Map<String, Object>> dbResults = jdbcTemplate.queryForList(queryConfig);
            xmlOutput.append(processQueryResults(dbResults)); // Assuming method to generate XML from query results
        }
        xmlOutput.append("</ReportFields>");
        return xmlOutput.toString();
    }

    /**
     * Helper method to format database query results into XML segments.
     *
     * @param dbResults A list of maps, where each map represents a database row.
     * @return  An XML string representing the query results.
     */
    private String processQueryResults(List<Map<String, Object>> dbResults) {
        StringBuilder queryXml = new StringBuilder();

        for (Map<String, Object> row : dbResults) {
            queryXml.append("<ReportField>");
            for (Map.Entry<String, Object> entry : row.entrySet()) {
                queryXml.append("<").append(entry.getKey()).append(">");
                queryXml.append(entry.getValue()).append("</").append(entry.getKey()).append(">");
            }
            queryXml.append("</ReportField>");
        }
        return queryXml.toString();
    }
}