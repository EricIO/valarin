package org.valarin.nodes.controlflow;

import com.oracle.truffle.api.frame.VirtualFrame;
import org.valarin.nodes.ValExpressionNode;
import org.valarin.nodes.ValStatementNode;

public class ValReturnNode extends ValStatementNode {

    @Child private ValExpressionNode node;

    public ValReturnNode(ValExpressionNode node) {
        super();
        this.node = node;
        adoptChildren();
    }

    @Override
    public void executeVoid(VirtualFrame frame) throws ValReturnException {
        Object result = node.executeGeneric(frame);
        throw new ValReturnException(result);
    }

}
