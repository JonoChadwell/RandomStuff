package spell;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.image.BufferedImage;
import java.io.File;

import javax.imageio.ImageIO;
import javax.swing.JFrame;
import javax.swing.JPanel;

public class PathViewer {
   private JFrame frame;
   private JPanel panel;

   private static final double X_SCALAR = 276;
   private static final int X_SHIFT = 12;
   private static final double Y_SCALAR = 276;
   private static final int Y_SHIFT = 12;
   private static final int WIDTH = 300;
   private static final int HEIGHT = 300;

   @SuppressWarnings("serial")
   public PathViewer(PathSample sample) {
      panel = new JPanel() {
         @Override
         public void paint(Graphics g) {
            super.paint(g);
            g.setColor(Color.BLACK);
            g.fillRect(0, 0, panel.getWidth(), panel.getHeight());
            paintPath(g, sample);
         }
      };
      panel.setPreferredSize(new Dimension(WIDTH, HEIGHT));
      frame = new JFrame();
      frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
      frame.setContentPane(panel);
      frame.pack();
      frame.setVisible(true);
   }

   public static void writeImage(PathSample sample, String filename) {
      try {
         BufferedImage img = new BufferedImage(WIDTH, HEIGHT, BufferedImage.TYPE_4BYTE_ABGR);
         Graphics g = img.getGraphics();
         paintPath(g, sample);
         ImageIO.write(img, "png", new File(filename));
      } catch (Exception ex) {

      }
   }

   private static void paintPath(Graphics g, PathSample sample) {
      int partsToDraw = sample.getLength();
      for (int i = 0; i < partsToDraw; i++) {
         float pos = (float) i / (float) sample.getLength();
         g.setColor(new Color(0f, 1 - pos, pos, 0.2f));
         Point pt = sample.getPoint(i);
         drawPoint(g, pt, (int) (6 * (1 - pos)) + 5);
      }
   }

   private static void drawPoint(Graphics g, Point pt, int pointRadius) {
      int x = (int) (pt.x * X_SCALAR) + X_SHIFT - pointRadius;
      int y = (int) (pt.y * Y_SCALAR) + Y_SHIFT - pointRadius;
      g.fillOval(x, y, pointRadius * 2, pointRadius * 2);
   }

   public static void main(String[] args) {
      for (Path spell : SpellPathes.SPELLS) {
         if (spell != SpellPathes.PENTAGRAM) continue;
         new PathViewer(new PathSample(spell, 800, false));
      }
   }
}
