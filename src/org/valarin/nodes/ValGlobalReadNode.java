package org.valarin.nodes;

import com.oracle.truffle.api.dsl.NodeChild;
import com.oracle.truffle.api.dsl.NodeField;
import com.oracle.truffle.api.dsl.NodeFields;
import com.oracle.truffle.api.dsl.Specialization;
import com.oracle.truffle.api.frame.VirtualFrame;
import org.valarin.runtime.ValRegistry;

@NodeFields(
        {@NodeField(name = "name", type = String.class),
        @NodeField(name = "registry", type = ValRegistry.class)}
)
public abstract class ValGlobalReadNode extends ValExpressionNode {

    protected abstract String getName();
    protected abstract ValRegistry getRegistry();

    @Specialization
    protected Object readObject(VirtualFrame frame) {
        return (Object)getRegistry().lookup(getName());
    }

}
