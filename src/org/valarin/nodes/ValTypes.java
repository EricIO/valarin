package org.valarin.nodes;

import java.math.BigDecimal;
import java.math.BigInteger;

import org.valarin.runtime.*;
import com.oracle.truffle.api.dsl.ImplicitCast;
import com.oracle.truffle.api.dsl.TypeCheck;
import com.oracle.truffle.api.dsl.TypeSystem;
import com.oracle.truffle.api.dsl.internal.DSLOptions;


@TypeSystem({long.class, BigInteger.class, BigDecimal.class, boolean.class, String.class, ValFunction.class, ValNoneType.class})
@DSLOptions(useNewLayout = true)
public abstract class ValTypes {
	
	@TypeCheck(ValNoneType.class)
	public static boolean isNone(Object value) {
		return value == ValNoneType.NONE;
	}
	
	@ImplicitCast
	public static BigDecimal castBigDecima(double value) {
		return BigDecimal.valueOf(value);
	}
	
	@ImplicitCast
	public static BigInteger castBigInteger(long value) {
		return BigInteger.valueOf(value);
	}
}
