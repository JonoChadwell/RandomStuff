package spell;

public class BoundingBox {
   public final Point min;
   public final Point max;

   public BoundingBox(Point min, Point max) {
      this.min = min;
      this.max = max;
   }

   public BoundingBox combine(BoundingBox a, BoundingBox b) {
      return new BoundingBox(new Point(Math.min(a.min.x, b.min.x), Math.min(a.min.y, b.min.y)), new Point(Math.max(a.max.x, b.max.x), Math.max(
            a.max.y, b.max.y)));
   }
}
