package org.valarin.nodes;

import com.oracle.truffle.api.frame.FrameDescriptor;
import com.oracle.truffle.api.frame.VirtualFrame;
import com.oracle.truffle.api.nodes.Node;
import com.oracle.truffle.api.nodes.NodeInfo;
import com.oracle.truffle.api.nodes.RootNode;
import org.valarin.runtime.ValContext;

@NodeInfo(language = "Valarin")
public class ValRootNode extends RootNode {

    @Node.Child
    private ValExpressionNode body;

    private final ValContext context;

    public ValRootNode(ValContext context, FrameDescriptor frame, ValExpressionNode body) {
        super(null, frame);
        this.context = context;
        this.body = body;
    }


    @Override
    public Object execute(VirtualFrame frame) {
        return body.executeGeneric(frame);
    }
}
