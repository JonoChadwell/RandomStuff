package spell;

public class CurvedPath extends Path {
	
	private final Point center;
	private final Point startPoint;
	// counter-clockwise distance swept around center in radians. May be negative.
	private final double angle;
	
	public CurvedPath(Point center, Point startPoint, double angle) {
		this.center = center;
		this.startPoint = startPoint;
		this.angle = angle;
	}

	@Override
	public double getLength() {
		return Point.distance(center, startPoint) * angle;
	}

	@Override
	public Point sample(double position) {
		return Point.rotateAbout(startPoint, center, position * angle);
	}

}
