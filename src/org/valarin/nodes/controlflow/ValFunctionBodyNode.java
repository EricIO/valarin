package org.valarin.nodes.controlflow;

import com.oracle.truffle.api.frame.VirtualFrame;
import com.oracle.truffle.api.nodes.Node;
import org.valarin.nodes.ValExpressionNode;
import org.valarin.nodes.ValStatementNode;
import org.valarin.runtime.ValNoneType;

public class ValFunctionBodyNode extends ValExpressionNode {

    @Child private final ValBodyNode body;

    public ValFunctionBodyNode(ValBodyNode body) {
        super();
        this.body = body;
        adoptChildren();
    }

    @Override
    public Object executeGeneric(VirtualFrame frame) {
        try {
            return body.executeGeneric(frame);
        } catch (ValReturnException ex) {
            return ex.result;
        }
    }
}
