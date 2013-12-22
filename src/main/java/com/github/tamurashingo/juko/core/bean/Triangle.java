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

import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;

import org.apache.commons.math3.linear.MatrixUtils;
import org.apache.commons.math3.linear.RealMatrix;


/**
 * this class represents a triangle in 3-dimensional coordinate space.
 * 
 * @author tamura shingo
 *
 */
public class Triangle implements java.io.Serializable, Cloneable {
	
	/** serialVersionUID */
	private static final long serialVersionUID = 1L;
	
	/** vertex 1 */
	transient private RealMatrix v1;
	/** vertex 2 */
	transient private RealMatrix v2;
	/** vertex 3 */
	transient private RealMatrix v3;
	
	/** area */
	private double calculatedArea;
	
	
	/**
	 * create a new triangle.
	 * @param vertex1 vertex of the triangle. array contains the position of X, Y, Z.
	 * @param vertex2 vertex of the triangle. array contains the position of X, Y, Z.
	 * @param vertex3 vertex of the triangle. array contains the position of X, Y, Z.
	 */
	public Triangle(double[]vertex1, double[]vertex2, double[]vertex3) {
		double[][] _v1 = { {vertex1[0]}, {vertex1[1]}, {vertex1[2]}, {1} };
		double[][] _v2 = { {vertex2[0]}, {vertex2[1]}, {vertex2[2]}, {1} };
		double[][] _v3 = { {vertex3[0]}, {vertex3[1]}, {vertex3[2]}, {1} };
		
		v1 = MatrixUtils.createRealMatrix(_v1);
		v2 = MatrixUtils.createRealMatrix(_v2);
		v3 = MatrixUtils.createRealMatrix(_v3);
	}
	
	
	/**
	 * create a new triangle.
	 * @param v1
	 * @param v2
	 * @param v3
	 */
	public Triangle(RealMatrix v1, RealMatrix v2, RealMatrix v3) {
		this.v1 = v1;
		this.v2 = v2;
		this.v3 = v3;
	}
	
	
	/**
	 * create a new transformed triangle.
	 * @param transform transform matrix
	 * @return transformed triangle
	 */
	public Triangle transform(Transform transform) {
		RealMatrix m = transform.getMatrix();
		RealMatrix _v1 = m.multiply(v1);
		RealMatrix _v2 = m.multiply(v2);
		RealMatrix _v3 = m.multiply(v3);
		
		return new Triangle(_v1, _v2, _v3);
	}
	
	/**
	 * @param area the calculated area
	 */
	public void setCalculatedArea(double area) {
		this.calculatedArea = area;
	}
	
	/**
	 * @return the calculated area
	 */
	public double getCalculatedArea() {
		return this.calculatedArea;
	}
	
	
	/**
	 * returns the vertexes of the triangle.
	 * @return array of vertexes
	 */
	public double[][] getPosition() {
		return new double[][] {
				getPosition(v1),
				getPosition(v2),
				getPosition(v3),
		};
	}
	
	
	/**
	 * returns the address about <code>RealMatrix</code> object.
	 * 
	 * @param v target <code>RealMatrix</code>
	 * @return array about the position of X, Y, Z.
	 */
	private double[] getPosition(RealMatrix v) {
		return new double[]{ 
				v.getRow(0)[0],
				v.getRow(1)[0],
				v.getRow(2)[0],
		};
	}
	
	/**
	 * serialize object.
	 * 
	 * @serialData save v1, v2, v3 raw data.
	 * @param stream
	 * @throws IOException
	 */
	private void writeObject(ObjectOutputStream stream) throws IOException {
		stream.writeUTF("triangle version:" + serialVersionUID);
		
		/*- v1 */
		stream.writeDouble(v1.getRow(0)[0]);
		stream.writeDouble(v1.getRow(1)[0]);
		stream.writeDouble(v1.getRow(2)[0]);
		/*- v2 */
		stream.writeDouble(v2.getRow(0)[0]);
		stream.writeDouble(v2.getRow(1)[0]);
		stream.writeDouble(v2.getRow(2)[0]);
		/*- v3 */
		stream.writeDouble(v3.getRow(0)[0]);
		stream.writeDouble(v3.getRow(1)[0]);
		stream.writeDouble(v3.getRow(2)[0]);
	}
	
	
	/**
	 * deserialize object.
	 * 
	 * @serialData load v1, v2, v3 raw data and generate.
	 * @param stream
	 * @throws IOException
	 */
	private void readObject(ObjectInputStream stream) throws IOException {
		/* version info > /dev/null */
		stream.readUTF();
		
		double[][] _v1 = {
				{ stream.readDouble() },
				{ stream.readDouble() },
				{ stream.readDouble() },
				{ 1 }
		};
		double[][] _v2 = {
				{ stream.readDouble() },
				{ stream.readDouble() },
				{ stream.readDouble() },
				{ 1 }
		};
		double[][] _v3 = {
				{ stream.readDouble() },
				{ stream.readDouble() },
				{ stream.readDouble() },
				{ 1 }
		};
		
		v1 = MatrixUtils.createRealMatrix(_v1);
		v2 = MatrixUtils.createRealMatrix(_v2);
		v3 = MatrixUtils.createRealMatrix(_v3);
	}
	
	
	@Override
	public Object clone() {
		try {
			Triangle t = (Triangle) super.clone();
			t.v1 = this.v1.copy();
			t.v2 = this.v2.copy();
			t.v3 = this.v3.copy();
			return t;
		}
		catch (CloneNotSupportedException ex) {
			throw new RuntimeException(ex);
		}
	}

}

