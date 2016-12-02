package aplay;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import net.beadsproject.beads.core.AudioContext;
import net.beadsproject.beads.core.UGen;


public class Player extends UGen {
   
   private int position;
   
   public Player(AudioContext context) throws Exception {
      super(context, 0, 1);
   }
   
   private List<Playable> sounds = new ArrayList<>();
   private List<FadedPlayable> futureSounds = new ArrayList<>();
   
   public void addSound(Playable sound) {
      if (sound instanceof FadedPlayable) {
         int address = Collections.binarySearch(futureSounds, (FadedPlayable) sound, (a,b) -> Double.compare(a.getStart(), b.getStart()));
         if (address < 0) {
            address = -(address + 1);
         }
         futureSounds.add(address, (FadedPlayable) sound);
      } else {
         sounds.add(sound);
      }
   }
   
   public void removeSound(Playable sound) {
      sounds.remove(sound);
   }
   
   public byte clip(double val) {
      if (val > 255) {
         return (byte) 255;
      } else if (val < 0) {
         return 0;
      } else {
         return (byte) val;
      }
   }

   @Override
   public void calculateBuffer() {
      boolean clipping = false;
      for (int i = 0; i < bufOut[0].length; i++) {
         float val = 0;
         double time = (i + position) / context.getAudioFormat().sampleRate;
         while (!futureSounds.isEmpty() && futureSounds.get(0).getStart() <= time) {
            sounds.add(futureSounds.remove(0));
         }
         
         for (Playable sound : sounds) {
            val += sound.sample(time);
         }
         
         for (int j = 0; j < outs; j++) {
            bufOut[j][i] = val;
            if (val > 1 || val < -1) {
               clipping = true;
            }
         }
         
         sounds.removeIf((sound) -> sound instanceof FadedPlayable && ((FadedPlayable) sound).isOver(time));
      }
      position += bufOut[0].length;
      if (clipping) {
         System.out.println("Clipping");
      }
   }
}
