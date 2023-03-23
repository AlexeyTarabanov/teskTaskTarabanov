package test.task2;

public class ArithmeticOperations {

    public static int add(int a, int b) {
        return a + b;
    }

    public static int subs(int a, int b) {
        return a - b;
    }

    public static int mult(int a, int b) {
        return a * b;
    }

    public static int divide(int a, int b) {
        if (b == 0) {
            throw new IllegalArgumentException("На ноль делить нельзя");
        }
        return a / b;
    }
}
