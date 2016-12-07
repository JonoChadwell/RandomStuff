package spell;

import java.util.Arrays;

public class PathSample {

   private Point[] points;

   public PathSample(Point[] points) {
      this.points = points;
   }

   public PathSample(Path p, int points, boolean normalize) {
      this.points = new Point[points];
      for (int i = 0; i < points; i++) {
         this.points[i] = p.sample((double) i / (double) (points - 1));
      }
      if (normalize) {
         normalize();
      } else {
         rescale2();
      }
   }

   public int getLength() {
      return points.length;
   }

   public Point getPoint(int index) {
      return points[index];
   }

   public void normalize() {
      recenter(getCentroid());
      rotate(-Math.atan2(points[0].y, points[0].x));
      rescale2();
   }

   public Point getCentroid() {
      Point pt = new Point(0, 0);
      for (int i = 0; i < points.length; i++) {
         pt = Point.add(Point.scale(points[i], (double) 1 / (double) points.length), pt);
      }
      return pt;
   }

   private void recenter(Point newCenter) {
      for (int i = 0; i < points.length; i++) {
         points[i] = Point.subtract(points[i], newCenter);
      }
   }

   private void rotate(double theta) {
      for (int i = 0; i < points.length; i++) {
         points[i] = Point.rotate(points[i], theta);
      }
   }

   private void rescale2() {
      double minx = Arrays.stream(points).mapToDouble((p) -> p.x).min().getAsDouble();
      double miny = Arrays.stream(points).mapToDouble((p) -> p.y).min().getAsDouble();
      double maxx = Arrays.stream(points).mapToDouble((p) -> p.x).max().getAsDouble();
      double maxy = Arrays.stream(points).mapToDouble((p) -> p.y).max().getAsDouble();
      double scale = Math.max(maxx - minx, maxy - miny);
      double addx = (scale - (maxx-minx)) / 2;
      double addy = (scale - (maxy-miny)) / 2;
      for (int i = 0; i < points.length; i++) {
         points[i] = new Point((points[i].x - minx + addx) / scale, (points[i].y - miny + addy) / scale);
      }
   }
}
