package org.valarin.nodes.controlflow;

import com.oracle.truffle.api.frame.VirtualFrame;
import com.oracle.truffle.api.nodes.Node;
import org.valarin.nodes.ValStatementNode;

public class ValFunctionBody extends ValStatementNode {

    @Children ValBodyNode body;

    @Override
    public void executeVoid(VirtualFrame frame) {

    }

}
