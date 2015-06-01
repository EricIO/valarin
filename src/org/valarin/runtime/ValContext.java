package org.valarin.runtime;

import com.oracle.truffle.api.ExecutionContext;
import com.oracle.truffle.api.Truffle;
import com.oracle.truffle.api.frame.FrameDescriptor;
import com.oracle.truffle.api.frame.VirtualFrame;
import com.oracle.truffle.api.instrument.ASTPrinter;
import com.oracle.truffle.api.source.Source;
import org.valarin.grammar.Parser;
import org.valarin.grammar.Scanner;
import org.valarin.instrument.ValPrinter;
import org.valarin.nodes.ValExpressionNode;
import org.valarin.nodes.ValStatementNode;
import org.valarin.nodes.controlflow.ValBodyNode;
import org.valarin.nodes.controlflow.ValReturnException;
import org.valarin.nodes.controlflow.ValReturnNode;

import java.io.PrintWriter;

/**
 * Class to keep track of registered functions and such.
 */

public class ValContext extends ExecutionContext {

    ValFunctionRegistry functionRegistry;

    public void execute(Source code) {

        Parser p = new Parser(new Scanner(code.getInputStream()));
        p.Parse();

        ValBodyNode rootNode = p.root;
        
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

}
