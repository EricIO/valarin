package org.valarin.nodes;

import com.oracle.truffle.api.frame.VirtualFrame;
import com.oracle.truffle.api.nodes.Node;
import com.oracle.truffle.api.nodes.NodeInfo;
import org.valarin.nodes.controlflow.ValReturnException;

@NodeInfo(language = "Valarin", description = "Abstract base node class for all language nodes.")
public abstract class ValStatementNode extends Node {
	public abstract void executeVoid(VirtualFrame frame) throws ValReturnException;
}
