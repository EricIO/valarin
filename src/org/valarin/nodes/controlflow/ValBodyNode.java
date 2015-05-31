package org.valarin.nodes.controlflow;

import com.oracle.truffle.api.frame.VirtualFrame;
import com.oracle.truffle.api.nodes.ExplodeLoop;
import org.valarin.nodes.ValExpressionNode;
import org.valarin.nodes.ValStatementNode;

public class ValBodyNode extends ValStatementNode {

    @Children private final ValStatementNode[] nodes;

    public ValBodyNode (ValStatementNode[] expressionNodes) {
        this.nodes = expressionNodes;
        adoptChildren();
    }

    @ExplodeLoop
    public void executeVoidPrint(VirtualFrame frame) throws ValReturnException {
        for (ValStatementNode expr : nodes) {
            if (expr instanceof ValExpressionNode)
                System.out.println(((ValExpressionNode) expr).executeGeneric(frame));
            else
                expr.executeVoid(frame);
        }
    }

    @Override
    @ExplodeLoop
    public void executeVoid(VirtualFrame frame) throws ValReturnException {
        for (ValStatementNode expr : nodes) {
            expr.executeVoid(frame);
        }
    }
}
