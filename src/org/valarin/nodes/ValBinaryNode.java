package org.valarin.nodes;

import com.oracle.truffle.api.dsl.NodeChild;
import com.oracle.truffle.api.dsl.NodeChildren;

@NodeChildren({@NodeChild("left"), @NodeChild("right")})
public abstract class ValBinaryNode extends ValExpressionNode {

}
