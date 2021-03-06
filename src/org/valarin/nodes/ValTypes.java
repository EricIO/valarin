package org.valarin.nodes;

import java.math.BigDecimal;

import org.apfloat.Apint;
import org.valarin.runtime.*;
import com.oracle.truffle.api.dsl.ImplicitCast;
import com.oracle.truffle.api.dsl.TypeCheck;
import com.oracle.truffle.api.dsl.TypeSystem;
import com.oracle.truffle.api.dsl.internal.DSLOptions;

@TypeSystem({long.class, Apint.class, BigDecimal.class, boolean.class, String.class, ValFunction.class, ValNoneType.class})
@DSLOptions(useNewLayout = true)
public abstract class ValTypes {
	
	@TypeCheck(ValNoneType.class)
	public static boolean isNone(Object value) {
		return value == ValNoneType.NONE;
	}
	
	public static ValNoneType asNone(Object value) {
		assert isNone(value);
		return ValNoneType.NONE;
	}
	
	@ImplicitCast
	public static BigDecimal castBigDecimal(double value) {
		return BigDecimal.valueOf(value);
	}
	
	@ImplicitCast
	public static Apint castApint(long value) {
		return new Apint(value);
	}
}
