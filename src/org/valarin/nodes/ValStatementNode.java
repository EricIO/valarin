package org.valarin.nodes;

import com.oracle.truffle.api.frame.VirtualFrame;
import com.oracle.truffle.api.nodes.Node;

public abstract class ValStatementNode extends Node {
	public abstract void executeVoid(VirtualFrame frame);
}
