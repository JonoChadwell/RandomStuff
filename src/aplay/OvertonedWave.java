package aplay;
public class OvertonedWave implements Playable {
   
   public final double frequency;
   public final Waveform waveform;
   public final double amplitudes[];
   
   public OvertonedWave(double frequency, Waveform waveform, double amplitudes[]) {
      this.frequency = frequency;
      this.waveform = waveform;
      this.amplitudes = amplitudes;
   }
   
   public double sample(double time) {
      double accum = 0;
      for (int i = 0; i < amplitudes.length; i++) {
         accum += 2 * amplitudes[i] * waveform.getPosition(time, 1 / (frequency * (i + 1))) - amplitudes[i];
      }
      return accum;
   }
}
