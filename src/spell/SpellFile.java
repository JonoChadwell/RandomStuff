package spell;

import java.io.File;
import java.io.PrintWriter;
import java.util.Scanner;

public class SpellFile {

   public static PathSample readSpellFile(String path) {
      try {
         Scanner file = new Scanner(new File(path));
         int count = file.nextInt();
         file.nextLine();
         file.nextLine();
         Point[] points = new Point[count];
         for (int i = 0; i < count; i++) {
            Scanner line = new Scanner(file.nextLine());
            line.useDelimiter(",");
            points[i] = new Point(line.nextDouble(), line.nextDouble());
            line.close();
         }
         file.close();
         return new PathSample(points);
      } catch (Exception ex) {
         throw new RuntimeException(ex);
      }
   }

   public static void writeSpellFile(String fileName, PathSample path) {
      writeSpellFile(fileName, path, 1.0);
   }

   public static void writeSpellFile(String fileName, PathSample path, double difficulty) {
      try {
         PrintWriter out = new PrintWriter(fileName);
         out.write(path.getLength() + "\n");
         out.write(difficulty + "\n");
         for (int i = 0; i < path.getLength(); i++) {
            Point pt = path.getPoint(i);
            out.write(pt.x + "," + pt.y + "\n");
         }
         out.flush();
         out.close();
      } catch (Exception ex) {
         throw new RuntimeException(ex);
      }
   }

   public static void main(String[] args) {
      int SAMPLES = 800;
      writeSpellFile("C:/Jono/Documents/Programming/Vive/ViveTest/Assets/SpellData/Fireball.spell", new PathSample(SpellPathes.FIREBALL, SAMPLES, true), 1.1);
      writeSpellFile("C:/Jono/Documents/Programming/Vive/ViveTest/Assets/SpellData/Fireblast.spell", new PathSample(SpellPathes.FIREBLAST, SAMPLES, true), 0.9);
      writeSpellFile("C:/Jono/Documents/Programming/Vive/ViveTest/Assets/SpellData/Fireball2.spell", new PathSample(SpellPathes.FIREBALL_2, SAMPLES, true), 1.0);
      writeSpellFile("C:/Jono/Documents/Programming/Vive/ViveTest/Assets/SpellData/Barrier.spell", new PathSample(SpellPathes.BARRIER, SAMPLES, true), 1.1);
      writeSpellFile("C:/Jono/Documents/Programming/Vive/ViveTest/Assets/SpellData/Wind.spell", new PathSample(SpellPathes.WIND, SAMPLES, true), 0.8);
      writeSpellFile("C:/Jono/Documents/Programming/Vive/ViveTest/Assets/SpellData/Pentagram.spell", new PathSample(SpellPathes.PENTAGRAM, SAMPLES, true), 1.0);
      writeSpellFile("C:/Jono/Documents/Programming/Vive/ViveTest/Assets/SpellData/Missile.spell", new PathSample(SpellPathes.MAGIC_MISSILE, SAMPLES, true), 0.9);
      writeSpellFile("C:/Jono/Documents/Programming/Vive/ViveTest/Assets/SpellData/Heal.spell", new PathSample(SpellPathes.HEAL, SAMPLES, true), 1.0);
      writeSpellFile("C:/Jono/Documents/Programming/Vive/ViveTest/Assets/SpellData/Blast.spell", new PathSample(SpellPathes.MAGIC_BLAST, SAMPLES, true), 0.7);
      PathViewer.writeImage(new PathSample(SpellPathes.FIREBALL, 800, false), "C:/Jono/Documents/Programming/Vive/ViveTest/Assets/SpellData/Images/Fireball.png");
      PathViewer.writeImage(new PathSample(SpellPathes.FIREBALL_2, 800, false), "C:/Jono/Documents/Programming/Vive/ViveTest/Assets/SpellData/Images/Fireball2.png");
      PathViewer.writeImage(new PathSample(SpellPathes.FIREBLAST, 800, false), "C:/Jono/Documents/Programming/Vive/ViveTest/Assets/SpellData/Images/Fireblast.png");
      PathViewer.writeImage(new PathSample(SpellPathes.BARRIER, 800, false), "C:/Jono/Documents/Programming/Vive/ViveTest/Assets/SpellData/Images/Barrier.png");
      PathViewer.writeImage(new PathSample(SpellPathes.PENTAGRAM, 800, false), "C:/Jono/Documents/Programming/Vive/ViveTest/Assets/SpellData/Images/Pentagram.png");
      PathViewer.writeImage(new PathSample(SpellPathes.HEAL, 800, false), "C:/Jono/Documents/Programming/Vive/ViveTest/Assets/SpellData/Images/Heal.png");
      PathViewer.writeImage(new PathSample(SpellPathes.MAGIC_BLAST, 800, false), "C:/Jono/Documents/Programming/Vive/ViveTest/Assets/SpellData/Images/Blast.png");
      PathViewer.writeImage(new PathSample(SpellPathes.MAGIC_MISSILE, 800, false), "C:/Jono/Documents/Programming/Vive/ViveTest/Assets/SpellData/Images/Missile.png");
      PathViewer.writeImage(new PathSample(SpellPathes.WIND, 800, false), "C:/Jono/Documents/Programming/Vive/ViveTest/Assets/SpellData/Images/Wind.png");
   }
}
