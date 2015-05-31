package org.valarin.nodes.controlflow;

import com.oracle.truffle.api.frame.VirtualFrame;
import com.oracle.truffle.api.nodes.ExplodeLoop;
import org.valarin.nodes.ValExpressionNode;
import org.valarin.nodes.ValStatementNode;

public class ValBodyNode extends ValStatementNode {

    @Children private final ValStatementNode[] nodes;

    public ValBodyNode (ValStatementNode[] statementNodes) {
        this.nodes = statementNodes;
        adoptChildren();
    }

    @ExplodeLoop
    public void executeVoidPrint(VirtualFrame frame) throws ValReturnException {
        for (ValStatementNode stmt : nodes) {
            if (stmt instanceof ValExpressionNode)
                System.out.println(((ValExpressionNode) stmt).executeGeneric(frame));
            else
                stmt.executeVoid(frame);
        }
    }

    @Override
    @ExplodeLoop
    public void executeVoid(VirtualFrame frame) throws ValReturnException {
        for (ValStatementNode stmt : nodes) {
            stmt.executeVoid(frame);
        }
    }
}
