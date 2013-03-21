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
package test.com.github.tamurashingo.juko.core.bean;


import static org.junit.Assert.*;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;

import org.junit.Test;

import com.github.tamurashingo.juko.core.bean.Transform;
import com.github.tamurashingo.juko.core.bean.Triangle;

public class TriangleTest {
	
	Triangle triangle = new Triangle(
			new double[]{  0.0, -1.0, 0.0 },
			new double[]{  1.0,  1.0, 0.0 },
			new double[]{ -1.0,  1.0, 0.0 });


	@Test
	public void testTransform() {
		Transform move = new Transform(
				new double[][] {
						{ 1, 0, 0, 1 },
						{ 0, 1, 0, 2 },
						{ 0, 0, 1, 3 },
						{ 0, 0, 0, 1 },
				});
		Triangle moveT = triangle.transform( move );
		double[][] points = moveT.getPosition();
		
		assertArrayEquals( new double[]{ 1.0, 1.0, 3.0 }, points[0], 0.01 );
		assertArrayEquals( new double[]{ 2.0, 3.0, 3.0 }, points[1], 0.01 );
		assertArrayEquals( new double[]{ 0.0, 3.0, 3.0 }, points[2], 0.01 );
		
	}

	@Test
	public void testGetPosition() {
		double[][] points = triangle.getPosition();
		
		assertArrayEquals( new double[]{ 0.0, -1.0, 0.0 }, points[0], 0.01 );
		assertArrayEquals( new double[]{ 1.0, 1.0, 0.0 }, points[1], 0.01 );
		assertArrayEquals( new double[]{ -1.0, 1.0, 0.0 }, points[2], 0.01 );
	}
	
	@Test
	public void testSerialize() throws Exception {
		
		Triangle t = null;
		
		ByteArrayOutputStream outBuf = new ByteArrayOutputStream();
		try ( ObjectOutputStream out = new ObjectOutputStream( outBuf ) ) {
			out.writeObject( triangle );
			byte[] buf = outBuf.toByteArray();
			
			try ( ByteArrayInputStream inBuf = new ByteArrayInputStream( buf ) ) {
				ObjectInputStream in = new ObjectInputStream( inBuf );
				t = (Triangle)in.readObject();
			}
		}

		double[][] points = t.getPosition();
		
		assertArrayEquals( new double[]{ 0.0, -1.0, 0.0 }, points[0], 0.01 );
		assertArrayEquals( new double[]{ 1.0, 1.0, 0.0 }, points[1], 0.01 );
		assertArrayEquals( new double[]{ -1.0, 1.0, 0.0 }, points[2], 0.01 );
	}
	
	@Test
	public void testClone() throws Exception {
		Triangle t = (Triangle)triangle.clone();
		double[][] pOrigin = triangle.getPosition();
		double[][] pTarget = t.getPosition();
		
		t.setCalculatedArea(3.0);
		
		assertFalse("t is another instance.", triangle == t);
		assertArrayEquals(pOrigin[0], pTarget[0], 0.01);
		assertArrayEquals(pOrigin[1], pTarget[1], 0.01);
		assertArrayEquals(pOrigin[2], pTarget[2], 0.01);
		
		assertNotEquals(triangle.getCalculatedArea(), t.getCalculatedArea());
	}
	

}
