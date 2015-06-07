package org.valarin.runtime;

import com.oracle.truffle.api.RootCallTarget;
import com.oracle.truffle.api.Truffle;
import com.oracle.truffle.api.frame.VirtualFrame;
import org.valarin.nodes.ValExpressionNode;
import org.valarin.nodes.ValRootNode;
import sun.reflect.generics.reflectiveObjects.NotImplementedException;

import java.util.HashMap;
import java.util.Map;

/**
 * Created by RobinH on 2015-05-30.
 */
public class ValRegistry {

    private final Map<String, Object> variables = new HashMap<>();

    public Object lookup(String variableName) {
        Object o =  variables.get(variableName);
        if (o == null ) {
            ValFunction newFunc = new ValFunction(variableName);
            variables.put(variableName, newFunc);
            return newFunc;
        }
        return o;
    }

    public void register(String variableName, Object variable) {
        variables.put(variableName, variable);
    }

    public void registerFunction(String name, ValRootNode node) {
        ValFunction func = (ValFunction)lookup(name);
        //RootCallTarget callTarget = Truffle.getRuntime().createCallTarget(node);
        func.setCallTarget(node.getCallTarget());
        assert node != null;
        assert func != null;
       // assert callTarget != null;
        //func.setCallTarget(callTarget);
    }

}
