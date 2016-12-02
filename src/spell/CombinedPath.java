package spell;

public class CombinedPath extends Path {

	private double length;
	private double[] lengths;
	private Path[] pathes;
	
	public CombinedPath(Path ... pathes) {
		this.pathes = pathes;
		length = 0;
		lengths = new double[pathes.length];
		for (int i = 0; i < pathes.length; i++) {
			lengths[i] = pathes[i].getLength();
			length += lengths[i];
		}
	}

	@Override
	public double getLength() {
		return length;
	}

	@Override
	public Point sample(double position) {
		position = position * length;
		int i = 0;
		while (position > lengths[i] && i < pathes.length - 1) {
			position -= lengths[i];
			i++;
		}
		return pathes[i].sample(position / lengths[i]);
	}
	
	@Override
	public boolean isContinuous() {
		for (int position = 0; position < pathes.length - 1; position++) {
			if (!Point.approxEqual(pathes[position].sample(1), pathes[position + 1].sample(0))) {
				return false;
			}
		}
		return true;
	}
}
