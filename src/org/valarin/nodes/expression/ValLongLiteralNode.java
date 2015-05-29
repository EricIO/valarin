package org.valarin.nodes.expression;

import com.oracle.graal.nodeinfo.NodeInfo;
import com.oracle.truffle.api.frame.VirtualFrame;
import com.oracle.truffle.api.nodes.UnexpectedResultException;
import org.valarin.nodes.ValExpressionNode;

import java.rmi.UnexpectedException;
import java.util.Objects;

@NodeInfo(shortName = "const")
public class ValLongLiteralNode extends ValExpressionNode {
    
    private final long value;
    
    public ValLongLiteralNode(long value) {
        this.value = value;
    }
    
    @Override public long executeLong(VirtualFrame frame) throws UnexpectedResultException { return this.value; }
    
    @Override public Object executeGeneric(VirtualFrame frame) { return value; }
}
