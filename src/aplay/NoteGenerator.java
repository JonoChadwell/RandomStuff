package aplay;

public class NoteGenerator {
   private static final double DIFF = 1.05946309435929526456182529494634170077920431749;
   private static final double A = 440.0;
   private static final double DEFAULT_AMPLITUDE[] = {32,8,2,2,2};
   private static final int DEFAULT_OCTAVE = 0;
   public static Waveform DEFAULT_WAVEFORM = Waveform.SINE;
   
   public static Playable getNote(Note note) {
      return generateNote(note.position, DEFAULT_AMPLITUDE, DEFAULT_OCTAVE);
   }
   
   public static Playable getNote(Note note, double amplitude) {
      return generateNote(note.position, amplitude, DEFAULT_OCTAVE);
   }
   
   public static Playable getNote(Note note, double amplitude, int octave) {
      return generateNote(note.position, amplitude, octave);
   }
   
   public static Playable getNote(int note) {
      return generateNote(note, DEFAULT_AMPLITUDE, DEFAULT_OCTAVE);
   }
   
   public static Playable getNote(int note, double amplitude) {
      return generateNote(note, amplitude, DEFAULT_OCTAVE);
   }
   
   public static Playable getNote(int note, double amplitude, int octave) {
      return generateNote(note, amplitude, octave);
   }
   public static Playable getNote(Note note, double amplitudes[]) {
      return generateNote(note.position, amplitudes, DEFAULT_OCTAVE);
   }
   
   public static Playable getNote(Note note, double amplitudes[], int octave) {
      return generateNote(note.position, amplitudes, octave);
   }
   
   public static Playable getNote(int note, double amplitudes[]) {
      return generateNote(note, amplitudes, DEFAULT_OCTAVE);
   }
   
   public static Playable getNote(int note, double amplitudes[], int octave) {
      return generateNote(note, amplitudes, octave);
   }
   
   private static Playable generateNote(int note, double amplitude, int octave) {
      return new SoundWave(Math.pow(2, octave) * Math.pow(DIFF, note) * A, DEFAULT_WAVEFORM, amplitude);
   }
   
   private static Playable generateNote(int note, double amplitudes[], int octave) {
      return new OvertonedWave(Math.pow(2, octave) * Math.pow(DIFF, note) * A, DEFAULT_WAVEFORM, amplitudes);
   }
}
