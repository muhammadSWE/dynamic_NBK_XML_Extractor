package com.NBK.xmlextractor.XMLConfiguration;

import javax.xml.bind.annotation.*;
import java.util.List;

/**
 * Represents the configuration data extracted from the XML file.
 */
@XmlRootElement(name = "configuration")
@XmlAccessorType(XmlAccessType.FIELD)
public class XMLConfig {

    @XmlElement(name = "dburl")
    private String dburl;

    @XmlElementWrapper(name = "queries")
    @XmlElement(name = "query")
    private List<String> queries;

    @XmlElementWrapper(name = "tables")
    @XmlElement(name = "table")
    private List<Table> tables;

    //full constructor
    public XMLConfig(String dburl, List<String> queries, List<Table> tables) {
        this.dburl = dburl;
        this.queries = queries;
        this.tables = tables;
    }
    //default constructor
    public XMLConfig() {
    }


    // Getters and setters
    public String getDburl() {
        return dburl;
    }

    public void setDburl(String dburl) {
        this.dburl = dburl;
    }
    public List<String> getQueries() {
        return queries;
    }

    public void setQueries(List<String> queries) {
        this.queries = queries;
    }
    public List<Table> getTables() {
        return tables;
    }

    public void setTables(List<Table> tables) {
        this.tables = tables;
    }

    /**
     * Represents a single table definition within the XML configuration.
     */
    public static class Table {

        @XmlElement(name = "name")
        private String TableName;

        @XmlElementWrapper(name = "columns")
        @XmlElement(name = "column")
        private List<String> Columns;

        //default constructor
        public Table() {
        }

        //full constructor
        public Table(String tablename, List<String> columns) {
            this.TableName = tablename;
            this.Columns = columns;
        }

        // Getters, Setters
        public String getTableName() {
            return TableName;
        }

        public void setTableName(String tableName) {
            this.TableName = tableName;
        }

        public List<String> getColumns() {
            return Columns;
        }

        public void setColumns(List<String> columns) {
            this.Columns = columns;
        }
    }


}