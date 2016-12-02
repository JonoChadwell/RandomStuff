package aplay;

public enum Note {
   Aflat(-1),
   A(0),
   Asharp(1),
   Bflat(1),
   B(2),
   Bsharp(3),
   Cflat(2),
   C(3),
   Csharp(4),
   Dflat(4),
   D(5),
   Dsharp(6),
   Eflat(6),
   E(7),
   Esharp(8),
   Fflat(7),
   F(8),
   Fsharp(9),
   Gflat(9),
   G(10),
   Gsharp(11);
   
   public int position;
   
   Note(int position) {
      this.position = position;
   }
}
