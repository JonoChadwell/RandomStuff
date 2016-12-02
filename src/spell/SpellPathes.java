package spell;

public abstract class SpellPathes {
	// Prevent Instantiation
	private SpellPathes() {}
	public static final Path FIREBALL = new CombinedPath(
			new LinearPath(new Point(0,0), new Point(1,1)),
			new LinearPath(new Point(1,1), new Point(1,0)),
			new LinearPath(new Point(1,0), new Point(0,1)));
	
	public static final Path MAGIC_MISSILE = null;
}
