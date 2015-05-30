package org.valarin.runtime;

import sun.reflect.generics.reflectiveObjects.NotImplementedException;

import java.util.HashMap;
import java.util.Map;

/**
 * Created by RobinH on 2015-05-30.
 */
public class ValFunctionRegistry {

    private final Map<String, ValFunction> functions = new HashMap<>();

    ValFunction lookup(String functionName) {
        throw new NotImplementedException();
    }

    void register(String functionName, ValFunction function) {
        throw new NotImplementedException();
    }



}
