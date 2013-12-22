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
package test.com.github.tamurashingo.juko.core.reader.impl;

import static org.junit.Assert.*;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.io.Reader;
import java.util.List;

import mockit.Expectations;
import mockit.Mock;
import mockit.MockClass;
import mockit.MockUp;
import mockit.Mocked;
import mockit.Mockit;


import org.junit.BeforeClass;
import org.junit.Test;

import com.github.tamurashingo.juko.core.bean.PlantObject;
import com.github.tamurashingo.juko.core.bean.PlantObject.PlantType;
import com.github.tamurashingo.juko.core.reader.PlantObjectReader;
import com.github.tamurashingo.juko.core.reader.PlantObjectReaderException;
import com.github.tamurashingo.juko.core.reader.impl.SimpleReader;
import com.github.tamurashingo.juko.core.util.LineReader;

public class SimpleReaderTest {
	
	@BeforeClass
	public static void setUpClass() {
		Mockit.setUpMocks();
		
		//
		// disable constructors.
		//
		new MockUp<Reader>() {
			@Mock void $init(Object obj){}
		};
		new MockUp<FileReader>() {
			@Mock void $init(String file){}
		};
		new MockUp<BufferedReader>() {
			@Mock void $init(Reader reader){}
		};
	}
	
	@Test
	public void testSample() throws Exception {
		@MockClass(realClass=com.github.tamurashingo.juko.core.util.LineReader.class)
		class MockLineReader {
			@Mock
			public String readLine() throws IOException {
				throw new IOException();
			}
		}
		
		PlantObjectReader reader = new SimpleReader();
		reader.open("");
		try {
			reader.read();
			fail("not reached");
		}
		catch ( PlantObjectReaderException ex ) {
			// ok
		}
		reader.close();
	}
	
	/*-
	 * read normal tsv format.
	 */
	@Test
	public void testRead1() throws Exception {
		new Expectations() {
			@Mocked(methods="readLine")
			private LineReader lineReader;
			{
				//
				lineReader.readLine();
				result = "1.0\t2.0\t3.0";
				result = "4.0\t5.0\t6.0";
				result = "7.0\t8.0\t9.0";
				result = "0.0\t0.0\t0.0";
				result = null;
			}
		};

		PlantObjectReader reader = new SimpleReader();
		reader.open("");
		List<PlantObject> obj = reader.read();
		assertEquals(1, obj.size());
		assertEquals(PlantType.LEAF, obj.get(0).getPlantType());
		assertEquals(1, obj.get(0).getTriangles().size());
		double[][] points = obj.get(0).getTriangles().get(0).getPosition();
		assertArrayEquals(new double[]{1.0, 2.0, 3.0}, points[0], 0.01);
		assertArrayEquals(new double[]{4.0, 5.0, 6.0}, points[1], 0.01);
		assertArrayEquals(new double[]{7.0, 8.0, 9.0}, points[2], 0.01);
		
		reader.close();
		
	}


	/*-
	 * read normal tsv with brank line.
	 */
	@Test
	public void testRead2() throws Exception {
		new Expectations() {
			@Mocked(methods="readLine")
			private LineReader lineReader;
			{
				//
				lineReader.readLine();
				result = "1.0\t2.0\t3.0";
				result = "4.0\t5.0\t6.0";
				result = "7.0\t8.0\t9.0";
				result = "";
				result = "0.0\t0.0\t0.0";
				result = null;
			}
		};

		PlantObjectReader reader = new SimpleReader();
		reader.open("");
		List<PlantObject> obj = reader.read();
		assertEquals(1, obj.size());
		assertEquals(PlantType.LEAF, obj.get(0).getPlantType());
		assertEquals(1, obj.get(0).getTriangles().size());
		double[][] points = obj.get(0).getTriangles().get(0).getPosition();
		assertArrayEquals(new double[]{1.0, 2.0, 3.0}, points[0], 0.01);
		assertArrayEquals(new double[]{4.0, 5.0, 6.0}, points[1], 0.01);
		assertArrayEquals(new double[]{7.0, 8.0, 9.0}, points[2], 0.01);
		
		reader.close();
		
	}
	
	/*-
	 * read normal tsv and move to origin.
	 */
	@Test
	public void testRead3() throws Exception {
		new Expectations() {
			@Mocked(methods="readLine")
			private LineReader lineReader;
			{
				//
				lineReader.readLine();
				result = "1.0\t2.0\t3.0";
				result = "4.0\t5.0\t6.0";
				result = "7.0\t8.0\t9.0";
				result = "";
				result = "3.0\t2.0\t1.0";
				result = null;
			}
		};

		PlantObjectReader reader = new SimpleReader();
		reader.open("");
		List<PlantObject> obj = reader.read();
		assertEquals(1, obj.size());
		assertEquals(PlantType.LEAF, obj.get(0).getPlantType());
		assertEquals(1, obj.get(0).getTriangles().size());
		double[][] points = obj.get(0).getTriangles().get(0).getPosition();
		assertArrayEquals(new double[]{-2.0, 0.0, 2.0}, points[0], 0.01);
		assertArrayEquals(new double[]{1.0, 3.0, 5.0}, points[1], 0.01);
		assertArrayEquals(new double[]{4.0, 6.0, 8.0}, points[2], 0.01);
		
		reader.close();
		
	}
}
