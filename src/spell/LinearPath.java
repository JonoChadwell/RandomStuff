package spell;

public class LinearPath extends Path {
	
	private final Point from;
	private final Point to;

	public LinearPath(Point from, Point to) {
		this.from = from;
		this.to = to;
	}
	
	@Override
	public double getLength() {
		return Point.distance(from, to);
	}

	@Override
	public Point sample(double position) {
		return Point.mix(from, to, position);
	}

}
