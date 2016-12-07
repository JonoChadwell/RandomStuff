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

   public Path build() {
      return new CombinedPath(true, pathes.toArray(new Path[0]));
   }
}
