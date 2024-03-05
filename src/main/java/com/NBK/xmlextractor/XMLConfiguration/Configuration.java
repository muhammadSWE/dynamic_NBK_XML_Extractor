package com.NBK.xmlextractor.XMLConfiguration;


import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlElementWrapper;
import javax.xml.bind.annotation.XmlRootElement;
import java.util.List;

@XmlRootElement(name = "configuration")
public class Configuration {
    private Database database;
    private List<Table> tables;
    private List<Query> queries;

    @XmlElement(name = "query")
    public List<Query> getQueries() {
        return queries;
    }

    public void setQueries(List<Query> queries) {
        this.queries = queries;
    }


    @XmlElement(name = "database")
    public Database getDatabase() {
        return database;
    }

    public void setDatabase(Database database) {
        this.database = database;
    }

    @XmlElementWrapper(name = "tables")
    @XmlElement(name = "table")
    public List<Table> getTables() {
        return tables;
    }

    public void setTables(List<Table> tables) {
        this.tables = tables;
    }
}

