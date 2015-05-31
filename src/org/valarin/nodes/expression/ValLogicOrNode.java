package org.valarin.nodes.expression;

import com.oracle.graal.nodeinfo.NodeInfo;
import org.valarin.nodes.ValBinaryNode;

@NodeInfo(shortName = "||")
public abstract class ValLogicOrNode extends ValBinaryNode {
    
    public boolean or(boolean left, boolean right) {
        return (left || right);
    }
}
