package org.valarin.nodes.expression;

import com.oracle.truffle.api.CompilerDirectives;
import com.oracle.truffle.api.ExactMath;
import com.oracle.truffle.api.dsl.Specialization;
import org.apfloat.Apint;
import org.apfloat.ApintMath;
import org.valarin.nodes.ValBinaryNode;

public abstract class ValPowerNode extends ValBinaryNode {


    /*
     * I could not find a pow in the language that raised an exception on overflow
     * that's why ValPowerNode only handles Apint
     */
    @Specialization
    public Apint power(Apint left, long right) {
        return ApintMath.pow(left, right);
    }
}
