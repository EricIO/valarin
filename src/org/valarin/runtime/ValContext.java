package org.valarin.runtime;

import com.oracle.truffle.api.ExecutionContext;

/**
 * Class to keep track of registered functions and such.
 */
public class ValContext extends ExecutionContext {

    @Override
    public String getLanguageShortName() {
        return null;
    }

}
