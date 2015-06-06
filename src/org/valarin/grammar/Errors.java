package org.valarin.grammar;

/**
 * Created by Simon on 2015-06-06.
 */
public class Errors {
	public int count = 0;                                    // number of errors detected
	public java.io.PrintStream errorStream = System.out;     // error messages go to this stream
	public String errMsgFormat = "-- line {0} col {1}: {2}"; // 0=line, 1=column, 2=text

	protected void printMsg(int line, int column, String msg) {
		StringBuffer b = new StringBuffer(errMsgFormat);
		int pos = b.indexOf("{0}");
		if (pos >= 0) { b.delete(pos, pos+3); b.insert(pos, line); }
		pos = b.indexOf("{1}");
		if (pos >= 0) { b.delete(pos, pos+3); b.insert(pos, column); }
		pos = b.indexOf("{2}");
		if (pos >= 0) b.replace(pos, pos+3, msg);
		errorStream.println(b.toString());
	}

	public void SynErr (int line, int col, int n) {
		String s;
		switch (n) {
			case 0: s = "EOF expected"; break;
			case 1: s = "ident expected"; break;
			case 2: s = "number expected"; break;
			case 3: s = "float expected"; break;
			case 4: s = "string expected"; break;
			case 5: s = "true expected"; break;
			case 6: s = "false expected"; break;
			case 7: s = "if expected"; break;
			case 8: s = "else expected"; break;
			case 9: s = "then expected"; break;
			case 10: s = "for expected"; break;
			case 11: s = "do expected"; break;
			case 12: s = "eol expected"; break;
			case 13: s = "\"program\" expected"; break;
			case 14: s = "\";\" expected"; break;
			case 15: s = "\"+\" expected"; break;
			case 16: s = "\"-\" expected"; break;
			case 17: s = "\"||\" expected"; break;
			case 18: s = "\"&&\" expected"; break;
			case 19: s = "\"*\" expected"; break;
			case 20: s = "\"/\" expected"; break;
			case 21: s = "\"**\" expected"; break;
			case 22: s = "\"(\" expected"; break;
			case 23: s = "\")\" expected"; break;
			case 24: s = "\"!\" expected"; break;
			case 25: s = "??? expected"; break;
			case 26: s = "this symbol not expected in Expr"; break;
			case 27: s = "invalid Expr"; break;
			case 28: s = "invalid Power"; break;
			default: s = "error " + n; break;
		}
		printMsg(line, col, s);
		count++;
	}

	public void SemErr (int line, int col, String s) {
		printMsg(line, col, s);
		count++;
	}

	public void SemErr (String s) {
		errorStream.println(s);
		count++;
	}

	public void Warning (int line, int col, String s) {
		printMsg(line, col, s);
	}

	public void Warning (String s) {
		errorStream.println(s);
	}
} // Errors
