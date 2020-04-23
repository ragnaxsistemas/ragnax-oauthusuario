package com.ragnax.ragnaxoauthusuario.exception;

import java.io.Serializable;

public class LogicaImplException extends Exception implements Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = 7672936385153673862L;

	public LogicaImplException(){
		super();
	}

	public LogicaImplException(String message){
		super(message);
	}
	
	public LogicaImplException(String message, Throwable cause){
		super(message, cause);
	}
	
	
	public LogicaImplException(Throwable cause){
		super(cause);
	}
}
