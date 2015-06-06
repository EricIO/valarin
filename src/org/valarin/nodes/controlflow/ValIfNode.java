package org.valarin.nodes.controlflow;

import com.oracle.graal.nodeinfo.NodeInfo;
import com.oracle.truffle.api.dsl.UnsupportedSpecializationException;
import com.oracle.truffle.api.frame.VirtualFrame;
import com.oracle.truffle.api.nodes.Node;
import com.oracle.truffle.api.nodes.Node.Child;
import com.oracle.truffle.api.nodes.UnexpectedResultException;
import org.valarin.nodes.ValExpressionNode;
import org.valarin.nodes.ValExpressionNode;
import org.valarin.runtime.ValFunction;
import org.valarin.runtime.ValNoneType;

@NodeInfo(shortName = "if")
public final class ValIfNode extends ValExpressionNode {
	
	@Child private ValExpressionNode condition;
	@Child private ValExpressionNode  thenPart;
	@Child private ValExpressionNode  elsePart;

	public ValIfNode(ValExpressionNode condNode, ValExpressionNode thenNode, ValExpressionNode elseNode) {
		this.condition = condNode;
		this.thenPart  = thenNode;
		this.elsePart  = elseNode;
		adoptChildren();
	}
	
	@Override
	public Object executeGeneric(VirtualFrame frame) throws ValReturnException {
		try {
			if (condition.executeBoolean(frame)) {
				return thenPart.executeGeneric(frame);
			} else {
				// If we do have an else part
				if (elsePart != null) {
					return elsePart.executeGeneric(frame);
				}
			}
		} catch (UnexpectedResultException ex ) {
			throw new UnsupportedSpecializationException(this, new Node[]{condition}, ex.getResult());
		}
		return ValNoneType.NONE;
	}
	
}
