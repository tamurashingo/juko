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
package com.github.tamurashingo.juko.core.calculate.impl;

import java.util.ArrayList;
import java.util.List;

import com.github.tamurashingo.juko.core.bean.PlantObject;
import com.github.tamurashingo.juko.core.bean.Triangle;
import com.github.tamurashingo.juko.core.calculate.CalcIF;

/**
 * Calculate an area of plants, out of consideration of overlap among each objects.
 * 
 * 1/2|{A_{x}B_{y} - A_{y}B_{x}|
 * 
 * @author tamura shingo
 *
 */
public class CalcIncludeOverlap implements CalcIF {

	@Override
	public List<PlantObject> calc(List<PlantObject> plants) {
		List<PlantObject> list = new ArrayList<>(plants.size());
		for (PlantObject plant: plants) {
			PlantObject p = (PlantObject)plant.clone();
			calc(p);
			list.add(p);
		}
		return list;
	}
	

	/**
	 * calculate an area of triangles.
	 * @param plant
	 */
	private void calc(PlantObject plant) {
		for (Triangle t: plant.getTriangles()) {
			double[][] v = t.getPosition();
			
			// AB-> = (b1-a1, b2-a2)
			// AC-> = (c1-a1, c2-a2)
			// S = 1/2 AB-> AC->
			// S = 1/2 |AB_{x}AC_{y} - AB_{y}AC_{x}|
			
			double ABx = v[1][0] - v[0][0];
			double ABy = v[1][1] - v[0][1];
			double ACx = v[2][0] - v[0][0];
			double ACy = v[2][1] - v[0][1];
			
			t.setCalculatedArea(Math.abs(ABx*ACy - ABy*ACx) / 2.0);
		}
	}
}
