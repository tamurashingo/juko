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
package com.github.tamurashingo.juko.core.reader;

import java.util.List;

import com.github.tamurashingo.juko.core.bean.PlantObject;


/**
 * reader interface for creating a {@code PlantObject}.
 * @author tamura shingo
 *
 */
public interface PlantObjectReader {
	
	/**
	 * prepare inner state for {@code read}.
	 * <p>
	 * example:
	 * <ul>
	 * <li>open file</li>
	 * <li>open socket</li>
	 * <li>open database connection</li>
	 * <li>...</li>
	 * </ul>
	 * </p>
	 * 
	 * @param args parameter for open
	 * @throws PlantObjectReaderException if some error occurs.
	 */
	public void open(String... args) throws PlantObjectReaderException;
	
	
	/**
	 * read some data and creates {@code PlantObject}s.
	 * 
	 * @return created {@code PlantObject}s
	 * @throws PlantObjectReaderException if some error occurs.
	 */
	public List<PlantObject> read() throws PlantObjectReaderException;

	
	/**
	 * ends {@code read}.
	 * <p>
	 * example:
	 * <ul>
	 * <li>close file</li>
	 * <li>close socket</li>
	 * <li>close database connection</li>
	 * <li>...</li>
	 * </p>
	 * 
	 * @throws PlantObjectReaderException if some error occurs.
	 */
	public void close() throws PlantObjectReaderException;
}
