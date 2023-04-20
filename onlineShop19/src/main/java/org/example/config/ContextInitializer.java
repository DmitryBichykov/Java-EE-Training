package org.example.config;

import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;
import java.io.*;
import java.sql.*;
import java.util.Properties;

public class ContextInitializer implements ServletContextListener {
    public static final String DB_PROPS_PATH = "src/main/resources/db/db.properties";

    @Override
    public void contextInitialized(ServletContextEvent sce) {
        Properties props = new Properties();
        try (FileInputStream in = new FileInputStream(DB_PROPS_PATH)) {
            props.load(in);
        } catch (IOException e) {
            e.printStackTrace();
        }
        String databaseURL = props.getProperty("db.url");

        try (Connection connection = DriverManager
                .getConnection(databaseURL + ";DB_CLOSE_DELAY=-1;INIT=RUNSCRIPT FROM 'classpath:db/init/createDB.sql'\\;" +
                        "RUNSCRIPT FROM 'classpath:db/init/insertDB.sql'", "sa", "")) {
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}
