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


import org.apache.commons.math3.linear.RealMatrix;
import org.junit.Test;

import com.github.tamurashingo.juko.core.bean.Transform;

public class TransformTest {
	
	@Test
	public void testMultiply() throws Exception {
		
		double[][] m = new double[][] {
				{ 1.0, 2.0, 3.0, 4.0 },
				{ 1.1, 2.1, 3.1, 4.1 },
				{ 1.2, 2.2, 3.2, 4.2 },
				{ 1.3, 2.3, 3.3, 4.3 },
		};

		Transform t1 = new Transform(m);
		Transform t2 = t1.multiply( new Transform(m) );
		double[][] m2 = t2.getMatrix().getData();

		double[][] m2target = new double[][] {
				{ 12.0,  22.0,  32.0,  42.0 },
				{ 12.46, 22.86, 33.26, 43.66 },
				{ 12.92, 23.72, 34.52, 45.32 },
				{ 13.38, 24.58, 35.78, 46.98 },
		};
		
		assertArrayEquals( m2target[0], m2[0], 0.01 );
		assertArrayEquals( m2target[1], m2[1], 0.01 );
		assertArrayEquals( m2target[2], m2[2], 0.01 );
		assertArrayEquals( m2target[3], m2[3], 0.01 );
	}

	@Test
	public void testSerialize() throws Exception {
		
		double[][] m = new double[][] {
				{ 1.0, 2.0, 3.0, 4.0 },
				{ 1.1, 2.1, 3.1, 4.1 },
				{ 1.2, 2.2, 3.2, 4.2 },
				{ 1.3, 2.3, 3.3, 4.3 },
		};
		
		Transform t1 = new Transform( m );
		Transform t2 = null;
		ByteArrayOutputStream outBuf = new ByteArrayOutputStream();
		try ( ObjectOutputStream out = new ObjectOutputStream( outBuf ) ) {
			out.writeObject( t1 );
			byte[] buf = outBuf.toByteArray();

			try ( ByteArrayInputStream inBuf = new ByteArrayInputStream( buf ) ) {
				ObjectInputStream in = new ObjectInputStream( inBuf );
				t2 = (Transform)in.readObject();
			}
		}
		
		RealMatrix t1m = t1.getMatrix();
		RealMatrix t2m = t2.getMatrix();
		
		double[][] t1data = t1m.getData();
		double[][] t2data = t2m.getData();
		
		assertArrayEquals( t1data[0], t2data[0], 0.01 );
		assertArrayEquals( t1data[1], t2data[1], 0.01 );
		assertArrayEquals( t1data[2], t2data[2], 0.01 );
		assertArrayEquals( t1data[3], t2data[3], 0.01 );
	}

}
