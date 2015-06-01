package org.valarin.nodes;

import com.oracle.truffle.api.CompilerAsserts;
import com.oracle.truffle.api.Truffle;
import com.oracle.truffle.api.dsl.UnsupportedSpecializationException;
import com.oracle.truffle.api.frame.VirtualFrame;
import com.oracle.truffle.api.nodes.ExplodeLoop;
import com.oracle.truffle.api.nodes.IndirectCallNode;
import com.oracle.truffle.api.nodes.Node;
import com.oracle.truffle.api.nodes.UnexpectedResultException;
import org.valarin.nodes.ValExpressionNode;
import org.valarin.runtime.ValFunction;

public class ValInvokeNode extends ValExpressionNode {

    @Child private ValExpressionNode function;
    @Children private final ValExpressionNode[] arguments;
    @Child private IndirectCallNode dispatchNode;

    public ValInvokeNode(ValExpressionNode function, ValExpressionNode[] arguments) {
        this.function = function;
        this.arguments = arguments;
        this.dispatchNode = Truffle.getRuntime().createIndirectCallNode();
    }

    @Override
    @ExplodeLoop
    public Object executeGeneric(VirtualFrame virtualFrame) {
        ValFunction func = getFunction(virtualFrame);
        CompilerAsserts.compilationConstant(arguments.length);

        Object[] argumentList = new Object[arguments.length];
        for (int i = 0; i < arguments.length; i++) {
            argumentList[i] = arguments[i].executeGeneric(virtualFrame);
        }

        return dispatchNode.call(virtualFrame, func.callTarget, argumentList);
    }

    public ValFunction getFunction(VirtualFrame virtualFrame) {
        try {
            return function.executeFunction(virtualFrame);
        } catch (UnexpectedResultException ex) {
            throw new UnsupportedSpecializationException(this, new Node[]{function}, ex.getResult());
        }
    }
}
