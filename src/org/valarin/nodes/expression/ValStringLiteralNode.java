package org.valarin.nodes.expression;

import com.oracle.truffle.api.frame.VirtualFrame;
import org.valarin.nodes.ValExpressionNode;

public class ValStringLiteralNode extends ValExpressionNode {

    private String value;
    
    public ValStringLiteralNode(String value) {
        this.value = value;
    }
    
    @Override
    public Object executeGeneric(VirtualFrame frame) {
        return this.value;
    }
    
    @Override
    public String toString() {
        return this.value;
    }
}
