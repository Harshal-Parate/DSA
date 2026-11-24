package DSAModule.Module1;

public class Sept28 {

    public int unsetIthBit(int A, int B) {
        int mask = 1 << B;
        return A & ~mask;
    }

    public int toggle(int A, int B) {
        return A ^ (1 << B);
    }

    public int set(int A, int B) {
        return A | (1 << B);
    }

    public int solve(int A, int B) {
        return (1 << A) | (1 << B);
    }

    /*

    Set    → |
    Unset  → & ~
    Toggle → ^

     */


}
