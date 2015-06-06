package org.valarin.nodes;

import com.oracle.truffle.api.frame.VirtualFrame;
import org.valarin.runtime.ValNoneType;


public class ValReadArgumentNode extends ValExpressionNode {
    
    private final int index; // The index of the argument to the function.
    
    public ValReadArgumentNode(int index) {
        this.index = index;
    }
    
    @Override
    public Object executeGeneric(VirtualFrame frame) {
        Object[] args = frame.getArguments();
        if (index < args.length) {
            return args[index];
        } else {
            return ValNoneType.NONE;
        }
    }
}
