package org.ucll.demo;


public class ParameterizedIllegalArgumentException extends IllegalArgumentException {
	private static final long serialVersionUID = 1L;

	private Object[] parameters;
	
	public ParameterizedIllegalArgumentException(String message, Object... parameters){
		super(message);
		setParameters(parameters);
	}

	public Object[] getParameters() {
		return parameters;
	}

	private void setParameters(Object[] parameters) {
		this.parameters = parameters;
	}
}
