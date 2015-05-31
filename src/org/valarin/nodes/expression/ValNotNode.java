package org.valarin.nodes.expression;

import com.oracle.graal.nodeinfo.NodeInfo;
import com.oracle.truffle.api.dsl.Specialization;
import jdk.nashorn.internal.objects.annotations.SpecializedFunction;
import org.valarin.nodes.ValUnaryNode;

@NodeInfo(shortName = "!")
public abstract class ValNotNode extends ValUnaryNode {

    @Specialization
    public boolean not(boolean expression) {
        return !expression;
    }
}
