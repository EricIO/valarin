package org.valarin.nodes.expression;

import com.oracle.truffle.api.CompilerDirectives;
import com.oracle.truffle.api.ExactMath;
import com.oracle.truffle.api.dsl.Specialization;
import org.valarin.nodes.ValBinaryNode;

import java.math.BigInteger;

public abstract class ValPowerNode extends ValBinaryNode {


    /*
     * I could not find a pow in the language that raised an exception on overflow
     * that's why ValPowerNode only handles BigInteger
     */
    @Specialization
    public BigInteger power(BigInteger left, long right) {
        return left.pow((int)right);
    }
}
