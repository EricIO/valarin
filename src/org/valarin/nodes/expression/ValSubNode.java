package org.valarin.nodes.expression;

import com.oracle.graal.nodeinfo.NodeInfo;
import com.oracle.truffle.api.ExactMath;
import com.oracle.truffle.api.dsl.Specialization;
import org.valarin.nodes.ValBinaryNode;

import java.math.BigInteger;

@NodeInfo(shortName = "-")
public abstract class ValSubNode extends ValBinaryNode {
    /**
     * For perfomance we always work with longs if we can. All integers
     * in the language are BigInteger but we use longs when it is possible so
     * that the truffle api is able to optimize. When either of the arguments
     * are not longs or the addExact method will overflow we go to the BigInteger
     * version of add.
     * @param left
     * @param right
     * @return
     */
    @Specialization(rewriteOn = ArithmeticException.class)
    protected long subtract(long left, long right) {
        return ExactMath.subtractExact(left, right);
    }

    @Specialization
    protected BigInteger subtract(BigInteger left, BigInteger right) {
        return left.subtract(right);
    }
}
