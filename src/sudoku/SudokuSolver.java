package sudoku;

import java.util.LinkedList;
import java.util.List;

public class SudokuSolver {
   private final List<Integer>[][] possibilities;
   private final int size;
   
   @SuppressWarnings("unchecked")
   public SudokuSolver(int size) {
      this.size = size;
      possibilities = (List<Integer>[][]) new Object[size][size];
      for (int i = 0; i < size; i++) {
         for (int j = 0; j < size; j++) {
            possibilities[i][j] = new LinkedList<Integer>();
            for (int k = 0; k < size; k++) {
               possibilities[i][j].add(k);
            }
         }
      }
   }
}
