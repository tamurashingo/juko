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

import org.junit.Test;

import com.github.tamurashingo.juko.core.bean.Transform;
import com.github.tamurashingo.juko.core.bean.TransformUtil;
import com.github.tamurashingo.juko.core.bean.Triangle;

public class TransformUtilTest {

	/*-
	 *     y     _z
	 *    /|     /
	 *     |    /
	 *     |  /
	 *     |/
	 *   --+--------->x
	 *    /|
	 */
	Triangle triangle = new Triangle(
			new double[]{  0.0,  1.0, 0.0 },
			new double[]{  1.0, -1.0, 0.0 },
			new double[]{ -1.0, -1.0, 0.0 });
	
	@Test
	public void testCreateRotateX() {
		Transform rotx = TransformUtil.createRotateX( 90.0 * Math.PI / 180.0 );
		Triangle newTriangle = triangle.transform( rotx );
		double[][] point = newTriangle.getPosition();
		
		assertArrayEquals( new double[]{  0.0, 0.0,  1.0 }, point[0], 0.01 );
		assertArrayEquals( new double[]{  1.0, 0.0, -1.0 }, point[1], 0.01 );
		assertArrayEquals( new double[]{ -1.0, 0.0, -1.0 }, point[2], 0.01 );
	}

	@Test
	public void testCreateRotateY() {
		Transform roty = TransformUtil.createRotateY( 90.0 * Math.PI / 180.0 );
		Triangle newTriangle = triangle.transform( roty );
		double[][] point = newTriangle.getPosition();
		
		assertArrayEquals( new double[]{  0.0,  1.0,   0.0 }, point[0], 0.01 );
		assertArrayEquals( new double[]{  0.0, -1.0,  -1.0 }, point[1], 0.01 );
		assertArrayEquals( new double[]{  0.0, -1.0,   1.0 }, point[2], 0.01 );
	}


	@Test
	public void testCreateRotateZ() {
		Transform rotz = TransformUtil.createRotateZ( 90.0 * Math.PI / 180 );
		Triangle newTriangle = triangle.transform( rotz );
		double[][] point = newTriangle.getPosition();
		
		assertArrayEquals( new double[]{ -1.0,  0.0, 0.0 }, point[0], 0.01 );
		assertArrayEquals( new double[]{  1.0,  1.0, 0.0 }, point[1], 0.01 );
		assertArrayEquals( new double[]{  1.0, -1.0, 0.0 }, point[2], 0.01 );
	}

	@Test
	public void testCreateTranslate() {
		Transform translate = TransformUtil.createTranslate( 1.0, 2.0, 3.0 );
		Triangle newTriangle = triangle.transform( translate );
		double[][] point = newTriangle.getPosition();
		
		assertArrayEquals( new double[]{  1.0,  3.0, 3.0 }, point[0], 0.01 );
		assertArrayEquals( new double[]{  2.0,  1.0, 3.0 }, point[1], 0.01 );
		assertArrayEquals( new double[]{  0.0,  1.0, 3.0 }, point[2], 0.01 );
	}

	@Test
	public void testCreateScale() {
		Transform scale = TransformUtil.createScale( 0.5, 2.0, 3.0 );
		Triangle newTriangle = triangle.transform( scale );
		double[][] point = newTriangle.getPosition();
		
		assertArrayEquals( new double[]{  0.0,  2.0, 0.0 }, point[0], 0.01 );
		assertArrayEquals( new double[]{  0.5, -2.0, 0.0 }, point[1], 0.01 );
		assertArrayEquals( new double[]{ -0.5, -2.0, 0.0 }, point[2], 0.01 );
	}

}
