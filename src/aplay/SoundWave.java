package aplay;
public class SoundWave implements Playable {
   
   public final double frequency;
   public final Waveform waveform;
   public final double amplitude;
   
   public SoundWave(double frequency, Waveform waveform, double amplitude) {
      this.frequency = frequency;
      this.waveform = waveform;
      this.amplitude = amplitude;
   }
   
   public double sample(double time) {
      return 2 * amplitude * waveform.getPosition(time, 1 / frequency) - amplitude;
   }
}
