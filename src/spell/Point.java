package spell;

public class Point {
	
	private static final double EPSILON = 0.00001;
	
	public final double x;
	public final double y;

	public Point(double x, double y) {
		this.x = x;
		this.y = y;
	}
	
	public static Point mix(Point a, Point b, double ratio) {
		return add(scale(a, 1 - ratio), scale(b, ratio));
	}
	
	public static Point add(Point a, Point b) {
		return new Point(a.x + b.x, a.y + b.y);
	}
	
	public static Point subtract(Point a, Point b) {
		return new Point(a.x - b.x, a.y - b.y);
	}
	
	public static Point scale(Point a, double scalar) {
		return new Point(a.x * scalar, a.y * scalar);
	}
	
	public static double distance(Point a, Point b) {
		return Math.sqrt((a.x - b.x) * (a.x - b.x) + (a.y - b.y) * (a.y - b.y)); 
	}
	
	public static Point rotate(Point a, double theta) {
		double s = Math.sin(theta);
		double c = Math.cos(theta);
		return new Point(a.x * c + a.y * s, a.x * s + a.y * c);
	}
	
	public static Point rotateAbout(Point a, Point b, double theta) {
		return Point.add(Point.rotate(Point.subtract(a, b), theta), b);
	}
	
	public static boolean approxEqual(Point a, Point b) {
		return Math.abs(a.x - b.x) - EPSILON < 0 && Math.abs(a.y - b.y) - EPSILON < 0;
	}
	
	@Override
	public String toString() {
		return "(" + x + "," + y + ")";
	}
}
