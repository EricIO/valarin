package org.valarin.nodes;

import com.oracle.truffle.api.dsl.NodeChild;
import com.oracle.truffle.api.dsl.NodeField;
import com.oracle.truffle.api.dsl.Specialization;
import com.oracle.truffle.api.frame.FrameSlot;
import com.oracle.truffle.api.frame.VirtualFrame;
import com.oracle.truffle.api.nodes.Node;

@NodeChild("expression")
@NodeField(name = "frame", type = FrameSlot.class)
public abstract class ValAssignNode extends ValStatementNode {
    
    protected abstract FrameSlot getFrame();
    protected abstract Node getExpressionNode();
    
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
} 
