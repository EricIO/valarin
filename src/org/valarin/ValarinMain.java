package org.valarin;


import com.oracle.truffle.api.nodes.Node;

import java.io.FileReader;
import java.io.IOException;

import com.oracle.truffle.api.TruffleLanguage;
import com.oracle.truffle.api.source.Source;
import com.oracle.truffle.api.vm.TruffleVM;

import org.valarin.grammar.Scanner;
import org.valarin.grammar.Parser;
import org.valarin.nodes.ValExpressionNode;
import org.valarin.nodes.ValStatementNode;
import org.valarin.runtime.ValContext;


import com.oracle.truffle.api.instrument.ASTPrinter;
import org.valarin.instrument.ValPrinter;

import java.io.PrintWriter;

@TruffleLanguage.Registration(name = "valarin", mimeType = "application/x-val")
public class ValarinMain extends TruffleLanguage {
    ValContext context;
    
    /**
     * Constructor to be called by subclasses.
     *
     * @param env language environment that will be available via {@link #env()} method to
     *            subclasses.
     */
    public ValarinMain(Env env) {
        super(env);
        this.context = new ValContext();
    }


    public static void main(String[] args) {

        TruffleVM vm = TruffleVM.newVM().build();

        String fileName = "test.val";
        //load file, parse it around, etc

        try {
            vm.eval("application/x-val", new FileReader(fileName));
        } catch (IOException ex){
            //ERR?
        }

    }

    @Override
    protected Object eval(Source code) throws IOException {
        return context.execute(code);
    }

    @Override
    protected Object findExportedSymbol(String globalName) {
        return null;
    }

    @Override
    protected Object getLanguageGlobal() {
        return null;
    }

    @Override
    protected boolean isObjectOfLanguage(Object object) {
        return false;
    }
}
