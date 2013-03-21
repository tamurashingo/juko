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

import java.util.Iterator;
import java.util.LinkedList;
import java.util.NoSuchElementException;

import com.github.tamurashingo.juko.core.bean.PlantObject;
import com.github.tamurashingo.juko.core.bean.Triangle;

/**
 * read the tsv file that was used in my research.
 *
 * read nine vertexes and create 7 triangle for one leaf.
 * <pp><pre>
 *      v9
 *     /  \
 *    / L7 \
 *  v8------v7
 *   |  L6 /|
 *   |  _/  |
 *   |/ L5  |
 *  v6------v5
 *   |  L4 /|
 *   |  _/  |
 *   |/ L3  |
 *  v4------v3
 *   |  L2 /|
 *   |  _/  |
 *   |/ L1  |
 *  v2------v1
 * </pre></pp>
 * 
 * @author tamura shingo
 *
 */
public class SotsuronResearchReader extends SimpleReader {
	
	@Override
	protected PlantObject createPlant(LinkedList<double[]> points) {
		PlantObject plant = new PlantObject();

		Iterator<double[]> it = points.iterator();
		double[][][] vertexes = null;
		while ((vertexes = getLeaf(it)) != null) {
			for (double[][] v: vertexes) {
				plant.addTriangle(createTriangle(v));
			}
		}

		return plant;
	}
	
	/**
	 * get next leaf vertexes.
	 * @param it iterator from {@code LinkedList<double[]>}
	 * @return leaf vertexes. if {@code it} has no item, returns null.
	 */
	protected double[][][] getLeaf(Iterator<double[]> it) {
		try {
			double[] v1 = it.next();
			double[] v2 = it.next();
			double[] v3 = it.next();
			double[] v4 = it.next();
			double[] v5 = it.next();
			double[] v6 = it.next();
			double[] v7 = it.next();
			double[] v8 = it.next();
			double[] v9 = it.next();
			return new double[][][] {
					 {v1, v2, v3},
					 {v2, v3, v4},
					 {v3, v4, v5},
					 {v4, v5, v6},
					 {v5, v6, v7},
					 {v6, v7, v8},
					 {v7, v8, v9},
			};
		}
		catch (NoSuchElementException ex) {
			return null;			
		}
	}
	
	
	/**
	 * create a triangle object from 3 vertexes.
	 * @param v arrays of vertex
	 * @return {@code Triangle} object
	 */
	protected Triangle createTriangle(double[][] v) {
		return new Triangle(v[0], v[1], v[2]);
	}

}
