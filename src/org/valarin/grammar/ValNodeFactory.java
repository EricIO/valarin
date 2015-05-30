package org.valarin.grammar;


import org.valarin.nodes.*;
import org.valarin.nodes.expression.*;
import org.valarin.runtime.*;

import java.math.BigInteger;

public class ValNodeFactory {
    
    public ValExpressionNode createNumberLiteral(Token literal) {
        try {
            return new ValLongLiteralNode(Long.parseLong(literal.val));
        } catch (NumberFormatException overflow) {
            // If the literal is bigger than a long can hold fallback to BigInteger.
            return new ValBigIntegerLiteral(new BigInteger(literal.val));
        }
    }
    
    public ValStringLiteralNode createStringLiteral(Token literal) {
        // TODO: Parse the string to remove the " and ' chars that delimit them
        //       Special cases are "str\"ing" and 'str\'ing'
        return new ValStringLiteralNode(literal.val);
    }
    
    public ValExpressionNode createBinaryNode(Token op, ValExpressionNode left, ValExpressionNode right) {
        switch (op.val) {
            case "+":
                return ValAddNodeGen.create(left, right);
            case "-":
                return ValSubNodeGen.create(left, right);
            case "*":
                return ValMulNodeGen.create(left, right);
            case "/":
                return ValDivNodeGen.create(left, right);
            case "**":
                return ValPowerNodeGen.create(left, right);
            /*case "<":
                return ValLessThanGen.create(left, right);
            case ">":
                return ValGreaterThanGen.create(left, right);
            case "<=":
                return ValLessThanEqualGen.create(left, right);
            case ">=":
                return ValGreaterThanEqualGen.create(left, right);
            case "==":
                return ValEqualsGen.create(left, right);
            case "||":
                return ValLogicOrGen.create(left, right);
            case "&&":
                return ValLogicAndGen.create(left, right);*/
            default:
                throw new RuntimeException("Unknown operation: " + op.val);
        }
    }
}
