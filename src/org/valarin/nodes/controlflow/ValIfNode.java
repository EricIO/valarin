package org.valarin.nodes.controlflow;

import com.oracle.truffle.api.dsl.UnsupportedSpecializationException;
import com.oracle.truffle.api.frame.VirtualFrame;
import com.oracle.truffle.api.nodes.Node;
import com.oracle.truffle.api.nodes.Node.Child;
import com.oracle.truffle.api.nodes.UnexpectedResultException;
import org.valarin.nodes.ValExpressionNode;
import org.valarin.nodes.ValStatementNode;
import org.valarin.runtime.ValFunction;

public class ValIfNode extends ValStatementNode {
	
	@Child private ValExpressionNode condition;
	@Child private ValStatementNode  thenPart;
	@Child private ValStatementNode  elsePart;

	public ValIfNode(ValExpressionNode condNode, ValStatementNode thenNode, ValStatementNode elseNode) {
		this.condition = condNode;
		this.thenPart  = thenNode;
		this.elsePart  = elseNode;
		adoptChildren();
	}
	
	@Override
	public void executeVoid(VirtualFrame frame) throws ValReturnException {
		try {
			if (condition.executeBoolean(frame)) {
				thenPart.executeVoid(frame);
			} else {
				// If we do have an else part
				if (elsePart != null) {
					elsePart.executeVoid(frame);
				}
			}
		} catch (UnexpectedResultException ex ) {
			throw new UnsupportedSpecializationException(this, new Node[]{condition}, ex.getResult());
		}
	}
	
}
