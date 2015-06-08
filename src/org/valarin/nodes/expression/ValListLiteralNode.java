package org.valarin.nodes.expression;

import com.oracle.truffle.api.frame.VirtualFrame;
import org.valarin.nodes.ValExpressionNode;

import java.util.ArrayList;


public class ValListLiteralNode extends ValExpressionNode {
    
    private ArrayList<Object> list;
    
    public ValListLiteralNode(ArrayList<Object> list) {
        this.list = list;
    } 
    
    @Override
    public String toString() {
        return list.toString();
    }
    
    @Override
    public Object executeGeneric(VirtualFrame frame) {
        return this.list;
    }
}
