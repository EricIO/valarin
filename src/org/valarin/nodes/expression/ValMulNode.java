package org.valarin.nodes.expression;

import com.oracle.graal.nodeinfo.NodeInfo;
import com.oracle.truffle.api.ExactMath;
import com.oracle.truffle.api.dsl.Specialization;
import org.apfloat.Apint;
import org.valarin.nodes.ValBinaryNode;

import java.math.BigInteger;

@NodeInfo(shortName = "*")
public abstract class ValMulNode extends ValBinaryNode {
    
    @Specialization(rewriteOn = ArithmeticException.class)
    public long mul(long left, long right) {
        return ExactMath.multiplyExact(left, right);
    }
    
    @Specialization
    public Apint Apint(Apint left, Apint right) {
        return left.multiply(right);
    }
}
