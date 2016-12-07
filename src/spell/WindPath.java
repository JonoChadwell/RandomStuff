package spell;

public class WindPath extends Path {

   @Override
   public double getLength() {
      // TODO Auto-generated method stub
      return 1;
   }

   @Override
   public Point sample(double t) {
      return new Point(Math.sin(t * 4 * Math.PI) + (t - 0.5) * 3.5, -Math.cos(t * 4 * Math.PI));
   }

}
