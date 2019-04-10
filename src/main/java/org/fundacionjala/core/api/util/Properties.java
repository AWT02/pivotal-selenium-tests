package org.fundacionjala.core.api.util;

import org.fundacionjala.core.Environment;

/** Utility to get properties. **/
public final class Properties {

    /** Default constructor. **/
    private Properties() {
    }

    /**
     * This method get value of a property.
     * @param key property key to search.
     * @return string value of the property.
     **/
    public static String getValue(final String key) {
        return Environment.getInstance().getValue(key);
    }
}
