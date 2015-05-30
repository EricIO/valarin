package org.valarin.nodes.expression;

import com.oracle.truffle.api.ExactMath;
import com.oracle.truffle.api.dsl.Specialization;
import org.valarin.nodes.ValBinaryNode;

import java.math.BigInteger;

/**
 * Created by eric on 5/30/15.
 */
public abstract class ValPowerNode extends ValBinaryNode {
    
    @Specialization
    public BigInteger power(long base, int exponent) {
        return BigInteger.valueOf(base).pow(exponent);
    }
    
    @Specialization
    public BigInteger power(BigInteger base, int exponent) {
        return base.pow(exponent);
    }
}
