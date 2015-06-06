package org.valarin.runtime;

import com.oracle.truffle.api.frame.VirtualFrame;
import org.valarin.nodes.ValExpressionNode;
import sun.reflect.generics.reflectiveObjects.NotImplementedException;

import java.util.HashMap;
import java.util.Map;

/**
 * Created by RobinH on 2015-05-30.
 */
public class ValRegistry {

    private final Map<String, Object> variables = new HashMap<>();

    public Object lookup(String variableName) {
        return variables.get(variableName);
    }

    public void register(String variableName, Object variable) {
        variables.put(variableName, variable);
    }

}
