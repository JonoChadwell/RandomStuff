package aplay;

import java.util.Random;
import java.util.function.Consumer;

import javax.sound.sampled.*;

import net.beadsproject.beads.core.AudioContext;
import net.beadsproject.beads.ugens.Gain;
import net.beadsproject.beads.ugens.Noise;

public class AplayThing {
   private static final int SAMPLE_RATE = 64000;

   private static double LEN = 0.5;
   private static double PAUSE = 0.00;
   private static double FADE_RATE = 0.4;
   private static long SEED = 0;
   private static Note BASE_NOTE = Note.C;
   private static int OCTAVE = 0;
   private static double BASE_AMPLITUDE[] = { 0.1, 0.002 };
   private static Chord BASE_CHORD = Chord.MAJOR;

   private static Player player;

   public static void main(String args[]) throws Exception {
      System.out.println("Tone Generator by Jono Chadwell");
      AudioContext ac;
      ac = new AudioContext();

      parseArgs(args);
      player = new Player(ac);

      Random rand = new Random(SEED);

      for (int i = 0; i < 100000; i++) {
         addNote(BASE_CHORD.getChord()[rand.nextInt(8)] + BASE_NOTE.position + 12 * OCTAVE, i);
      }

      new PlayableViewer(NoteGenerator.getNote(0, BASE_AMPLITUDE), 1 / 80000.0);
      Gain g = new Gain(ac, 1, 0.1f);
      g.addInput(player);
      ac.out.addInput(g);
      ac.start();
   }

   private static void parseArgs(String args[]) {
      for (String s : args) {
         if (s.contains("help") || s.equals("-h") || s.equals("?")) {
            System.out.println("Options:");
            System.out.println("--seed= (-s=) seed for music");
            System.out.println("--length= (-l=) note length (seconds)");
            System.out.println("--pause= (-p=) break between notes");
            System.out.println("--fade= (-f=) fade speed");
            System.out.println("--node= (-n=) base note");
            System.out.println("--chord= (-c=) chord type");
            System.out.println("--octave= (-o=) octave shift");
            System.out.println("--wave= (-w=) waveform (SINE, SQUARE, SAWTOOTH, TRIANGLE, BACKSAW)");
            System.out.println("--amplitude= (-a=) list of amplitudes for overtones");
         }
         ifStarts((str) -> SEED = str.hashCode(), s, "-s=", "--seed=");
         ifStarts((str) -> LEN = Double.valueOf(str), s, "-l=", "--length=");
         ifStarts((str) -> PAUSE = Double.valueOf(str), s, "-p=", "--pause=");
         ifStarts((str) -> FADE_RATE = Double.valueOf(str), s, "-f=", "--fade=");
         ifStarts((str) -> BASE_NOTE = Note.valueOf(str), s, "-n=", "--note=");
         ifStarts((str) -> BASE_CHORD = Chord.valueOf(str), s, "-c=", "--chord=");
         ifStarts((str) -> OCTAVE = Integer.valueOf(str), s, "-o=", "--octave=");
         ifStarts((str) -> NoteGenerator.DEFAULT_WAVEFORM = Waveform.valueOf(str), s, "-w=", "--wave=");
         ifStarts((str) -> setAmplitude(str), s, "-a=", "--amplitude=");
      }
   }

   private static void setAmplitude(String str) {
      System.out.println("Setting amplitude A");
      String[] parts = str.split(",");
      double[] overtones = new double[parts.length];
      for (int i = 0; i < parts.length; i++) {
         overtones[i] = Double.valueOf(parts[i]);
      }
      System.out.println("Setting amplitude B");
      BASE_AMPLITUDE = overtones;
   }

   private static void ifStarts(Consumer<String> funct, String s, String... prefixes) {
      for (String prefix : prefixes) {
         if (s.startsWith(prefix)) {
            String rest = s.substring(prefix.length());
            funct.accept(rest);
         }
      }
   }

   private static void addHalf(Note n, double pos) {
      player.addSound(new FadedPlayable(NoteGenerator.getNote(n, BASE_AMPLITUDE), pos * LEN, (pos + 0.5) * LEN - PAUSE, FADE_RATE));
   }

   private static void addNote(Note n, double pos) {
      player.addSound(new FadedPlayable(NoteGenerator.getNote(n, BASE_AMPLITUDE), pos * LEN, (pos + 1) * LEN - PAUSE, FADE_RATE));
   }

   private static void addNote(int n, double pos) {
      player.addSound(new FadedPlayable(NoteGenerator.getNote(n, BASE_AMPLITUDE), pos * LEN, (pos + 1) * LEN - PAUSE, FADE_RATE));
   }

   private static void addDouble(Note n, double pos) {
      player.addSound(new FadedPlayable(NoteGenerator.getNote(n, BASE_AMPLITUDE), pos * LEN, (pos + 2) * LEN - PAUSE, FADE_RATE));
   }

   private static void addQuad(Note n, double pos) {
      player.addSound(new FadedPlayable(NoteGenerator.getNote(n, BASE_AMPLITUDE), pos * LEN, (pos + 4) * LEN - PAUSE, FADE_RATE));
   }

   private static void littleLamb() {
      // Mary Had a Little Lamb
      addNote(Note.E, 0);
      addNote(Note.D, 1);
      addNote(Note.C, 2);
      addNote(Note.D, 3);
      addNote(Note.E, 4);
      addNote(Note.E, 5);
      addDouble(Note.E, 6);

      addNote(Note.D, 8);
      addNote(Note.D, 9);
      addDouble(Note.D, 10);
      addNote(Note.E, 12);
      addNote(Note.G, 13);
      addDouble(Note.G, 14);

      addNote(Note.E, 16);
      addNote(Note.D, 17);
      addNote(Note.C, 18);
      addNote(Note.D, 19);
      addNote(Note.E, 20);
      addNote(Note.E, 21);
      addNote(Note.E, 22);
      addNote(Note.E, 23);

      addNote(Note.D, 24);
      addNote(Note.D, 25);
      addNote(Note.E, 26);
      addNote(Note.D, 27);
      addQuad(Note.C, 28);
   }

   private static void saints() {
      // The Saints go marching in
      addNote(Note.C, 0);
      addNote(Note.E, 1);
      addNote(Note.F, 2);
      addNote(Note.G, 3);

      addNote(Note.C, 6);
      addNote(Note.E, 7);
      addNote(Note.F, 8);
      addNote(Note.G, 9);

      addNote(Note.C, 12);
      addNote(Note.E, 13);
      addNote(Note.F, 14);

      addNote(Note.G, 15);
      addNote(Note.E, 17);
      addNote(Note.C, 19);
      addNote(Note.E, 21);
      addNote(Note.D, 23);

      addNote(Note.E, 26);
      addNote(Note.E, 27);
      addNote(Note.D, 28);
      addNote(Note.C, 29);
      addNote(Note.C, 31);
      addNote(Note.E, 32);
      addNote(Note.G, 34);
      addNote(Note.G, 35);
      addNote(Note.G, 36);
      addNote(Note.F, 37);

      addNote(Note.E, 40);
      addNote(Note.F, 41);
      addNote(Note.G, 43);
      addNote(Note.E, 45);
      addNote(Note.C, 47);
      addNote(Note.D, 49);
      addNote(Note.C, 51);
   }
}
