package org.valarin.nodes.controlflow;

import com.oracle.truffle.api.frame.VirtualFrame;
import com.oracle.truffle.api.nodes.Node;
import org.valarin.nodes.ValExpressionNode;
import org.valarin.nodes.ValStatementNode;
import org.valarin.runtime.ValNull;

public class ValFunctionBody extends ValExpressionNode {

    @Children private final ValBodyNode body;

    public ValFunctionBody(ValBodyNode body) {
        super();
        this.body = body;
        adoptChildren();
    }

    @Override
    public Object executeGeneric(VirtualFrame frame) {
        try {
            body.executeVoid(frame);
        } catch (ValReturnException ex) {
            return ex.result;
        }
        return ValNull.SINGLETON;
    }

}
