class Solution {
    public int maxLength(int[] nums) {
        int n = nums.length;
        int maxVal = 0;
        long overallLcm = 1;

        for (int num : nums) {
            maxVal = Math.max(maxVal, num);
            overallLcm = lcm(overallLcm, num);
        }

        // Any valid subarray has gcd <= maxVal and lcm <= overallLcm
        long maxPossible = overallLcm * maxVal;
        int ans = 0;

        for (int i = 0; i < n; i++) {
            long prod = 1;
            long g = 0;
            long l = 1;

            for (int j = i; j < n; j++) {
                long val = nums[j];
                prod *= val;
                if (prod > maxPossible) {
                    break;
                }

                g = gcd(g, val);
                l = lcm(l, val);

                if (prod == l * g) {
                    ans = Math.max(ans, j - i + 1);
                }
            }
        }

        return ans;
    }

    private long gcd(long a, long b) {
        while (b != 0) {
            long temp = b;
            b = a % b;
            a = temp;
        }
        return a;
    }

    private long lcm(long a, long b) {
        return (a / gcd(a, b)) * b;
    }
}