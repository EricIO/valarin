package org.valarin.nodes.expression;

import com.oracle.graal.nodeinfo.NodeInfo;
import com.oracle.truffle.api.dsl.Specialization;
import org.apfloat.Apint;
import org.valarin.nodes.ValBinaryNode;

@NodeInfo(shortName = "<")
public abstract class ValLessThanNode extends ValBinaryNode {

    @Specialization
    boolean lessThan(long left, long right) {
        return left < right;
    }

    @Specialization
    boolean lessThan(Apint left, Apint right) {
        return left.compareTo(right) == -1;
    }

}
