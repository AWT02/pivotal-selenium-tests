package org.fundacionjala.pivotal.cucumber.runner;

import java.util.Locale;

import cucumber.api.TypeRegistry;
import cucumber.api.TypeRegistryConfigurer;

/**
 * Registers parameters types.
 */
public class ParameterTypes implements TypeRegistryConfigurer {

    /**
     * {@inheritDoc}
     */
    public Locale locale() {
        return Locale.ENGLISH;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void configureTypeRegistry(final TypeRegistry typeRegistry) {

    }
}
