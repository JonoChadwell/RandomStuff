package spell;

public abstract class SpellPathes {

   private static final double PI = Math.PI;

   public static final Path FIREBALL = new CombinedPath(0, 0, 1, 1, 1, 0, 0, 1);

   public static final Path MAGIC_MISSILE = new CombinedPath(true,
         new CurvedPath(2, 0, PI * 2), new LinearPath(-3, -2));

   public static final Path FIREBLAST = new CombinedPath(FIREBALL,
         new LinearPath(0, 1, 0, 0), new CurvedPath(-0.25, 0, 0, 0, -PI));

   public static final Path FIREBALL_2 = new CombinedPath(1, 1, 3, 3, 3, 1, 1,
         3, 4, 0, 4, 4, 1, 1, 2, 0);

   public static final Path BARRIER = new CombinedPath(true,
         new LinearPath(0.4, 0.4), new CurvedPath(-1, 1, -Math.PI * 1.05),
         new LinearPath(-.9, -.65), new CurvedPath(-.6, .6, -Math.PI * 1.12),
         new CurvedPath(-1, 2, Math.PI / 2),
         new CurvedPath(-.8, -0.6, Math.PI * .81), new LinearPath(.18, 2));

   public static final Path PENTAGRAM = new PathBuilder(72).line(1).turn(-144)
         .line(1).turn(-144).line(1).turn(-144).line(1).turn(-144).line(1)
         .turn(-72).arc(0.5 / Math.cos(Math.PI / 10), 360).build();

   public static final Path WIND = new WindPath();

   public static final Path HEAL = new CombinedPath(new LinearPath(0, 0, 0, -4),
         new CurvedPath(0, -3, 0, -4, PI),
         new CurvedPath(0, -1, 0, -2, -PI * 1.5));

   public static final Path MAGIC_BLAST = new PathBuilder(0).arc(1, -PI * 2)
         .arc(0.5, PI).arc(2, PI * 2).arc(1, PI * 2).build();

   public static Path[] SPELLS = { FIREBALL, MAGIC_MISSILE, FIREBALL_2,
         FIREBLAST, BARRIER, PENTAGRAM, WIND, HEAL, MAGIC_BLAST };
}
