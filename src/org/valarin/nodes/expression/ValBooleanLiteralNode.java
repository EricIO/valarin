package org.valarin.nodes.expression;

import com.oracle.truffle.api.dsl.Specialization;
import com.oracle.truffle.api.frame.VirtualFrame;
import org.valarin.nodes.ValExpressionNode;

/**
 * Created by eric on 5/31/15.
 */
public class ValBooleanLiteralNode extends ValExpressionNode {

    private final boolean value;

    public ValBooleanLiteralNode(boolean value) {
        this.value = value;
    }

    @Override
    public Object executeGeneric(VirtualFrame frame) {
        return this.value;
    }
}
