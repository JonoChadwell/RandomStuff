package automata;

import java.util.List;
import java.util.Set;
import java.util.function.BiFunction;

public class DeterministicFiniteAutomaton<A extends Enum<A>> {

   @SuppressWarnings("serial")
   public static final class StateException extends RuntimeException {};

   private final Set<String> states;
   private final String initial;
   private final BiFunction<String, A, String> delta;
   private final Set<String> terminal;

   public DeterministicFiniteAutomaton(Set<String> states, String initial, BiFunction<String, A, String> delta, Set<String> terminal) {
      this.states = states;
      this.initial = initial;
      this.delta = delta;
      this.terminal = terminal;
      if (!states.contains(initial)) {
         throw new StateException();
      }
   }

   public boolean isInLanguage(List<A> input) {
      return terminal.contains(trace(input));
   }

   private String trace(List<A> input) {
      String state = initial;
      for (A symbol : input) {
         state = delta.apply(state, symbol);
         if (!states.contains(state)) {
            throw new StateException();
         }
      }
      return state;
   }

}
