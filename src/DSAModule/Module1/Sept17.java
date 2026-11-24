package DSAModule.Module1;

public class Sept17 {
    public static void main(String[] args) {
        System.out.println(numSetBits(23));
        System.out.println(countFactors(9));

    }

    public static int numSetBits(int A) {
        int count = 0;
        while (A > 0) {
            if ((A & 1) == 1) { // A&1 -> will provide the last bit of number
                count++;
            }
            A = A >> 1; // shift to next bit for same number
        }
        return count;
    }

    public static int countFactors(int A) {
        int count = 0;
        for (int i = 1; i * i <= A; i++) {
            if (A % i == 0) {
                if (i * i == A) {
                    count++;
                }
                count += 2;
            }
        }
        return count;
    }
}
