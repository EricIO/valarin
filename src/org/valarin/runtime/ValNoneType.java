package org.valarin.runtime;

/**
 * This is the None (or null) type for valarin. According to the Truffle API
 * it is discouraged to use the java null value for the language null.
 */
public final class ValNoneType {

	public static final ValNoneType NONE = new ValNoneType();
	
	private ValNoneType() { }
	
	@Override
	public String toString() {
		return "None";
	}
}
