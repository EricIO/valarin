package org.valarin.nodes.expression;

import com.oracle.graal.nodeinfo.NodeInfo;
import com.oracle.truffle.api.dsl.Specialization;
import org.valarin.nodes.ValBinaryNode;

@NodeInfo(shortName = "&&")
public abstract class ValLogicAndNode extends ValBinaryNode {
    
    @Specialization
    public boolean and(boolean left, boolean right) { 
        return (left && right);
    }
}
