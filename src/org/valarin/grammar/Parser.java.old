

public class Parser {
	public static final int _EOF = 0;
	public static final int _ident = 1;
	public static final int _number = 2;
	public static final int _float = 3;
	public static final int _string = 4;
	public static final int _eol = 5;
	public static final int maxT = 17;

	static final boolean _T = true;
	static final boolean _x = false;
	static final int minErrDist = 2;

	public Token t;    // last recognized token
	public Token la;   // lookahead token
	int errDist = minErrDist;
	
	public Scanner scanner;
	public Errors errors;

	

	public Parser(Scanner scanner) {
		this.scanner = scanner;
		errors = new Errors();
	}

	void SynErr (int n) {
		if (errDist >= minErrDist) errors.SynErr(la.line, la.col, n);
		errDist = 0;
	}

	public void SemErr (String msg) {
		if (errDist >= minErrDist) errors.SemErr(t.line, t.col, msg);
		errDist = 0;
	}
	
	void Get () {
		for (;;) {
			t = la;
			la = scanner.Scan();
			if (la.kind <= maxT) {
				++errDist;
				break;
			}

			la = t;
		}
	}
	
	void Expect (int n) {
		if (la.kind==n) Get(); else { SynErr(n); }
	}
	
	boolean StartOf (int s) {
		return set[s][la.kind];
	}
	
	void ExpectWeak (int n, int follow) {
		if (la.kind == n) Get();
		else {
			SynErr(n);
			while (!StartOf(follow)) Get();
		}
	}
	
	boolean WeakSeparator (int n, int syFol, int repFol) {
		int kind = la.kind;
		if (kind == n) { Get(); return true; }
		else if (StartOf(repFol)) return false;
		else {
			SynErr(n);
			while (!(set[syFol][kind] || set[repFol][kind] || set[0][kind])) {
				Get();
				kind = la.kind;
			}
			return StartOf(syFol);
		}
	}
	
	void Valarin() {
		Function();
		while (StartOf(1)) {
			if (la.kind == 2 || la.kind == 8) {
				Expr();
			} else if (la.kind == 10) {
				Function();
			} else {
				Statement();
			}
		}
	}

	void Function() {
		Expect(10);
		Expect(1);
		Expect(11);
		if (la.kind == 1) {
			Get();
			while (la.kind == 12) {
				Get();
				Expect(1);
			}
		}
		Expect(13);
		Expect(14);
		Body();
		Expect(15);
	}

	void Expr() {
		if (la.kind == 2) {
			Term();
			while (la.kind == 6) {
				Get();
				Term();
			}
		} else if (la.kind == 8) {
			List();
		} else SynErr(18);
	}

	void Statement() {
		Assign();
	}

	void Term() {
		Expect(2);
	}

	void List() {
		Expect(8);
		while (la.kind == 2 || la.kind == 8) {
			Expr();
		}
		Expect(9);
	}

	void Assign() {
		Expect(1);
		Expect(7);
		Expr();
	}

	void Body() {
		while (la.kind == 1 || la.kind == 2 || la.kind == 8) {
			if (la.kind == 1) {
				Statement();
			} else {
				Expr();
			}
			Expect(5);
		}
		Expect(16);
		Expr();
	}



	public void Parse() {
		la = new Token();
		la.val = "";		
		Get();
		Valarin();
		Expect(0);

	}

	private static final boolean[][] set = {
		{_T,_x,_x,_x, _x,_x,_x,_x, _x,_x,_x,_x, _x,_x,_x,_x, _x,_x,_x},
		{_x,_T,_T,_x, _x,_x,_x,_x, _T,_x,_T,_x, _x,_x,_x,_x, _x,_x,_x}

	};
} // end Parser


class Errors {
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
			case 5: s = "eol expected"; break;
			case 6: s = "\"+-\" expected"; break;
			case 7: s = "\"=\" expected"; break;
			case 8: s = "\"[\" expected"; break;
			case 9: s = "\"]\" expected"; break;
			case 10: s = "\"fun\" expected"; break;
			case 11: s = "\"(\" expected"; break;
			case 12: s = "\",\" expected"; break;
			case 13: s = "\")\" expected"; break;
			case 14: s = "\"{\" expected"; break;
			case 15: s = "\"}\" expected"; break;
			case 16: s = "\"return\" expected"; break;
			case 17: s = "??? expected"; break;
			case 18: s = "invalid Expr"; break;
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


class FatalError extends RuntimeException {
	public static final long serialVersionUID = 1L;
	public FatalError(String s) { super(s); }
}
