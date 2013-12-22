package test.com.github.tamurashingo.juko.core.calculate.impl;

import static org.junit.Assert.*;

import java.util.ArrayList;
import java.util.List;

import org.junit.Test;

import com.github.tamurashingo.juko.core.bean.PlantObject;
import com.github.tamurashingo.juko.core.bean.TransformUtil;
import com.github.tamurashingo.juko.core.bean.Triangle;
import com.github.tamurashingo.juko.core.bean.PlantObject.PlantType;
import com.github.tamurashingo.juko.core.calculate.CalcIF;
import com.github.tamurashingo.juko.core.calculate.impl.CalcIncludeOverlap;

public class CalcIncludeOverlapTest {

	@Test
	public void test() {
		
		/*-
		 * |\
		 * | \
		 * +--
		 */
		Triangle t = new Triangle(
				new double[]{ 0.0, 0.0, 0.0 },
				new double[]{ 2.0, 0.0, 0.0 },
				new double[]{ 0.0, 2.0, 0.0 });
		
		PlantObject p = new PlantObject();
		p.setPlantType(PlantType.LEAF);
		p.addTriangle(t);
		
		List<PlantObject> list = new ArrayList<>();
		list.add(p);

		CalcIF c = new CalcIncludeOverlap();
		List<PlantObject> result = c.calc(list);
		
		assertEquals(2.0, result.get(0).getTriangles().get(0).getCalculatedArea(), 0.001);
	}
	
	@Test
	public void testRotated() {
		
		/*-
		 * |\
		 * | \
		 * +--
		 */
		Triangle t = new Triangle(
				new double[]{ 0.0, 0.0, 0.0 },
				new double[]{ 2.0, 0.0, 0.0 },
				new double[]{ 0.0, 2.0, 0.0 });
		
		Triangle t2 = t.transform(TransformUtil.createRotateZ(30 * Math.PI / 180));
		
		PlantObject p = new PlantObject();
		p.setPlantType(PlantType.LEAF);
		p.addTriangle(t2);
		
		List<PlantObject> list = new ArrayList<>();
		list.add(p);

		CalcIF c = new CalcIncludeOverlap();
		List<PlantObject> result = c.calc(list);
		
		assertEquals(2.0, result.get(0).getTriangles().get(0).getCalculatedArea(), 0.001);
	}
	

}
