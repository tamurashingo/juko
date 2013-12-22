package test.com.github.tamurashingo.juko.core.bean;

import static org.junit.Assert.*;

import org.junit.Test;

import com.github.tamurashingo.juko.core.bean.PlantObject;
import com.github.tamurashingo.juko.core.bean.PlantObject.PlantType;
import com.github.tamurashingo.juko.core.bean.Triangle;

public class PlantObjectTest {

	@Test
	public void testClone() {
		PlantObject p1 = new PlantObject();
		p1.setPlantType(PlantType.LEAF);
		p1.addTriangle(new Triangle(new double[]{  0.0, -1.0, 0.0 },
				new double[]{  1.0,  1.0, 0.0 },
				new double[]{ -1.0,  1.0, 0.0 }));
		
		PlantObject p2 = (PlantObject)p1.clone();
		
		assertFalse("p2 is another instance.", p1 == p2);
		assertFalse("p2's list is another instance.", p1.getTriangles() == p2.getTriangles());
		assertFalse("p2's triangle is another instance.", p1.getTriangles().get(0) == p2.getTriangles().get(0));
		
		assertEquals(PlantType.LEAF, p2.getPlantType());
		
		assertArrayEquals(new double[]{  0.0, -1.0, 0.0 }, p2.getTriangles().get(0).getPosition()[0], 0.001);
		assertArrayEquals(new double[]{  1.0,  1.0, 0.0 }, p2.getTriangles().get(0).getPosition()[1], 0.001);
		assertArrayEquals(new double[]{ -1.0,  1.0, 0.0 }, p2.getTriangles().get(0).getPosition()[2], 0.001);
	}

}
