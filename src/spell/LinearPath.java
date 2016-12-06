package spell;

public class LinearPath extends Path {

   private final Point from;
   private final Point to;

   public LinearPath(double x, double y) {
      this(Point.ZERO, new Point(x, y));
   }

   public LinearPath(double x1, double y1, double x2, double y2) {
      this(new Point(x1, y1), new Point(x2, y2));
   }

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
