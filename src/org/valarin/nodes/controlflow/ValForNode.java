package org.valarin.nodes.controlflow;

import com.oracle.truffle.api.dsl.UnsupportedSpecializationException;
import com.oracle.truffle.api.frame.VirtualFrame;
import com.oracle.truffle.api.nodes.Node;
import com.oracle.truffle.api.nodes.Node.Child;
import com.oracle.truffle.api.nodes.UnexpectedResultException;
import org.valarin.nodes.ValExpressionNode;
import org.valarin.runtime.ValFunction;
import org.valarin.runtime.ValNoneType;

public final class ValForNode extends ValExpressionNode {

    @Child private ValExpressionNode initNode;
    @Child private ValExpressionNode condNode;
    @Child private ValExpressionNode nextNode;
    @Child private ValExpressionNode whileNode;

    public ValForNode(ValExpressionNode initNode, ValExpressionNode condNode, ValExpressionNode nextNode, ValExpressionNode whileNode) {
        this.initNode  = initNode;
        this.condNode = condNode;
        this.nextNode = nextNode;
        this.whileNode = whileNode;
        adoptChildren();
    }

    @Override
    public Object executeGeneric(VirtualFrame frame) throws ValReturnException {

        initNode.executeVoid(frame);
        Object last_result = ValNoneType.NONE;

        try {
            while (condNode.executeBoolean(frame)) {
                last_result = whileNode.executeGeneric(frame);
                nextNode.executeVoid(frame);
            }
            return last_result;
        } catch (UnexpectedResultException ex) {
            return last_result;
        }

    }

}
