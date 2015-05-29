package org.valarin.nodes.controlflow;

import com.oracle.truffle.api.frame.VirtualFrame;
import com.oracle.truffle.api.nodes.ExplodeLoop;
import org.valarin.nodes.ValStatementNode;

/**
 * Created by RobinH on 2015-05-29.
 */
public class ValBodyNode extends ValStatementNode {

    @Children private final ValStatementNode[] nodes;

    public ValBodyNode (ValStatementNode[] statementNodes) {
        this.nodes = statementNodes;
    }

    @Override
    @ExplodeLoop
    public void executeVoid(VirtualFrame frame) {
        for (ValStatementNode stmt : nodes) {
            stmt.executeVoid(frame);
        }
    }
}
