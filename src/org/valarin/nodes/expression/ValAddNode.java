package org.valarin.nodes.expression;

import com.oracle.graal.nodeinfo.NodeInfo;
import com.oracle.truffle.api.ExactMath;
import com.oracle.truffle.api.dsl.Specialization;
import org.valarin.nodes.ValBinaryNode;

import java.math.BigInteger;

@NodeInfo(shortName = "+")
public abstract class ValAddNode extends ValBinaryNode {

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
    protected long add(long left, long right) {
        System.out.println(ExactMath.addExact(left,right));
        return ExactMath.addExact(left, right);
    }
    
    @Specialization
    protected BigInteger add(BigInteger left, BigInteger right) {
        return left.add(right);
    }

    /**
     * We don't have any implicit typecasting so that you can add strings with strings
     * but we will not typecast another type to string when added.
     * @param left
     * @param right
     * @return
     */
    @Specialization
    protected String add(String left, String right) {
        System.out.println(left + right);
        return left + right;
    }
}
