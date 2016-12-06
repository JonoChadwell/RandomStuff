package spell;

public abstract class Path {
   public abstract double getLength();

   public abstract Point sample(double position);

   public boolean isContinuous() {
      return true;
   }
}
