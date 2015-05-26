package org.valarin.nodes;

import java.math.BigInteger;

import org.valarin.runtime.ValNoneType;

import com.oracle.truffle.api.dsl.TypeSystem;


@TypeSystem({long.class, boolean.class, BigInteger.class, String.class, ValNoneType.class, ValFunction.class, ValArray.class})
public abstract class ValTypes {
	

}
