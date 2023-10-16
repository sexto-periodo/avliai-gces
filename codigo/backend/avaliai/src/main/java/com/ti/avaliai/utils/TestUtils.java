package com.ti.avaliai.utils;


import lombok.experimental.UtilityClass;
import org.springframework.jdbc.core.ConnectionCallback;
import org.springframework.jdbc.core.JdbcTemplate;

import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@UtilityClass
public class TestUtils {


    public void clearDatabase(JdbcTemplate jdbcTemplate) {
        clearDatabase(jdbcTemplate, getTableNames(jdbcTemplate));
    }

    public void clearDatabase(JdbcTemplate jdbcTemplate, List<String> tableNames) {
        jdbcTemplate.execute("SET REFERENTIAL_INTEGRITY FALSE");
        tableNames.forEach(tableName -> {
            jdbcTemplate.execute("TRUNCATE TABLE " + tableName + " RESTART IDENTITY");
            jdbcTemplate.execute("ALTER SEQUENCE " + tableName + "_SEQ" + " RESTART WITH 1");
        });
        jdbcTemplate.execute("SET REFERENTIAL_INTEGRITY TRUE");
    }

    private List<String> getTableNames(JdbcTemplate jdbcTemplate) {

        Object tableNames = jdbcTemplate.execute((ConnectionCallback<Object>) callback -> {
            ArrayList<String> names = new ArrayList<>();

            try (ResultSet tables = callback.getMetaData().getTables(
                    null, null, "%", new String[]{"TABLE"})) {
                while (tables.next()) {
                    names.add(tables.getString("TABLE_NAME"));
                }
            }

            return names;
        });

        List<String> tableNamesString =  (List<String>) tableNames;
        return tableNamesString.stream().filter(t -> t.startsWith("T_")).collect(Collectors.toList());
    }
}