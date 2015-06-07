package org.valarin.nodes.expression;

import com.oracle.truffle.api.frame.VirtualFrame;
import org.valarin.nodes.ValExpressionNode;
import org.valarin.runtime.ValFunction;

public final class ValFunctionLiteralNode extends ValExpressionNode {

    private final ValFunction value;

    public ValFunctionLiteralNode(ValFunction value) {
        this.value = value;
    }

    @Override
    public ValFunction executeGeneric(VirtualFrame frame) {
        return value;
    }

}
