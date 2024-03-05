package com.NBK.xmlextractor.Service;



import com.NBK.xmlextractor.XMLConfiguration.Query;
import com.NBK.xmlextractor.XMLParser.XMLParser;
import com.NBK.xmlextractor.XMLConfiguration.Configuration;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;



import javax.xml.bind.JAXBException;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;


@Service
public class AppService {

    private final XMLParser xmlParser;

    @Autowired
    public AppService(XMLParser xmlParser) {
        this.xmlParser = xmlParser;
    }

    public String generateXmlOutput(String filePath) {
        try {
            Configuration config = xmlParser.parseConfig(filePath);
            String jdbcUrl = config.getDatabase().getJDBCURL();

            StringBuilder xmlOutput = new StringBuilder("<data>");

            for (Query queryConfig : config.getQueries()) {
                String sql = queryConfig.getSql();
                List<String> columns = executeQuery(jdbcUrl, sql);
                generateXml(xmlOutput, columns);
            }

            xmlOutput.append("</data>");
            return xmlOutput.toString();
        } catch (JAXBException e) {
            System.out.println("Error parsing XML configuration: " + e.getMessage());
            return "<error>" + e.getMessage() + "</error>";
        }
    }

    private List<String> executeQuery(String jdbcUrl, String sql) {
        List<String> columns = new ArrayList<>();

        try (Connection connection = DriverManager.getConnection(jdbcUrl);
             Statement statement = connection.createStatement();
             ResultSet resultSet = statement.executeQuery(sql)) {

            ResultSetMetaData metaData = resultSet.getMetaData();
            int columnCount = metaData.getColumnCount();

            for (int i = 1; i <= columnCount; i++) {
                columns.add(metaData.getColumnName(i));
            }
        } catch (SQLException e) {
            System.out.println("Error executing query: " + e.getMessage());
        }

        return columns;
    }

    private void generateXml(StringBuilder xmlOutput, List<String> columns) {
        xmlOutput.append("<query>");
        for (String column : columns) {
            xmlOutput.append("<column>").append(column).append("</column>");
        }
        xmlOutput.append("</query>");
    }
}
