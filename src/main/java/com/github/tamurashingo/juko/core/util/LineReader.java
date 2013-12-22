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

import java.io.BufferedReader;
import java.io.IOException;

/**
 * this class provides the feature for reading lines and counting newlines.
 * 
 * @author tamura shingo
 *
 */
public class LineReader implements AutoCloseable {
	
	private BufferedReader in;
	private int line = 0;
	
	/**
	 * Constructor.
	 * @param in input data
	 */
	public LineReader( BufferedReader in ) {
		this.in = in;
	}
	
	/**
	 * Reads a line of text. A new line is considered to be terminated
	 * by any one of a line feed('\n'), a carriage return('\r'), or a
	 * carriage return followed immediately by a linefeed.
	 * 
	 * @return A {@code String} containing the contents of the line,
	 *         not including any line-termination characters,
	 *         or null if the end of the stream has been reached
	 * @throws LineReaderException if an I/O error occurs
	 * @see {@link java.io.BufferedReader#readLine()}
	 */
	public String readLine() throws LineReaderException {
		try {
			String str = in.readLine();
			if (str != null) {
				this.line++;
			}
			return str;
		}
		catch ( IOException ex ) {
			throw new LineReaderException( ex, new MessageProperty("E.IO.000030", line) ); 
		}
	}
	
	/**
	 * get the line count.
	 * @return line count
	 */
	public int getLineCount() {
		return this.line;
	}

	@Override
	public void close() throws Exception {
		in.close();
	}
	
}
