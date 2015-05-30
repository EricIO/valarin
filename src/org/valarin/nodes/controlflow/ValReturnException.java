package org.valarin.nodes.controlflow;

import com.oracle.truffle.api.nodes.ControlFlowException;

/**
 * Created by RobinH on 2015-05-30.
 */
public class ValReturnException extends ControlFlowException {

    public Object result;

    public ValReturnException(Object result) {
        this.result = result;
    }

}
