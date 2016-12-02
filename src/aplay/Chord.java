package aplay;

   

public enum Chord {
   
   MAJOR,
   MINOR;
   
   private static final int MAJOR_CHORD[] = {0,2,4,5,7,9,11,12};
   private static final int MINOR_CHORD[] = {0,2,3,5,7,8,10,12};
   public int[] getChord() {
      switch(this) {
      case MAJOR:
         return MAJOR_CHORD;
      case MINOR:
         return MINOR_CHORD;
      }
      throw new RuntimeException("you dun fucked up");
   }
}
