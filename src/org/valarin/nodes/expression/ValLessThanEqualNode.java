package org.valarin.nodes.expression;

import com.oracle.graal.nodeinfo.NodeInfo;
import com.oracle.truffle.api.dsl.Specialization;
import org.apfloat.Apint;
import org.valarin.nodes.ValBinaryNode;

@NodeInfo(shortName = "<=")
public abstract class ValLessThanEqualNode extends ValBinaryNode {

    @Specialization
    boolean lessThanOrEqual(long left, long right) {
        return left <= right;
    }

    @Specialization
    boolean lessThanOrEqual(Apint left, Apint right) {
        return left.compareTo(right) != 1;
    }

}
