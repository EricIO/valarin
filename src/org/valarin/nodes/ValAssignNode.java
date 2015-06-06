package org.valarin.nodes;

import com.oracle.truffle.api.CompilerDirectives;
import com.oracle.truffle.api.dsl.NodeChild;
import com.oracle.truffle.api.dsl.NodeField;
import com.oracle.truffle.api.dsl.Specialization;
import com.oracle.truffle.api.frame.FrameSlot;
import com.oracle.truffle.api.frame.FrameSlotKind;
import com.oracle.truffle.api.frame.VirtualFrame;
import com.oracle.truffle.api.nodes.Node;

@NodeChild("expression")
@NodeField(name = "frameSlot", type = FrameSlot.class)
public abstract class ValAssignNode extends ValExpressionNode {

    protected abstract FrameSlot getFrameSlot();
    protected abstract Node getExpression();

    @Specialization(guards = "isLong()")
    protected long writeLong(VirtualFrame frame, long value) {
        frame.setLong(this.getFrameSlot(), value);
        return value;
    }
    
    @Specialization(guards = "isBoolean()")
    protected boolean writeBoolean(VirtualFrame frame, boolean value) {
        frame.setBoolean(this.getFrameSlot(), value);
        return value;
    }
    
    @Specialization(guards = "isDouble()")
    protected Double writeDouble(VirtualFrame frame, double value) {
        frame.setDouble(this.getFrameSlot(), value);
        return value;
    }
    
    @Specialization(contains = {"writeLong", "writeBoolean", "writeDouble"})
    protected Object write(VirtualFrame frame, Object value) {
        FrameSlot slot = this.getFrameSlot();
        if (slot.getKind() != FrameSlotKind.Object) {
            CompilerDirectives.transferToInterpreterAndInvalidate();
            slot.setKind(FrameSlotKind.Object);
        }
        
        frame.setObject(slot, value);
        return value;
    }

    protected boolean isLong() {
        return this.isKind(FrameSlotKind.Long);
    }
    
    protected boolean isBoolean() {
        return this.isKind(FrameSlotKind.Boolean);
    }

    protected boolean isDouble() {
        return this.isKind(FrameSlotKind.Double);
    }
    
    protected boolean isKind(FrameSlotKind kind) {
        if (this.getFrameSlot().getKind() == kind)
            return true;
        else if (this.getFrameSlot().getKind() == FrameSlotKind.Illegal) {
            CompilerDirectives.transferToInterpreterAndInvalidate();
            this.getFrameSlot().setKind(kind);
            return true;
        }
        
        return false;
    }
} 
