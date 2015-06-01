package org.valarin.nodes;

import com.oracle.truffle.api.CompilerDirectives;
import com.oracle.truffle.api.dsl.NodeChild;
import com.oracle.truffle.api.dsl.NodeField;
import com.oracle.truffle.api.dsl.Specialization;
import com.oracle.truffle.api.frame.FrameSlot;
import com.oracle.truffle.api.frame.FrameSlotKind;
import com.oracle.truffle.api.frame.VirtualFrame;
import com.oracle.truffle.api.nodes.Node;
/*
@NodeChild("expression")
@NodeField(name = "frame", type = FrameSlot.class)
public abstract class ValAssignNode extends ValExpressionNode {
    
    protected abstract FrameSlot getFrame();
    protected abstract Node getExpression();

    @Specialization(guards = "isLong()")
    protected long writeLong(VirtualFrame frame, long value) {
        frame.setLong(this.getFrame(), value);
        return value;
    }
    
    @Specialization(guards = "isBoolean()")
    protected boolean writeBoolean(VirtualFrame frame, boolean value) {
        frame.setBoolean(this.getFrame(), value);
        return value;
    }
    
    @Specialization(guards = "isDouble()")
    protected Double writeDouble(VirtualFrame frame, double value) {
        frame.setDouble(this.getFrame(), value);
        return value;
    }
    
    @Specialization(contains = {"writeLong", "writeBoolean", "writeDouble"})
    protected Object write(VirtualFrame frame, Object value) {
        FrameSlot slot = this.getFrame();
        if (slot.getKind() != FrameSlotKind.Object) {
            CompilerDirectives.transferToInterpreterAndInvalidate();
            slot.setKind(FrameSlotKind.Object);
        }
        
        frame.setObject(slot, value);
        return value;
    }

    protected boolean isLong() {
        return this.isKind(this.getFrame(), FrameSlotKind.Long);
    }
    
    protected boolean isBoolean() {
        return this.isKind(this.getFrame(), FrameSlotKind.Boolean);
    }
    
    protected boolean isDouble() {
        return this.isKind(this.getFrame(), FrameSlotKind.Double);
    }
    
    protected boolean isKind(FrameSlot frame, FrameSlotKind kind) {
        if (frame.getKind() == kind) 
            return true;
        else if (frame.getKind() == FrameSlotKind.Illegal) {
            CompilerDirectives.transferToInterpreterAndInvalidate();
            frame.setKind(kind);
            return true;
        }
        
        return false;
    }
} 
*/