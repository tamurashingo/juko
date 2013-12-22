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
package com.github.tamurashingo.juko.core.reader.impl;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;
import java.util.NoSuchElementException;

import com.github.tamurashingo.juko.core.bean.PlantObject;
import com.github.tamurashingo.juko.core.bean.Triangle;
import com.github.tamurashingo.juko.core.bean.PlantObject.PlantType;
import com.github.tamurashingo.juko.core.reader.PlantObjectReader;
import com.github.tamurashingo.juko.core.reader.PlantObjectReaderException;
import com.github.tamurashingo.juko.core.util.LineReader;
import com.github.tamurashingo.juko.core.util.LineReaderException;
import com.github.tamurashingo.juko.core.util.MessageProperty;


/**
 * sample implementation.
 * this class loads TSV(tab separeted values) files and create {@code PlantObject}s.
 *
 * @author tamura shingo
 *
 */
public class SimpleReader implements PlantObjectReader {
	
	/**
	 * given files.
	 */
	protected String[] files;

	@Override
	public List<PlantObject> read() throws PlantObjectReaderException {
		List<PlantObject> plants = new ArrayList<>();
		
		for (String file: files) {
			try (LineReader reader = new LineReader(new BufferedReader(new FileReader(file)))) {
				PlantObject p = readMain(reader);
				plants.add(p);
			}
			catch (IOException ex) {
				throw new PlantObjectReaderException(ex, new MessageProperty("E.IO.000010", file));
			}
			catch (Exception ex) {
				throw new PlantObjectReaderException(ex, new MessageProperty("E.IO.000000", file));
			}
		}
		
		return plants;
	}

	@Override
	public void open(String... args) throws PlantObjectReaderException {
		this.files = args;
	}

	@Override
	public void close() throws PlantObjectReaderException {
		// nothing
	}
	
	
	/**
	 * create a {@code PlantObject} from a TSV file.
	 * @param reader LineReader
	 * @return PlantObject created from a TSV file.
	 * @throws PlantObjectReaderException
	 */
	protected PlantObject readMain(LineReader reader) throws PlantObjectReaderException {
		try {
			LinkedList<double[]> rawPoints = readAllPoints(reader);
			LinkedList<double[]> normalizedPoints = originShift(rawPoints);
			PlantObject plant = createPlant(normalizedPoints);
			
			return plant;
		}
		catch (LineReaderException ex) {
			throw new PlantObjectReaderException(ex, new MessageProperty(""));
		}
	}
	
	/**
	 * read vertex informations.
	 *  
	 * @param reader LineReader
	 * @return vertex informations
	 * @throws LineReaderException if occurs an IO error.
	 * @throws PlantObjectReaderException if occurs a format error.
	 */
	protected LinkedList<double[]> readAllPoints(LineReader reader) throws LineReaderException, PlantObjectReaderException {
		double[] p;
		LinkedList<double[]> points = new LinkedList<>();
		while ((p = readPoint(reader)) != null) {
			points.add(p);
		}
		return points;
	}
	
	/**
	 * move vertexes to origin.
	 * last item is center vertexes.
	 * 
	 * @param points raw vertexes
	 * @return moved vertexes
	 */
	protected LinkedList<double[]> originShift(LinkedList<double[]> points) {
		// get origin address;
		double[] origin = points.peekLast();
		LinkedList<double[]> newPoints = new LinkedList<>();
		
		for (double[] p: points) {
			double[] newP = new double[] {
					p[0] - origin[0],
					p[1] - origin[1],
					p[2] - origin[2],
			};
			newPoints.add(newP);
		}
		
		return newPoints;
	}
	
	/**
	 * create a {@code PlantObject} from vertexes.
	 * {@code PlantType} is {@code LEAF}.
	 * 
	 * @param points vertexes
	 * @return PlantObject
	 */
	protected PlantObject createPlant(LinkedList<double[]> points) {
		PlantObject plant = new PlantObject();
		plant.setPlantType(PlantType.LEAF);
		
		Iterator<double[]> it = points.iterator();
		Triangle t = null;
		while ((t = createTriangle(it)) !=null) {
			plant.addTriangle(t);
		}
		
		return plant;
	}
	
	
	/**
	 * create a triangle object from 3 vertexes.
	 * 
	 * @param it iterator about vertex object.
	 * @return {@code Triangle} object. if {@code it} has no item, returns null.
	 */
	protected Triangle createTriangle(Iterator<double[]> it) {
		try {
			Triangle t = new Triangle(it.next(), it.next(), it.next());
			return t;
		}
		catch (NoSuchElementException ex) {
			return null;
		}
	}
	
	/**
	 * read a line and convert to array of double.
	 * @param reader LineReader
	 * @return array of double(vertex information). if EOF, return null.
	 * @throws LineReaderException if IO error occurs.
	 * @throws PlantObjectReaderException if format error occurs.
	 */
	protected double[] readPoint(LineReader reader) throws LineReaderException, PlantObjectReaderException {
		String line = readLine(reader);
		
		// EOF
		if (line == null) {
			return null;
		}

		String[] items = line.split("\t");
		
		// must have 3 items.
		if (items.length != 3) {
			throw new PlantObjectReaderException( "FormatError", new MessageProperty(""));
		}
		
		double[] points = new double[items.length];
		for (int ix = 0; ix < items.length; ix++) {
			points[ix] = Double.parseDouble(items[ix]);
		}

		return points;
	}
	
	/**
	 * get a line from a TSV file.
	 * @param reader LineReader
	 * @return a line. if EOF return null.
	 * @throws LineReaderException if IO error occurs.
	 */
	protected String readLine(LineReader reader) throws LineReaderException {
		String line = null;
		boolean loopFlag = true;
		
		while (loopFlag) {
			line = reader.readLine();
			if (line == null) {
				// EOF
				break;
			}
			if (line.length() > 0) {
				// get some items.
				break;
			}
			// when blank line, get next line
		}
		return line;
	}
}
