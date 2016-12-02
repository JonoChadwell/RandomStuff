package aplay;

public interface Playable {
   public double sample(double time);
   
   public default FadedPlayable clipFade(double start, double end, double fadeRate) {
      return new FadedPlayable(this, start, end, fadeRate);
   }
}
