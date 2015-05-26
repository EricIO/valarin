package org.valarin.nodes;

import com.oracle.truffle.api.dsl.NodeChild;
import com.oracle.truffle.api.dsl.NodeChildren;
import com.oracle.truffle.api.source.SourceSection;

/**
 * Created by eric on 5/26/15.
 */
@NodeChildren({@NodeChild("left"), @NodeChild("right")})
public abstract class ValBinaryNode extends ValExpressionNode {

}
