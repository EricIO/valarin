package org.valarin.nodes.expression;

import com.oracle.graal.nodeinfo.NodeInfo;
import com.oracle.truffle.api.dsl.Specialization;
import org.apfloat.Apfloat;
import org.apfloat.Apint;
import org.valarin.nodes.ValExpressionNode;
import org.valarin.nodes.ValUnaryNode;

@NodeInfo(shortName = "-")
public abstract class ValNegateNode extends ValUnaryNode {
    
    @Specialization
    public long negate(long value) {
        return -value;
    }
    
    @Specialization
    public Apint negate(Apint value) {
        return value.negate();
    }
    
    @Specialization
    public Apfloat negate(Apfloat value) {
        return value.negate();
    }
}
