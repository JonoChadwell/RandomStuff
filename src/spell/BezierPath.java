package spell;

public class BezierPath extends Path {

   private static final int SAMPLE_POINTS = 5000;

   private Point[] pts;
   private double[] normalizedLengths;
   private double length;

   public BezierPath(double... data) {
      Point[] pts = new Point[(data.length / 2)];
      for (int i = 0; i < pts.length; i++) {
         pts[i] = new Point(data[2 * i], data[2 * i + 1]);
      }
      initialize(pts);
   }

   public BezierPath(Point... pts) {
      initialize(pts);
   }

   private void initialize(Point[] pts) {
      length = 0;
      normalizedLengths = new double[SAMPLE_POINTS];
      this.pts = pts;
      Point last = pts[0];
      for (int i = 1; i < SAMPLE_POINTS; i++) {
         Point next = simpleSample((double) i / (double) (SAMPLE_POINTS - 1));
         double dist = Point.distance(last, next);
         length += dist;
         normalizedLengths[i] = length;
         last = next;
      }
      for (int i = 0; i < SAMPLE_POINTS; i++) {
         normalizedLengths[i] /= length;
      }
   }

   private Point simpleSample(double position) {
      Point[] cpy = pts.clone();
      int end = cpy.length;
      while (end > 1) {
         for (int i = 0; i < end - 1; i++) {
            cpy[i] = Point.mix(cpy[i], cpy[i + 1], position);
         }
         end--;
      }
      return cpy[0];
   }

   @Override
   public double getLength() {
      return length;
   }

   @Override
   public Point sample(double position) {
      int sampleNumber = 0;
      while (position > normalizedLengths[sampleNumber] && sampleNumber < SAMPLE_POINTS - 1) {
         sampleNumber++;
      }
      position = position - normalizedLengths[sampleNumber] + (double) sampleNumber / (double) (SAMPLE_POINTS - 1);
      Point[] cpy = pts.clone();
      int end = cpy.length;
      while (end > 1) {
         for (int i = 0; i < end - 1; i++) {
            cpy[i] = Point.mix(cpy[i], cpy[i + 1], position);
         }
         end--;
      }
      return cpy[0];
   }
}
