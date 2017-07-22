package com.java.xdd.springsecurity.mapping;

import org.springframework.jdbc.object.MappingSqlQuery;
import org.springframework.security.core.Authentication;

import javax.sql.DataSource;
import java.sql.ResultSet;
import java.sql.SQLException;

public class ResourceMapping extends MappingSqlQuery {

    protected ResourceMapping(DataSource dataSource, String resourceQuery) {
        super(dataSource, resourceQuery);
        compile();
    }

    protected Object mapRow(ResultSet rs, int rownum) throws SQLException {
        String url = rs.getString(1);
        String role = rs.getString(2);
        //Resource resource = new Resource(url, role);
        return null;
    }
}