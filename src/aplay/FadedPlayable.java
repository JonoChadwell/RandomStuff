package aplay;
public class FadedPlayable implements Playable {
   private final Playable sound;
   private final double start;
   private final double end;
   private final double fadeRate;

   public FadedPlayable(Playable sound, double start, double end, double fadeRate) {
      this.sound = sound;
      this.start = start;
      this.end = end;
      this.fadeRate = fadeRate;
   }

   private static boolean first = true;

   public double sample(double time) {
      if (isOver(time)) {
         return 0;
      } else if (time > end) {
         return sound.sample(time - start) * Math.exp((end - time) / fadeRate);
      } else if (time >= start) {
         return sound.sample(time - start);
      } else {
         return 0;
      }
   }
   
   public double getStart() {
      return start;
   }
   
   public boolean isOver(double time) {
      return time > end + Math.E * fadeRate;
   }
}
