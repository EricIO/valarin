package org.valarin.runtime;

import com.oracle.truffle.api.RootCallTarget;
import com.oracle.truffle.api.interop.ForeignAccessFactory;
import com.oracle.truffle.api.interop.TruffleObject;

public final class ValFunction implements TruffleObject {
	
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

	@Override
	public ForeignAccessFactory getForeignAccessFactory() {
		return null;
	}
}
