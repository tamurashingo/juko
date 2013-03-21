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
 * <p>
 * The <code>TransofromBean</code> class represents a transform matrix.
 * </p>
 * <p>
 * example.
 * </p>
 * <p>
 * <code><pre>
 * Transform rot = TransformUtil.createRotateX(radx);
 * Triangle t = new Triangle( ... );
 * 
 * Triangle rotT = t.transform( rot );
 * </pre></code>
 * </p>
 * 
 * @author tamura shingo
 *
 */
public class Transform implements java.io.Serializable {
	
	/** serialVersionUID */
	private static final long serialVersionUID = 1L;

	/** transform matrix */
	transient private RealMatrix m;
	
	/**
	 * constructor.
	 * @param m
	 */
	public Transform(double[][] m) {
		this.m = MatrixUtils.createRealMatrix(m);
	}
	
	/**
	 * constructor.
	 * @param m <code>RealMatrix</code> object
	 */
	public Transform(RealMatrix m) {
		this.m = m;
	}
	
	/**
	 * get the transform matrix.
	 * @return transform matrix
	 */
	public RealMatrix getMatrix() {
		return m;
	}
	
	/**
	 * create the new transform matrix.
	 * @param bean
	 * @return transform matrix
	 */
	public Transform multiply(Transform bean) {
		RealMatrix m2 = bean.getMatrix();
		return new Transform(this.m.multiply(m2));
	}
	
	
	/**
	 * serialize object.
	 * @param stream  output stream
	 * @throws IOException
	 */
	private void writeObject(ObjectOutputStream stream) throws IOException {
		stream.writeUTF("transform version:" + serialVersionUID);
		
		/*- data must be 4x4 */
		double[][] data = m.getData();
		/*- */
		stream.writeDouble(data[0][0]);
		stream.writeDouble(data[0][1]);
		stream.writeDouble(data[0][2]);
		stream.writeDouble(data[0][3]);
		/*- */
		stream.writeDouble(data[1][0]);
		stream.writeDouble(data[1][1]);
		stream.writeDouble(data[1][2]);
		stream.writeDouble(data[1][3]);
		/*- */
		stream.writeDouble(data[2][0]);
		stream.writeDouble(data[2][1]);
		stream.writeDouble(data[2][2]);
		stream.writeDouble(data[2][3]);
		/*- */
		stream.writeDouble(data[3][0]);
		stream.writeDouble(data[3][1]);
		stream.writeDouble(data[3][2]);
		stream.writeDouble(data[3][3]);
	}
	
	
	/**
	 * deserialize object.
	 * @param stream input stream
	 * @throws IOException
	 */
	private void readObject(ObjectInputStream stream) throws IOException {
		/* version info > /dev/null */
		stream.readUTF();
		
		double[][] d = {
				{ stream.readDouble(), 	
				  stream.readDouble(), 	
				  stream.readDouble(), 	
				  stream.readDouble(), },
				{ stream.readDouble(), 	
				  stream.readDouble(), 	
				  stream.readDouble(), 	
				  stream.readDouble(), },
				{ stream.readDouble(), 	
				  stream.readDouble(), 	
				  stream.readDouble(), 	
				  stream.readDouble(), },
				{ stream.readDouble(), 	
				  stream.readDouble(), 	
				  stream.readDouble(), 	
				  stream.readDouble(), },
		};
		
		m = MatrixUtils.createRealMatrix(d);
	}
}
