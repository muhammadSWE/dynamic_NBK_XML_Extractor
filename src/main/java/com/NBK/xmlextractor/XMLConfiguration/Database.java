package com.NBK.xmlextractor.XMLConfiguration;

import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;

@XmlRootElement(name = "database")
public class Database {
    private String JDBCURL;

    @XmlElement(name = "JDBCURL")
    public String getJDBCURL() {
        return JDBCURL;
    }

    public void setJDBCURL(String JDBCURL) {
        this.JDBCURL = JDBCURL;
    }
}
