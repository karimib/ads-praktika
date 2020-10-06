import java.util.AbstractMap;
import java.util.HashMap;
import java.util.Map;

public class BracketServerExtended {


    private ListStack<Character> stack;
    private Map<String, String> chars = Map.ofEntries(
            new AbstractMap.SimpleEntry<>("<", ">"),
            new AbstractMap.SimpleEntry<>("(", ")"),
            new AbstractMap.SimpleEntry<>("{", "}"),
            new AbstractMap.SimpleEntry<>("*", "*"),
            new AbstractMap.SimpleEntry<>("<*", "*>") //TODO:
    );

    public String execute(String s) {
        return Boolean.toString(checkBrackets(s));
    }


    /**
     * Checks whether a given string is bracket balanced
     *
     * @param s A string
     * @return true if brackets are balanced
     */
    public boolean checkBrackets(String s) {
        if (s == null || s.length() < 2) {
            return false;
        }
        stack = new ListStack<>();
        for (char c : s.toCharArray()) {
            if (chars.containsKey(c)) {
                stack.push(c);
            } else if (chars.containsValue(c)) {
                if (!stack.isEmpty() && chars.get(stack.peek(c))) {
                    stack.pop();
                } else {
                    return false;
                }
            }
        }
        return true;
    }


}
