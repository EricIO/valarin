package org.valarin.nodes.controlflow;

import com.oracle.truffle.api.frame.VirtualFrame;
import com.oracle.truffle.api.nodes.ExplodeLoop;
import org.valarin.nodes.ValExpressionNode;
import org.valarin.nodes.ValStatementNode;

public class ValBodyNode extends ValStatementNode {

    @Children private final ValExpressionNode[] nodes;

    public ValBodyNode (ValExpressionNode[] expressionNodes) {
        this.nodes = expressionNodes;
        adoptChildren();
    }

    @ExplodeLoop
    public void executeVoidPrint(VirtualFrame frame) throws ValReturnException {
        for (ValExpressionNode expr : nodes) {
            System.out.println("" + expr.executeGeneric(frame));
        }
    }

    @Override
    @ExplodeLoop
    public void executeVoid(VirtualFrame frame) throws ValReturnException {
        for (ValExpressionNode expr : nodes) {
            expr.executeVoid(frame);
        }
    }
}
