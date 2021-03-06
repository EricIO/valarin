package org.valarin.nodes.expression;

import com.oracle.graal.nodeinfo.NodeInfo;
import com.oracle.truffle.api.dsl.Specialization;
import org.apfloat.Apfloat;
import org.apfloat.Apint;
import org.valarin.nodes.ValBinaryNode;

import java.math.BigDecimal;
import java.math.BigInteger;

@NodeInfo(shortName = "/")
public abstract class ValDivNode extends ValBinaryNode {
    
    @Specialization(rewriteOn = ArithmeticException.class)
    public double div(long left, long right) {
        // TODO: Handle overflow and underflow and throw Arithmetic exception if that happens
        double result = (double) left / (double) right;
        if (result == Double.POSITIVE_INFINITY) // Overflow and underflow
            throw new ArithmeticException("division overflow");
        return result;
    }
    
    @Specialization(rewriteOn = ArithmeticException.class)
    public double div(double left, double right) {
        // TODO: Handle overflow and underflow and throw Arithmetic exception if that happens
        double result = left / right;
        if (result == Double.POSITIVE_INFINITY)
            throw new ArithmeticException("division overflow");
        return result;
    }
    
    @Specialization
    public Apfloat divide(Apint left, Apint right) {
        return left.divide(right);
    }
    
    @Specialization
    public BigDecimal divide(BigDecimal left, BigDecimal right) {
        return left.divide(right, BigDecimal.ROUND_HALF_DOWN);
    }
}
