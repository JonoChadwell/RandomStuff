package aplay;
import java.awt.Color;
import java.awt.Graphics;

import javax.swing.JFrame;

public class PlayableViewer {
   public PlayableViewer(final Playable playable, final double scale) {
      JFrame frame = new JFrame() {
         public void paint(Graphics g) {
            super.paint(g);
            g.setColor(Color.black);
            g.fillRect(0, 0, this.getWidth(), this.getHeight());
            g.setColor(Color.blue);
            g.drawLine(0, this.getHeight() / 2, this.getWidth(), this.getHeight() / 2);
            for (int i = 0; i < this.getWidth(); i++) {
               double val = playable.sample(i * scale);
               int pos = this.getHeight() / 2 + (int) (val * this.getHeight() / 3);
               if (val > 1 || val < -1) {
                  g.setColor(Color.red);
               } else {
                  g.setColor(Color.green);
               }
               g.drawLine(i, pos - 4, i, pos + 4);
            }
         }
      };
      frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
      frame.setSize(800, 255);
      frame.setVisible(true);
   }
}
