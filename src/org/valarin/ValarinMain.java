package org.valarin;

import org.valarin.grammar.Scanner;
import org.valarin.grammar.Parser;

public class ValarinMain {

    public static void main(String[] args) {

        //if (args.length != 2)
        //    throw new IllegalArgumentException("No file supplied as argument!");

        String fileName = "test.vlr";

        //load file, parse it around, etc
        Parser p = new Parser(new Scanner(fileName));
        p.Parse();


    }

}
