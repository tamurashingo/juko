/*-
 * The MIT License
 * 
 * Copyright (c) 2002, 2013 tamura shingo
 * 
 * Permission is hereby granted, free of charge, to any person obtaining
 * a copy of this software and associated documentation files (the
 * "Software"), to deal in the Software without restriction, including
 * without limitation the rights to use, copy, modify, merge, publish,
 * distribute, sublicense, and/or sell copies of the Software, and to
 * permit persons to whom the Software is furnished to do so, subject
 * to the following conditions:
 *
 * The above copyright notice and this permission notice shall be included
 * in all copies or substantial portions of the Software.
 *
 * THE SOFTWARE IS PROVIDED "AS IS", WITHOUT WARRANTY OF ANY KIND, EXPRESS
 * OR IMPLIED, INCLUDING BUT NOT LIMITED TO THE WARRANTIES OF MERCHANTABILITY,
 * FITNESS FOR A PARTICULAR PURPOSE AND NONINFRINGEMENT. IN NO EVENT SHALL
 * THE AUTHORS OR COPYRIGHT HOLDERS BE LIABLE FOR ANY CLAIM, DAMAGES OR
 * OTHER LIABILITY, WHETHER IN AN ACTION OF CONTRACT, TORT OR OTHERWISE,
 * ARISING FROM, OUT OF OR IN CONNECTION WITH THE SOFTWARE OR THE USE
 * OR OTHER DEALINGS IN THE SOFTWARE.
 * 
 */
package com.github.tamurashingo.juko.core;

import com.github.tamurashingo.juko.core.util.MessageProperty;

/**
 * Base for Exceptions thrown during running Juko application.
 * 
 * @version $Id$
 * @author tamura shingo
 *
 */
public class JukoException extends Exception {
	
	/**
	 * This exception {@code serialVersionUID}.
	 */
	private static final long serialVersionUID = 1L;
	
	/** the detail error message. */
	private MessageProperty msgProp;

	/**
	 * Construct a new <code>JukoException</code>.
	 */
	public JukoException() {
		super();
	}
	
	/**
	 * Construct a new <code>JukoException</code>
	 * with the specified short message.
	 * 
	 * @param message the short message
	 */
	public JukoException(String message) {
		super(message);
	}
	
	/**
	 * Construct a new <code>JukoException</code>
	 * with the specified message and cause.
	 * 
	 * @param message the short message
	 * @param cause the cause
	 */
	public JukoException(String message, Throwable cause) {
		super(message, cause);
	}
	
	/**
	 * Construct a new <code>JukoException</code>
	 * with the specified <code>MessageProperty</code>.
	 * @param msgProp the message property
	 */
	public JukoException(MessageProperty msgProp) {
		super();
		this.msgProp = msgProp;
	}
	
	/**
	 * Construct a new <code>JukoException</code>
	 * with the specified short message and <code>MessageProperty</code>.
	 * @param message the short message
	 * @param msgProp the message property
	 */
	public JukoException(String message, MessageProperty msgProp) {
		super(message);
		this.msgProp = msgProp;
	}
	
	/**
	 * Construct a new <code>JukoException</code>
	 * with the specified short message, cause, and <code>MessageProperty</code>.
	 * @param message the short message
	 * @param cause the cause
	 * @param msgProp the message property
	 */
	public JukoException(String message, Throwable cause, MessageProperty msgProp) {
		super(message, cause);
		this.msgProp = msgProp;
	}
	
	/**
	 * Construct a new <code>JukoException</code
	 * with the cause and <code>MessageProperty</code>.
	 * @param cause cause
	 * @param msgProp the message property
	 */
	public JukoException(Throwable cause, MessageProperty msgProp) {
		super(cause);
		this.msgProp = msgProp;
	}
	
	/**
	 * @return the message property
	 */
	public MessageProperty getMessageProperty() {
		return this.msgProp;
	}
}
