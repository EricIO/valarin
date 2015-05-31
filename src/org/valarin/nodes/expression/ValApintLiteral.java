package org.valarin.nodes.expression;

import com.oracle.graal.nodeinfo.NodeInfo;
import com.oracle.truffle.api.frame.VirtualFrame;
import com.oracle.truffle.api.nodes.UnexpectedResultException;
import org.apfloat.Apint;
import org.valarin.nodes.ValExpressionNode;

import java.math.BigInteger;

@NodeInfo(shortName = "const")
public class ValApintLiteral extends ValExpressionNode {
    
    public final Apint value;
    
    public ValApintLiteral(Apint big) { this.value = big; }
    
    @Override public Apint executeApint(VirtualFrame frame) throws UnexpectedResultException {
        return this.value; 
    }
    
    @Override
    public Object executeGeneric(VirtualFrame frame) {
        return this.value;
    }
    
    @Override
    public String toString() {
        return value.toString();
    }
}
