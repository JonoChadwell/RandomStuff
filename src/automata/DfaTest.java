package automata;

import static com.google.common.truth.Truth.assertThat;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import org.junit.Test;

public class DfaTest {

   private static enum Binary {
      ZERO, ONE;
      
      private static final List<Binary> convert(String src) {
         List<Binary> result = new ArrayList<>();
         for (int i = 0; i < src.length(); i++) {
            if (src.charAt(i) == '0') {
               result.add(ZERO);
            } else if (src.charAt(i) == '1') {
               result.add(ONE);
            } else {
               throw new RuntimeException();
            }
         }
         return result;
      }
   }

   @Test
   public void test() {
      @SuppressWarnings("serial")
      Set<String> states = new HashSet<String>() {
         {
            add("q0");
            add("q1");
            add("q2");
            add("q3");
         }
      };
      @SuppressWarnings("serial")
      Set<String> terminalStates = new HashSet<String>() {
         {
            add("q0");
         }
      };
      DeterministicFiniteAutomaton<Binary> dfa = new DeterministicFiniteAutomaton<Binary>(states, "q0", (s, b) -> {
         switch (s) {
         case "q0":
            return Binary.ZERO.equals(b) ? "q3" : "q1";

         case "q1":
            return Binary.ZERO.equals(b) ? "q2" : "q0";

         case "q2":
            return Binary.ZERO.equals(b) ? "q1" : "q3";

         case "q3":
            return Binary.ZERO.equals(b) ? "q0" : "q2";

         default:
            throw new RuntimeException();
         }
      }, terminalStates);
      assertThat(dfa.isInLanguage(Binary.convert("1100"))).isTrue();
      assertThat(dfa.isInLanguage(Binary.convert("11000"))).isFalse();
      assertThat(dfa.isInLanguage(Binary.convert("1000"))).isFalse();
      assertThat(dfa.isInLanguage(Binary.convert("1111"))).isTrue();
      assertThat(dfa.isInLanguage(Binary.convert("11110000000110"))).isTrue();
      assertThat(dfa.isInLanguage(Binary.convert("111100000001100"))).isFalse();
      assertThat(dfa.isInLanguage(Binary.convert("1111000000011001"))).isFalse();
   }

}
