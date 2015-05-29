package org.valarin.instrument;

import com.oracle.truffle.api.instrument.ASTPrinter;
import com.oracle.truffle.api.instrument.impl.DefaultASTPrinter;
import com.oracle.truffle.api.nodes.Node;
import com.oracle.truffle.api.nodes.NodeClass;
import com.oracle.truffle.api.nodes.NodeFieldAccessor;
import com.oracle.truffle.api.nodes.NodeFieldAccessor.NodeFieldKind;
import com.oracle.truffle.api.nodes.NodeUtil;

import java.io.PrintWriter;

/* In case we want to print language-specific stuff later, we can use this. */
public class ValPrinter extends DefaultASTPrinter {

    @Override
    protected void printTree(PrintWriter p, Node node, int maxDepth, Node markNode, int level) {

        if (node == null) {
            p.print("null");
            return;
        }

        p.print(nodeName(node));

        p.print("(");
        p.print(sourceInfo(node));
        p.print(NodeUtil.printSyntaxTags(node));
        p.print(")");

        if (level <= maxDepth) {
            p.print(" {");
            for (NodeFieldAccessor field : NodeClass.get(node.getClass()).getFields()) {
                Object value = field.loadValue(node);
                if (value == null) {
                    printNewLine(p, level);
                    p.print(field.getName());
                    p.print(" = null");
                } else if (field.getKind() == NodeFieldKind.CHILD) {
                    printChild(p, maxDepth, markNode, level, field, value);
                } else if (field.getKind() == NodeFieldKind.CHILDREN) {
                    printChildren(p, maxDepth, markNode, level, field, value);
                } else if (field.getKind() == NodeFieldKind.DATA){
                    printNewLine(p, level);
                    p.print(field.getName());
                    p.print(" = ");
                    p.print(field.getObject(node));
                }
            }
            printNewLine(p, level - 1);
            p.print("} ");

        }
    }

}
