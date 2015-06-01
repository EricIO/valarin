package org.valarin.nodes;

import java.math.BigDecimal;
import java.math.BigInteger;

import com.oracle.truffle.api.dsl.TypeSystemReference;
import com.oracle.truffle.api.frame.VirtualFrame;
import com.oracle.truffle.api.nodes.UnexpectedResultException;
import org.apfloat.Apint;
import org.valarin.nodes.*;
import org.valarin.runtime.*;

@TypeSystemReference(ValTypes.class)
public abstract class ValExpressionNode extends ValStatementNode {

	public abstract Object executeGeneric(VirtualFrame frame); 
	
	@Override
	public void executeVoid(VirtualFrame frame) {
		executeGeneric(frame);
	}
	
	/* We need to provide execute methods for specialized types. A type that ends with
	 * Gen is a generated class by the truffle api from the @TypeSystem annotation in ValTypes.java
	 */
	
	public long executeLong(VirtualFrame frame) throws UnexpectedResultException {
		return ValTypesGen.expectLong(executeGeneric(frame));
	}
	
	public boolean executeBoolean(VirtualFrame frame) throws UnexpectedResultException {
		return ValTypesGen.expectBoolean(executeGeneric(frame));
	}
	
	public Apint executeApint(VirtualFrame frame) throws UnexpectedResultException {
		return ValTypesGen.expectApint(executeGeneric(frame));
	}
	
	public BigDecimal executeBigDecimal(VirtualFrame frame)throws UnexpectedResultException{
		return ValTypesGen.expectBigDecimal(executeGeneric(frame));
	}
	
	public ValFunction executeFunction(VirtualFrame frame) throws UnexpectedResultException {
		return ValTypesGen.expectValFunction(executeGeneric(frame));
	}
}
