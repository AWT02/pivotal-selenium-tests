package org.fundacionjala.core.util;

import org.apache.commons.lang3.StringUtils;
import org.apache.log4j.Logger;

import java.util.HashMap;
import java.util.Map;

/** This class represent scenario context. **/
public final class ScenarioContext {
    public static final String API_URL_KEY = "url.api";
    private static final Logger LOGGER =
            Logger.getLogger(ScenarioContext.class.getName());
    private static final ScenarioContext INSTANCE = new ScenarioContext();
    private final Map<String, Object> context;

    /** Default constructor. **/
    private ScenarioContext() {
        this.context = new HashMap<>();
        String baseUrl = "https://www.pivotaltracker.com/services/v5";
        try {
            baseUrl = Environment.getInstance().getValue(API_URL_KEY);
        } catch (final ExceptionInInitializerError e) {
            LOGGER.info("Coudn't get api url from properties.", e);
        } catch (final RuntimeException e) {
            LOGGER.info("2 Coudn't get api url from properties.", e);
        } catch (final Exception e) {
            LOGGER.info("3 Coudn't get api url from properties.", e);
        }
        this.setContext(API_URL_KEY, baseUrl);
    }

    /**
     * Get context instance.
     *
     * @return ScenarioContext.
     */
    public static ScenarioContext getInstance() {
        return INSTANCE;
    }

    /**
     * This method get context string value for key.
     *
     * @param key string
     * @return object value.
     */
    public String getContextAsString(final String key) {
        return INSTANCE.context.get(key).toString();
    }

    /**
     * Set an element in context.
     *
     * @param key   string key
     * @param value object value
     */
    public void setContext(final String key, final Object value) {
        this.context.put(key, value);
    }

    /**
     * This method get context value for key.
     *
     * @param key string
     * @return object value.
     */
    public Object getContext(final String key) {
        return this.isContains(key) ? this.context.get(key) : StringUtils.EMPTY;
    }

    /**
     * This method get context string value for key.
     *
     * @param key    key in the context.
     * @param mapKey key in the map.
     * @return object value.
     */
    public String getContextInMapAsString(final String key, final String mapKey) {
        final Map map = (Map) context.get(key);
        return map.get(mapKey).toString();
    }

    /**
     * This method check if a key is in the context.
     *
     * @param key string
     * @return boolean
     **/
    public boolean isContains(final String key) {
        return this.context.containsKey(key);
    }

    /**
     * This method verify if a value already exist in the context.
     *
     * @param key   string.
     * @param value string.
     * @return boolean.
     */
    public boolean has(final String key, final Object value) {
        return INSTANCE.isContains(key) && getContext(key).equals(value);
    }
}
