package org.fundacionjala.core.util;

import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.Reader;
import java.nio.charset.StandardCharsets;

import com.jayway.jsonpath.DocumentContext;
import com.jayway.jsonpath.JsonPath;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;

/**
 * Singleton Class of environment.
 */
public final class Environment {
    private static final Logger LOGGER = LogManager.getLogger(Environment.class.getName());

    private static final String CONF_FILE = "environment.json";
    private static Environment ourInstance;
    private DocumentContext jsonContext;

    /**
     * Method for return the instance dof environment.
     * @return the our instance.
     */
    public static Environment getInstance() {
        if (ourInstance == null) {
            ourInstance = new Environment();
        }
        return ourInstance;
    }

    /**
     * Method for read the JSON file.
     */
    private Environment() {
        JSONParser parser = new JSONParser();
        try (InputStream inputStream = new FileInputStream(CONF_FILE)) {
            Reader fileReader = new InputStreamReader(inputStream, StandardCharsets.UTF_8);
            JSONObject jsonObject = (JSONObject) parser.parse(fileReader);
            jsonContext = JsonPath.parse(jsonObject);
        } catch (IOException | ParseException e) {
            throw new RuntimeException(e);
        }
    }

    /**
     * Getter of the user name.
     * @param key type String
     * @return the String of user.
     */
    public String getValue(final String key) {
        return this.jsonContext.read(key);
    }

    /**
     * This method get the username for provided key.
     * {owner1, memeber1}
     *
     * @param key string.
     * @return string.
     */
    public String getUserName(final String key) {
        final String userNameKey = String
                .format("credentials.%s.username", key);
        return Environment.getInstance().getValue(userNameKey);
    }

    /**
     * This method get the account name for a provided key.
     * {owner1, memeber1}
     *
     * @param key string.
     * @return string.
     */
    public String getAccountName(final String key) {
        try {
            return StringUtil.getKey(getUserName(key), '@').replace(".", "");
        } catch (final Exception e) {
            final String format =
                    "Account name for key %s doesn't found.\nException: %s";
            LOGGER.warn(String.format(format, key, e.getMessage()));
            return key;
        }
    }
}
