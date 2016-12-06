package spell;

public class CombinedPath extends Path {

   private double length;
   private double[] lengths;
   private Path[] pathes;
   private Point[] offsets;
   private boolean joined;

   public CombinedPath(double... points) {
      LinearPath[] parts = new LinearPath[(points.length / 2) - 1];
      for (int i = 0; i < parts.length; i++) {
         parts[i] = new LinearPath(points[2 * i], points[2 * i + 1], points[2 * i + 2], points[2 * i + 3]);
      }
      initialize(false, parts);
   }

   public CombinedPath(Point... points) {
      LinearPath[] parts = new LinearPath[points.length - 1];
      for (int i = 0; i < parts.length; i++) {
         parts[i] = new LinearPath(points[i], points[i + 1]);
      }
      initialize(false, parts);
   }

   public CombinedPath(Path... pathes) {
      initialize(false, pathes);
   }

   public CombinedPath(boolean join, Path... pathes) {
      initialize(join, pathes);
   }
   private void initialize(boolean join, Path[] pathes) {
      joined = join;
      this.pathes = pathes;
      length = 0;
      lengths = new double[pathes.length];
      Point last = new Point(0,0);
      offsets = new Point[pathes.length];
      for (int i = 0; i < pathes.length; i++) {
         lengths[i] = pathes[i].getLength();
         length += lengths[i];
         if (join) {
            last = Point.subtract(last, pathes[i].sample(0));
            offsets[i] = last;
            Point vector = pathes[i].sample(1);
            last = Point.add(vector, last);
         } else {
            offsets[i] = Point.ZERO;
         }
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
      return Point.add(pathes[i].sample(position / lengths[i]), offsets[i]);
   }

   @Override
   public boolean isContinuous() {
      if (joined) {
         return true;
      }
      for (int position = 0; position < pathes.length - 1; position++) {
         if (!Point.approxEqual(pathes[position].sample(1), pathes[position + 1].sample(0))) {
            return false;
         }
      }
      return true;
   }
}
