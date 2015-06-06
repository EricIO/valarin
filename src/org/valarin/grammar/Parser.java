
package org.valarin.grammar;

import org.valarin.nodes.*;
import org.valarin.nodes.expression.*;
import org.valarin.nodes.controlflow.*;
import org.valarin.runtime.*;
import org.valarin.grammar.*;

import java.util.ArrayList;

public class Parser {
	public static final int _EOF = 0;
	public static final int _ident = 1;
	public static final int _number = 2;
	public static final int _float = 3;
	public static final int _string = 4;
	public static final int _true = 5;
	public static final int _false = 6;
	public static final int _if = 7;
	public static final int _else = 8;
	public static final int _then = 9;
	public static final int _for = 10;
	public static final int _do = 11;
	public static final int _eol = 12;
	public static final int maxT = 25;

	static final boolean _T = true;
	static final boolean _x = false;
	static final int minErrDist = 2;

	public Token t;    // last recognized token
	public Token la;   // lookahead token
	int errDist = minErrDist;
	
	public Scanner scanner;
	public Errors errors;
	private final ValNodeFactory factory;
	public ValBodyNode root;
	

	public Parser(Scanner scanner) {
		this.scanner = scanner;
		this.factory = new ValNodeFactory();
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
		ArrayList<ValExpressionNode> nodes = new ArrayList<>(); 
		Expect(13);
		Expect(1);
		while (StartOf(1)) {
			ValExpressionNode result = Expr();
			nodes.add(result); 
		}
		root = new ValBodyNode(nodes.toArray(new ValStatementNode[nodes.size()])); 
	}

	ValExpressionNode  Expr() {
		ValExpressionNode  expr;
		expr = null; 
		if (StartOf(2)) {
			expr = Arithmetic();
			while (!(la.kind == 0 || la.kind == 14)) {SynErr(26); Get();}
			Expect(14);
		} else if (la.kind == 7) {
			expr = IfStmt();
		} else if (la.kind == 10) {
			expr = ForStmt();
		} else SynErr(27);
		return expr;
	}

	ValExpressionNode  Arithmetic() {
		ValExpressionNode  expr;
		expr = Term();
		while (StartOf(3)) {
			if (la.kind == 15) {
				Get();
			} else if (la.kind == 16) {
				Get();
			} else if (la.kind == 17) {
				Get();
			} else {
				Get();
			}
			Token op = t; 
			ValExpressionNode e2 = Term();
			expr = factory.createBinaryNode(op, expr, e2); 
		}
		return expr;
	}

	ValIfNode  IfStmt() {
		ValIfNode  ifNode;
		ValExpressionNode elseNode = null; 
		Expect(7);
		Expect(22);
		ValExpressionNode condNode = Expr();
		Expect(23);
		Expect(9);
		ValExpressionNode thenNode = Expr();
		if (la.kind == 8) {
			Get();
			elseNode = Expr();
		}
		ifNode = factory.createIfNode(condNode, thenNode, elseNode); 
		return ifNode;
	}

	ValForNode  ForStmt() {
		ValForNode  cond;
		Expect(10);
		Expect(22);
		ValExpressionNode initNode = Expr();
		Expect(14);
		ValExpressionNode condNode = Expr();
		Expect(14);
		ValExpressionNode nextNode = Expr();
		Expect(23);
		Expect(11);
		ValExpressionNode whileNode = Expr();
		cond = factory.createForNode(initNode,condNode, nextNode, whileNode); 
		return cond;
	}

	ValExpressionNode  Term() {
		ValExpressionNode  expr;
		expr = Factor();
		while (la.kind == 19 || la.kind == 20) {
			if (la.kind == 19) {
				Get();
			} else {
				Get();
			}
			Token op = t; 
			ValExpressionNode e2 = Factor();
			expr = factory.createBinaryNode(op, expr, e2); 
		}
		return expr;
	}

	ValExpressionNode  Factor() {
		ValExpressionNode  expr;
		expr = Power();
		while (la.kind == 21) {
			Get();
			Token op = t; 
			ValExpressionNode e2 = Power();
			expr = factory.createBinaryNode(op, expr, e2); 
		}
		return expr;
	}

	ValExpressionNode  Power() {
		ValExpressionNode  result;
		result = null; 
		switch (la.kind) {
		case 1: {
			Get();
			break;
		}
		case 2: {
			Get();
			result = factory.createNumberLiteral(t); 
			break;
		}
		case 5: case 6: {
			if (la.kind == 5) {
				Get();
			} else {
				Get();
			}
			result = factory.createBooleanLiteral(t); 
			break;
		}
		case 22: {
			Get();
			result = Arithmetic();
			Expect(23);
			break;
		}
		case 24: {
			Get();
			Token op = t; 
			ValExpressionNode expr = Arithmetic();
			result = factory.createUnaryNode(op, expr); 
			break;
		}
		case 4: {
			Get();
			result = factory.createStringLiteral(t); 
			break;
		}
		default: SynErr(28); break;
		}
		return result;
	}



	public void Parse() {
		la = new Token();
		la.val = "";		
		Get();
		Valarin();
		Expect(0);

	}

	private static final boolean[][] set = {
		{_T,_x,_x,_x, _x,_x,_x,_x, _x,_x,_x,_x, _x,_x,_T,_x, _x,_x,_x,_x, _x,_x,_x,_x, _x,_x,_x},
		{_x,_T,_T,_x, _T,_T,_T,_T, _x,_x,_T,_x, _x,_x,_x,_x, _x,_x,_x,_x, _x,_x,_T,_x, _T,_x,_x},
		{_x,_T,_T,_x, _T,_T,_T,_x, _x,_x,_x,_x, _x,_x,_x,_x, _x,_x,_x,_x, _x,_x,_T,_x, _T,_x,_x},
		{_x,_x,_x,_x, _x,_x,_x,_x, _x,_x,_x,_x, _x,_x,_x,_T, _T,_T,_T,_x, _x,_x,_x,_x, _x,_x,_x}

	};
} // end Parser

/*
public class Errors implements ErrorInterface{
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
*/

class FatalError extends RuntimeException {
	public static final long serialVersionUID = 1L;
	public FatalError(String s) { super(s); }
}
