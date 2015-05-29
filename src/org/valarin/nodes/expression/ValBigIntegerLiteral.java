package org.valarin.nodes.expression;

import com.oracle.graal.nodeinfo.NodeInfo;
import com.oracle.truffle.api.frame.VirtualFrame;
import com.oracle.truffle.api.nodes.UnexpectedResultException;
import org.valarin.nodes.ValExpressionNode;

import java.math.BigInteger;

@NodeInfo(shortName = "const")
public class ValBigIntegerLiteral extends ValExpressionNode {
    
    public final BigInteger value;
    
    public ValBigIntegerLiteral(BigInteger big) { this.value = big; }
    
    @Override public BigInteger executeBigInteger(VirtualFrame frame) throws UnexpectedResultException {
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
