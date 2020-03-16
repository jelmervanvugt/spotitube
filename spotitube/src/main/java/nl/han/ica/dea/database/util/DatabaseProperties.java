package nl.han.ica.dea.database.util;

import java.io.IOException;
import java.util.Objects;
import java.util.Properties;
import java.util.logging.Level;
import java.util.logging.Logger;

public class DatabaseProperties {

    private Logger logger = Logger.getLogger(getClass().getName());
    private Properties properties;

    public DatabaseProperties() {
        properties = new Properties();
        try {
            properties.load(Objects.requireNonNull(getClass()
                    .getClassLoader()
                    .getResourceAsStream("database.properties")));
        } catch (IOException e) {
            logger.log(Level.SEVERE, "Can't access property file database.properties", e);
        }
    }

    public String connectionString() {
        return properties.getProperty("connectionString");
    }

    public String driverString() {
        return properties.getProperty("driver");
    }


}
