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
package com.github.tamurashingo.juko.core.util;

import com.github.tamurashingo.juko.core.JukoException;

/**
 * Signals that an I/O exception has occurred.
 * 
 * @author tamura shingo
 *
 */
public class LineReaderException extends JukoException {
	
	/**
	 * This exception {@code serialVersionUID}.
	 */
	private static final long serialVersionUID = 1L;
	
	/**
	 * Construct a new <code>LineReaderException</code>
	 */
	public LineReaderException() {
		super();
	}
	
	/**
	 * Construct a new <code>LineReaderException</code>
	 * with the specified short message.
	 * 
	 * @param message the short message
	 */
	public LineReaderException(String message) {
		super(message);
	}
	
	/**
	 * Construct a new <code>LineReaderException</code>
	 * with the specified message and cause.
	 * 
	 * @param message the short message
	 * @param cause the cause
	 */
	public LineReaderException(String message, Throwable cause) {
		super(message, cause);
	}
	
	/**
	 * Construct a new <code>LineReaderException</code>
	 * with the specified <code>MessageProperty</code>.
	 * 
	 * @param msgProp the message property
	 */
	public LineReaderException(MessageProperty msgProp) {
		super(msgProp);
	}
	
	/**
	 * Construct a new <code>LineReaderException</code>
	 * with the specified short message and <code>MessageProperty</code>.
	 * 
	 * @param message the short message
	 * @param msgProp the message property
	 */
	public LineReaderException(String message, MessageProperty msgProp) {
		super(message, msgProp);
	}
	
	/**
	 * Construct a new <code>LineReaderException</code>
	 * with the specified short message, cause, and <code>MessageProperty</code>.
	 * 
	 * @param message the short message
	 * @param cause the cause
	 * @param msgProp the message property
	 */
	public LineReaderException(String message, Throwable cause, MessageProperty msgProp) {
		super(message, msgProp);
	}
	
	/**
	 * Construct a new <code>LineReaderException</code>
	 * with the cause and <code>MessageProperty</code>.
	 * @param cause the cause
	 * @param msgProp the message property
	 */
	public LineReaderException(Throwable cause, MessageProperty msgProp) {
		super(cause, msgProp);
	}
}
