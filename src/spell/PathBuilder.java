package spell;

import java.util.ArrayList;
import java.util.List;

public class PathBuilder {

   private List<Path> pathes;
   private double angle = 0;

   public PathBuilder(int angle) {
      this(angle * Math.PI / 180.0);
   }

   public PathBuilder(double angle) {
      this.angle = angle;
      pathes = new ArrayList<>();
   }

   public PathBuilder turn(int angle) {
      return turn(angle * Math.PI / 180.0);
   }

   public PathBuilder turn(double angle) {
      this.angle += angle;
      return this;
   }

   public PathBuilder line(double length) {
      pathes.add(
            new LinearPath(Math.cos(angle) * length, Math.sin(angle) * length));
      return this;
   }

   public PathBuilder arc(double radius, int angle) {
      return arc(radius, angle * Math.PI / 180.0);
   }

   public PathBuilder arc(double radius, double angle) {
      if (angle < 0) {
         radius = -radius;
      }
      pathes.add(new CurvedPath(-Math.sin(this.angle) * radius, Math.cos(this.angle) * radius, -angle));
      this.angle -= angle;
      return this;
   }

   public PathBuilder bezier(double... pts) {
      if (pts.length < 2 || pts.length % 2 == 1) {
         throw new RuntimeException("Bad arguments");
      }
      double startAngle = Math.atan2(pts[1], pts[0]);
      int len = pts.length;
      double endAngle;
      if (len == 2) {
         endAngle = startAngle;
      } else {
         endAngle = Math.atan2(pts[len - 1] - pts[len - 3], pts[len - 2] - pts[len - 4]);
      }
      Point[] vals = new Point[len / 2 + 1];
      vals[0] = new Point(0,0);
      for (int i = 0; i < len / 2; i++) {
         vals[i + 1] = Point.rotate(new Point(pts[2 * i], pts[2 * i + 1]), angle - startAngle);
      }
      angle += endAngle - startAngle;
      pathes.add(new BezierPath(vals));
      return this;
   }

   public Path build() {
      return new CombinedPath(true, pathes.toArray(new Path[0]));
   }
}
