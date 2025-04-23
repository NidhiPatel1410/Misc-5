// In this problem using a stack to store the frequency of characters in string. Also converting string to stringbuilder, and then
// start iterating over the sb. Whenever we find that stack is empty or the current char does not matches the previous char, we put
// in stack 1 (freq of current char). Then if we find that stack is not empty and also the current char matches the previous char
// then we pop whatever on top of stack, add 1 to it and push to stack. We also check if the freq becomes equal to k, then from sb
// we delete that substring of length k.

// Time Complexity : O(n^2) going over and inside delete operation which also takes O(n) everytime
// Space Complexity : O(n) sb and stack
// Did this code successfully run on Leetcode : yes
// Any problem you faced while coding this : no
class Solution {
    public String removeDuplicates(String s, int k) {
        // Base case
        if (s == null || s.length() == 0) {
            return s;
        }
        StringBuilder sb = new StringBuilder(s);
        Stack<Integer> st = new Stack<>();
        // Loop
        for (int i = 0; i < sb.length(); i++) {
            // Take one char at a time
            char c = sb.charAt(i);
            // Check if stack is empty or previous char is not same, then the freq of
            // current char will be 1
            if (st.isEmpty() || (sb.charAt(i - 1) != c)) {
                // so push 1
                st.push(1);
            } else {
                // Else if the chars are same pop from the stack
                int cnt = st.pop();
                // If adding one become equals to k, then do delete operation
                if (cnt + 1 == k) {
                    sb.delete(i - k + 1, i + 1);
                    // Adjust i
                    i = i - k;
                } else {
                    // Else push with the increment
                    st.push(cnt + 1);
                }
            }
        }
        return sb.toString();
    }
}

// In this approach, we are storing in stack the pair of char and it's
// frequency, whenever frequency becomes equal to k, we just
// remove that pair from the stack. At end, we iterate over the stack and pop
// one by one and build the string, then reverse it and
// return.

// Time Complexity : O(n)
// Space Complexity : O(n) sb (stack size will be constant since 26 chars at
// max)
// Did this code successfully run on Leetcode : yes
// Any problem you faced while coding this : no
class Solution {
    // Class for storing the character and it's frequency pair
    class Pair {
        char c1;
        int x;

        Pair(char c1, int x) {
            this.c1 = c1;
            this.x = x;
        }
    }

    public String removeDuplicates(String s, int k) {
        // Base case
        if (s == null || s.length() == 0) {
            return s;
        }
        Stack<Pair> st = new Stack<>();
        StringBuilder sb = new StringBuilder();
        // Loop over
        for (int i = 0; i < s.length(); i++) {
            // Take one char at a time
            char c = s.charAt(i);
            // If the stack is empty or char on top of stack is not equal to the current
            // char then push the current char with freq 1
            if (st.isEmpty() || (st.peek().c1 != c)) {
                st.push(new Pair(c, 1));
            } else {
                // Else, take the peek one
                Pair p = st.peek();
                // If addning 1 to freq becomes k, pop the pair
                if (p.x + 1 == k) {
                    st.pop();
                } else {
                    // Else pop, increment and push new pair
                    st.pop();
                    st.push(new Pair(c, p.x + 1));
                }
            }
        }
        // Go over the stack
        while (!st.isEmpty()) {
            Pair pp = st.pop();
            // Build the string, add each chars the number of times of it's frequency
            for (int j = 0; j < pp.x; j++) {
                sb.append(pp.c1);
            }
        }
        // Reverse
        sb.reverse();
        // Return string
        return sb.toString();
    }
}