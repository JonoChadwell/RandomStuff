package spell;

import static org.junit.Assert.*;

import org.junit.Test;

public class PathesTest {

	@Test
	public void testLinearPath() {
		Path p = new LinearPath(new Point(0,0), new Point(1,1));
		assertTrue(Point.approxEqual(new Point(0,0), p.sample(0)));
		assertTrue(Point.approxEqual(new Point(0.5,0.5), p.sample(0.5)));
		assertTrue(Point.approxEqual(new Point(1,1), p.sample(1)));
	}

	@Test
	public void testCurvedPath() {
		Path p = new CurvedPath(new Point(1,0), new Point(2,0), Math.PI);
		assertTrue(Point.approxEqual(new Point(2,0), p.sample(0)));
		assertTrue(Point.approxEqual(new Point(1,1), p.sample(0.5)));
		assertTrue(Point.approxEqual(new Point(0,0), p.sample(1)));
	}
	
	@Test
	public void testCombinedPath() {
		Path linear = new LinearPath(new Point(2,2), new Point(2,0));
		Path curved = new CurvedPath(new Point(1,0), new Point(2,0), Math.PI);
		Path p = new CombinedPath(linear,curved);
		Path p2 = new CombinedPath(curved,linear);
		assertTrue(p.isContinuous());
		assertFalse(p2.isContinuous());
		assertTrue(Point.approxEqual(new Point(2,2), p.sample(0)));
		assertTrue(Point.approxEqual(new Point(0,0), p.sample(1)));
		
		Path linear2 = new LinearPath(new Point(0,0), new Point(2,0));
		Path linear3 = new LinearPath(new Point(2,0), new Point(2,2));
		p = new CombinedPath(linear2, linear3);
		assertTrue(Point.approxEqual(new Point(0,0), p.sample(0)));
		assertTrue(Point.approxEqual(new Point(1,0), p.sample(0.25)));
		assertTrue(Point.approxEqual(new Point(2,0), p.sample(0.5)));
		assertTrue(Point.approxEqual(new Point(2,1), p.sample(0.75)));
		assertTrue(Point.approxEqual(new Point(2,2), p.sample(1)));
	}
}
