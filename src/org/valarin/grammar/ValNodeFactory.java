package org.valarin.grammar;


import com.oracle.truffle.api.frame.FrameDescriptor;
import com.oracle.truffle.api.frame.FrameSlot;
import org.apfloat.Apint;
import org.valarin.nodes.*;
import org.valarin.nodes.expression.*;
import org.valarin.nodes.controlflow.*;
import org.valarin.runtime.*;

import java.util.HashMap;
import java.util.Map;

public class ValNodeFactory {
    
    static class Scope {
        protected final Scope outer;
        protected final Map<String, FrameSlot> mappings;

        Scope(Scope outer) {
            this.outer    = outer;
            this.mappings = new HashMap<>();
            if (outer != null)
                mappings.putAll(outer.mappings);
        }
    }
    
    // Global state
    private ValContext executionContext = new ValContext();
    private FrameDescriptor globalFrameDescriptor = new FrameDescriptor();
    private Scope globalScope                     = new Scope(null);
    
    public ValExpressionNode createNumberLiteral(Token literal) {
        // TODO: In order to get the ability to express binary, octal and hex number
        //       we need to parse the Token. 0b0101 should be parsed as 0101 and then
        //       we can call Long.parseLong("0101", 2).
        try {
            return new ValLongLiteralNode(Long.parseLong(literal.val));
        } catch (NumberFormatException overflow) {
            // If the literal is bigger than a long can hold fallback to Apint.
            return new ValApintLiteral(new Apint(literal.val));
        }
    }
    
    public ValStringLiteralNode createStringLiteral(Token literal) {
        // TODO: Parse the string to remove the " and ' chars that delimit them
        //       Special cases are "str\"ing" and 'str\'ing'
        String value=literal.val;
        String quote=value.substring(0,1);
        value=value.substring(1,value.length()-1);
        value=value.replace('\\'+quote, quote);
        value=value.replace("\\\\", "\\");
        return new ValStringLiteralNode(literal.val);
    }
    
    public ValBooleanLiteralNode createBooleanLiteral(Token literal) {
        switch (literal.val) {
            case "true":
                return new ValBooleanLiteralNode(true);
            case "false":
                return new ValBooleanLiteralNode(false);
        }
        
        return null;
    }
    
    public ValExpressionNode createCallNode(ValExpressionNode function, ValExpressionNode[] parameters) {
        return new ValInvokeNode(function, parameters);
    }

    public ValExpressionNode createRead(Token name) {
        return ValGlobalReadNodeGen.create(name.val, executionContext.getRegistry());
    }

    public ValExpressionNode createAssignment(Token name, ValExpressionNode value) {
        return ValGlobalAssignNodeGen.create(value, executionContext.getRegistry(), name.val);
    }

    public ValIfNode createIfNode(ValExpressionNode condNode, ValExpressionNode thenNode, ValExpressionNode elseNode) {
        return new ValIfNode(condNode,thenNode,elseNode);
    }

    public ValForNode createForNode(ValExpressionNode initNode, ValExpressionNode condNode, ValExpressionNode nextNode,ValExpressionNode whileNode) {
        return new ValForNode(initNode,  condNode,  nextNode, whileNode);
    }

    public ValExpressionNode createUnaryNode(Token op, ValExpressionNode node) {
        switch (op.val) {
            case "!":
                return ValNotNodeGen.create(node);
            case "-":
                return ValNegateNodeGen.create(node);
        }

        return null;
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
            case "<":
                return ValLessThanNodeGen.create(left, right);
            case ">":
                return ValGreaterThanNodeGen.create(left, right);
            case "<=":
                return ValLessThanEqualNodeGen.create(left, right);
            case ">=":
                return ValGreaterThanEqualNodeGen.create(left, right);
            case "==":
                return ValEqualsNodeGen.create(left, right);
            case "||":
                return ValLogicOrNodeGen.create(left, right);
            case "&&":
                return ValLogicAndNodeGen.create(left, right);
            default:
                throw new RuntimeException("Unknown operation: " + op.val);
        }
    }
}
