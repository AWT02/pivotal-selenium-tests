package org.fundacionjala.core.util;

import org.springframework.stereotype.Component;
import org.testng.asserts.Assertion;

/**
 * Assertion.
 */
@Component
public class Assert {

    private Assertion assertion;

    public Assert() {
        assertion = new Assertion();
    }

    /**
     * Gets assertion instance.
     * @return {@link Assertion}
     */
    public Assertion getAssertion() {
        return assertion;
    }

    /**
     * Sets assertion instance
     * @param assertion {@link Assertion}
     */
    public void setAssertion(Assertion assertion) {
        this.assertion = assertion;
    }
}
