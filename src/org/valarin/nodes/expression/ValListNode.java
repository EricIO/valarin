package org.valarin.nodes.expression;

import com.oracle.truffle.api.frame.VirtualFrame;
import org.valarin.nodes.ValExpressionNode;

public class ValListNode extends ValExpressionNode {
    @Children private final ValExpressionNode[] items;

    public ValListNode (ValExpressionNode[] items) {
        this.items = items;
        adoptChildren();
    }

    @Override
    public Object executeGeneric(VirtualFrame frame) {
        return null;
    }
}
