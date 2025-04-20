// In this approach, using sliding window technique. Keeping a left pointer starting at index 0. Running a loop from i=0 to n. Then checking if
// the current element is 0, decrementing k, anytime the k value becomes negative, it indicates that we have converted one extra zero to 1. So,
// leave one zero from the window, so for that move left. And if the left value was zero, just increment the k. 

// Time Complexity : O(n)
// Space Complexity : O(1)
// Did this code successfully run on Leetcode : yes
// Any problem you faced while coding this : no

class Solution {
    public int longestOnes(int[] nums, int k) {
        // Base case
        if (nums == null || nums.length == 0) {
            return 0;
        }
        // Take the left
        int left = 0;
        // Loop over the nums
        for (int i = 0; i < nums.length; i++) {
            // If 0, decrement the k indicating that we are converting it to 1.
            if (nums[i] == 0) {
                k--;
            }
            // If k becomes negative, that means one extra 0 is in the window, we are only
            // allowed to keep k zeros
            if (k < 0) {
                // So leave one zero, move left and if it was 0 that increment k
                if (nums[left] == 0) {
                    k++;
                }
                // Do left++ in any case
                left++;
            }
        }
        // Return the window size at end
        return nums.length - left;
    }
}