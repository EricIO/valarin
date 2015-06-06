package org.valarin.nodes;

import com.oracle.graal.phases.common.LoweringPhase;
import com.oracle.truffle.api.CompilerDirectives;
import com.oracle.truffle.api.dsl.NodeChild;
import com.oracle.truffle.api.dsl.NodeField;
import com.oracle.truffle.api.dsl.Specialization;
import com.oracle.truffle.api.frame.FrameSlot;
import com.oracle.truffle.api.frame.FrameSlotKind;
import com.oracle.truffle.api.frame.FrameSlotTypeException;
import com.oracle.truffle.api.frame.VirtualFrame;
import com.oracle.truffle.api.nodes.Node;

@NodeField(name = "frameSlot", type = FrameSlot.class)
public abstract class ValReadNode extends ValExpressionNode {

    protected abstract FrameSlot getFrameSlot();

    @Specialization(rewriteOn = FrameSlotTypeException.class)
    protected long readLong(VirtualFrame frame) throws FrameSlotTypeException{
        return frame.getLong(getFrameSlot());
    }
    
    @Specialization(rewriteOn = FrameSlotTypeException.class)
    protected boolean readBoolean(VirtualFrame frame) throws FrameSlotTypeException {
        return frame.getBoolean(getFrameSlot());
    }

    @Specialization(rewriteOn = FrameSlotTypeException.class)
    protected Double readDouble(VirtualFrame frame) throws FrameSlotTypeException {
        return frame.getDouble((getFrameSlot()));
    }

    @Specialization(rewriteOn = FrameSlotTypeException.class)
    protected Object readObject(VirtualFrame frame) throws FrameSlotTypeException {
        return frame.getObject((getFrameSlot()));
    }

    @Specialization(contains = {"readLong", "readBoolean", "readDouble"})
    protected Object read(VirtualFrame frame) {
        return frame.getValue(getFrameSlot());
    }

} 
