package org.valarin.nodes;

import com.oracle.truffle.api.frame.FrameDescriptor;
import com.oracle.truffle.api.frame.VirtualFrame;
import com.oracle.truffle.api.nodes.Node;
import com.oracle.truffle.api.nodes.NodeInfo;
import com.oracle.truffle.api.nodes.RootNode;
import org.valarin.runtime.ValContext;
import org.valarin.runtime.ValNoneType;

@NodeInfo(language = "Valarin")
public class ValRootNode extends RootNode {

    @Child private ValStatementNode body;

    private final ValContext context;

    public ValRootNode(ValContext context, FrameDescriptor frame, ValStatementNode body) {
        super(null, frame);
        this.context = context;
        this.body = body;
    }


    @Override
    public Object execute(VirtualFrame frame) {
        if (this.body instanceof ValExpressionNode)
            return ((ValExpressionNode) this.body).executeGeneric(frame);
        else
            return ValNoneType.NONE;
    }
}
