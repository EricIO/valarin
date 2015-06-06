package org.valarin.runtime;

import com.oracle.truffle.api.ExecutionContext;
import com.oracle.truffle.api.Truffle;
import com.oracle.truffle.api.frame.FrameDescriptor;
import com.oracle.truffle.api.instrument.ASTPrinter;
import com.oracle.truffle.api.source.Source;
import org.valarin.grammar.Parser;
import org.valarin.grammar.Scanner;
import org.valarin.grammar.Token;
import org.valarin.grammar.ValNodeFactory;
import org.valarin.instrument.ValPrinter;
import org.valarin.nodes.ValExpressionNode;
import org.valarin.nodes.ValGlobalAssignNode;
import org.valarin.nodes.controlflow.ValBodyNode;
import org.valarin.nodes.controlflow.ValReturnException;

import java.io.PrintWriter;

/**
 * Class to keep track of registered functions and such.
 */

public class ValContext extends ExecutionContext {

    private final ValRegistry variableRegistry = new ValRegistry();

    public void execute(Source code) {

        Parser p = new Parser(new Scanner(code.getInputStream()));
        p.Parse();

        //ValBodyNode rootNode = p.root;

        ValNodeFactory vnf = new ValNodeFactory();

        Token someToken = new Token();
        someToken.val = "glabal";

        Token someAddition = new Token();
        someAddition.val = "+";

        Token left = new Token();
        Token right = new Token();

        left.val = "10";
        right.val = "25";

        ValExpressionNode vgan = vnf.createAssignment(
                someToken, vnf.createBinaryNode(someAddition, vnf.createNumberLiteral(left), vnf.createNumberLiteral(right)));

        ValExpressionNode[] exprs = new ValExpressionNode[2];
        exprs[0] = vgan;

        Token someRead = new Token();
        someRead.val = "glabal";

        ValExpressionNode vran = vnf.createRead(someRead);
        exprs[1] = vran;

        ValBodyNode rootNode = new ValBodyNode(exprs);

        ASTPrinter printer = new ValPrinter();
        printer.printTree(new PrintWriter(System.out), rootNode, 100, null);


        try {
            Object result = rootNode.executeGenericPrint(Truffle.getRuntime().createVirtualFrame(new Object[]{}, new FrameDescriptor()));
            System.out.println("returned: " + result);
        } catch (ValReturnException ex) {
            System.out.println("program returned: " + ex.result);
        }


    }

    @Override
    public String getLanguageShortName() {
        return null;
    }

    public ValRegistry getRegistry() {
        return variableRegistry;
    }

}
