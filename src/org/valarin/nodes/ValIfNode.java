package org.valarin.nodes;

import com.oracle.truffle.api.frame.VirtualFrame;
import com.oracle.truffle.api.nodes.Node.Child;

public class ValIfNode extends ValStatementNode {
	
	@Child private ValExpressionNode condition;
	@Child private ValStatementNode  thenPart;
	@Child private ValStatementNode  elsePart;
	@Override
	
	public void executeVoid(VirtualFrame frame) {
		if (condition.executeBoolean(frame)) {
			thenPart.executeVoid(frame);
		} else {
			if (elsePart != null)
				elsePart.executeVoid(frame);
		}
		// TODO Auto-generated method stub
		
	}
	
}
