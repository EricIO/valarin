package org.valarin;

import com.oracle.truffle.api.nodes.Node;
import org.valarin.grammar.Scanner;
import org.valarin.grammar.Parser;

import com.oracle.truffle.api.instrument.ASTPrinter;
import org.valarin.instrument.ValPrinter;

import java.io.PrintWriter;

public class ValarinMain {

    public static void main(String[] args) {

        //if (args.length != 2)
        //    throw new IllegalArgumentException("No file supplied as argument!");

        String fileName = "test.val";

        //load file, parse it around, etc
        Parser p = new Parser(new Scanner(fileName));
        p.Parse();

        ASTPrinter ast = new ValPrinter();
        ast.printTree(new PrintWriter(System.out), p.root, 100, null);

    }

}
