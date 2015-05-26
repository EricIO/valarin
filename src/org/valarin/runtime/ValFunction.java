package org.valarin.runtime;

import com.oracle.truffle.api.RootCallTarget;

public class ValFunction {
	
	private final String name;
	public RootCallTarget callTarget;
	
	public ValFunction(String name) {
		this.name = name;
	}
	
	public String getName() {
		return name;
	}

	public void setCallTarget(RootCallTarget callTarget) {
		this.callTarget = callTarget;
	}

}
