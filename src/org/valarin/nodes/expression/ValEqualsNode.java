package org.valarin.nodes.expression;

import com.oracle.graal.nodeinfo.NodeInfo;
import com.oracle.truffle.api.dsl.Specialization;
import com.oracle.truffle.api.frame.VirtualFrame;
import org.apfloat.Apint;
import org.valarin.nodes.ValBinaryNode;

@NodeInfo(shortName = "==")
public abstract class ValEqualsNode extends ValBinaryNode {

    @Specialization
    boolean equals(long left, long right) {
        return left == right;
    }

    @Specialization
    boolean equals(Apint left, Apint right) {
        return left.equals(right);
    }

    @Specialization
    boolean equals(String left, String right) {
        return left.equals(right);
    }

}
