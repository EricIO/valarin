package org.valarin;

import java.io.IOException;

import com.oracle.truffle.api.TruffleLanguage;
import com.oracle.truffle.api.source.Source;
import com.oracle.truffle.api.vm.TruffleVM;

import org.valarin.grammar.Scanner;
import org.valarin.grammar.Parser;
import org.valarin.runtime.ValContext;

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
    }

    public static void main(String[] args) {

        TruffleVM vm = TruffleVM.create();

        String fileName = "test.val";
        //load file, parse it around, etc
        Parser p = new Parser(new Scanner(fileName));
        p.Parse();

    }

    @Override
    protected Object eval(Source code) throws IOException {

        return null;
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
