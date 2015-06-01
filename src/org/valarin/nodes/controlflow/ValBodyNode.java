package org.valarin.nodes.controlflow;

import com.oracle.truffle.api.frame.VirtualFrame;
import com.oracle.truffle.api.nodes.ExplodeLoop;
import org.valarin.nodes.ValExpressionNode;
import org.valarin.nodes.ValStatementNode;

public class ValBodyNode extends ValExpressionNode {

    @Children private final ValStatementNode[] nodes;

    public ValBodyNode (ValStatementNode[] statementNodes) {
        this.nodes = statementNodes;
        adoptChildren();
    }

    @ExplodeLoop
    public Object executeGenericPrint(VirtualFrame frame) throws ValReturnException {
        Object lastExprResult = null;
        for (ValStatementNode stmt : nodes) {
            if (stmt instanceof ValExpressionNode){
                lastExprResult = ((ValExpressionNode)(stmt)).executeGeneric(frame);
                System.out.println(lastExprResult);
            } else {
                stmt.executeVoid(frame);
            }
        }
        return lastExprResult;
    }

    @Override
    @ExplodeLoop
    public Object executeGeneric(VirtualFrame frame) throws ValReturnException {
        Object lastExprResult = null;
        for (ValStatementNode stmt : nodes) {
            if (stmt instanceof ValExpressionNode){
                lastExprResult = ((ValExpressionNode)(stmt)).executeGeneric(frame);
            } else {
                stmt.executeVoid(frame);
            }
        }
        return lastExprResult;
    }
}
