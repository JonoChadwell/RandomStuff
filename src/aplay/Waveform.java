package aplay;
public enum Waveform {
   SINE,
   SQUARE,
   SAWTOOTH,
   TRIANGLE,
   BACKSAW;
   
   public double getPosition(double time, double period) {
      switch (this) {
      case SINE:
         return Math.sin(time * 2 * Math.PI / period) / 2.0 + 0.5;
         
      case SQUARE:
         return (time % period > (period / 2)) ? 1 : 0;
         
      case SAWTOOTH:
         return ((time + period / 2) % period) / period;
         
      case TRIANGLE:
         return Math.abs(1 - 2 * ((time + period / 4) % period) / period);
         
      case BACKSAW:
         return -(((time + period / 2) % period) / period);
         
      }
      throw new RuntimeException("you dun fucked up");
   }
}