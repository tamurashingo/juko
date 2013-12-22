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
package com.github.tamurashingo.juko.core.bean;

import java.util.ArrayList;
import java.util.List;


/**
 * The <code>PlantObject</code> class is the class of all 3D objects in the virtual field.
 * @author tamura shingo
 *
 */
public class PlantObject implements java.io.Serializable, Cloneable {
	
	/** serialVersionUID */
	private static final long serialVersionUID = 1L;
	
	protected List<Triangle> triangles = new ArrayList<>();
	protected PlantType plantType;
	
	/**
	 * @author tamura shingo
	 *
	 */
	public static enum PlantType {
		/** include the calculated area */
		LEAF,
		/** exclude the calculated area */
		SHIELD,
	}
	
	/**
	 * @return the triangles
	 */
	public List<Triangle> getTriangles() {
		return triangles;
	}

	/**
	 * add triangle to this plant.
	 * @param triangle the triangle
	 */
	public void addTriangle(Triangle triangle) {
		this.triangles.add(triangle);
	}
	
	/**
	 * @return the plantType
	 */
	public PlantType getPlantType() {
		return plantType;
	}
	/**
	 * @param plantType the plantType to set
	 */
	public void setPlantType(PlantType plantType) {
		this.plantType = plantType;
	}
	
	@Override
	public Object clone() {
		try {
			PlantObject p = (PlantObject)super.clone();
			p.triangles = new ArrayList<>(this.triangles.size());
			for (Triangle triangle: this.triangles) {
				p.triangles.add((Triangle)triangle.clone());
			}
			return p;
		}
		catch (CloneNotSupportedException ex) {
			throw new RuntimeException(ex);
		}
	}
}
