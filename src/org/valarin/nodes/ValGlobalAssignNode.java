package org.valarin.nodes;

import com.oracle.truffle.api.CompilerDirectives;
import com.oracle.truffle.api.dsl.NodeChild;
import com.oracle.truffle.api.dsl.NodeField;
import com.oracle.truffle.api.dsl.NodeFields;
import com.oracle.truffle.api.dsl.Specialization;
import com.oracle.truffle.api.frame.FrameSlot;
import com.oracle.truffle.api.frame.FrameSlotKind;
import com.oracle.truffle.api.frame.VirtualFrame;
import com.oracle.truffle.api.nodes.Node;
import org.valarin.runtime.ValRegistry;

@NodeChild(value = "expression")
@NodeFields(
        {@NodeField(name = "registry", type = ValRegistry.class),
        @NodeField(name = "name", type = String.class)}
)
public abstract class ValGlobalAssignNode extends ValExpressionNode {

    protected abstract ValExpressionNode getExpression();
    protected abstract ValRegistry getRegistry();
    protected abstract String getName();

    @Specialization(guards = "isLong()")
    protected long writeLong(VirtualFrame frame, long value) {
        getRegistry().register(getName(), value);
        return value;
    }
    
    @Specialization(guards = "isBoolean()")
    protected boolean writeBoolean(VirtualFrame frame, boolean value) {
        getRegistry().register(getName(), value);
        return value;
    }
    
    @Specialization(guards = "isDouble()")
    protected Double writeDouble(VirtualFrame frame, double value) {
        getRegistry().register(getName(), value);
        return value;
    }
    
    @Specialization(contains = {"writeLong", "writeBoolean", "writeDouble"})
    protected Object write(VirtualFrame frame, Object value) {
        getRegistry().register(getName(), value);
        return value;
    }

    protected boolean isLong() {
        return getRegistry().lookup(getName()) instanceof Long;
    }
    
    protected boolean isBoolean() {
        return getRegistry().lookup(getName()) instanceof Boolean;
    }
    
    protected boolean isDouble() {
        return getRegistry().lookup(getName()) instanceof Double;
    }
    
}
