package org.valarin.nodes.controlflow;

/**
 * Created by RobinH on 2015-05-30.
 */
public class ValReturnException extends Exception {

    public Object result;

    public ValReturnException(Object result) {
        this.result = result;
    }

}
