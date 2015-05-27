package org.valarin.grammar;


import org.valarin.nodes.ValExpressionNode;

public class ValNodeFactory {
    
    public ValExpressionNode createBinary(Token op, ValExpressionNode left, ValExpressionNode right) {
        switch (op.val) {
            case "+":
                return ValAddNodeGen.create(left, right);
            case "-":
                return ValSubNodeGen.create(left, right);
            case "*":
                return ValMulNodeGen.create(left, right);
            case "/":
                return ValDivNodeGen.create(left, right);
            /*case "**":
                return ValPowerNodeGen.create(left, right);
            case "<":
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
