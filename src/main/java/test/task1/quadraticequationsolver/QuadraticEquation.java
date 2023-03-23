package test.task1.quadraticequationsolver;

/**
 1) Требуется написать программу, которая будет решать уравнение ax^2+bx+c=0 при любых a, b, c.
 Решение уравнения нужно вывести в отдельный метод, который ожидает целочисленные a, b, c
 на вход и печатает строку.
 !!!
 2) Обернуть это в рест-сервис.*/

public class QuadraticEquation {
    public static void main(String[] args) {
        getResult(2, -7, 3);
        getResult(1, -8, 15);
    }

    public static void getResult(int a, int b, int c) {

        String result;

        int discriminant = b * b - 4 * a * c;

        if (discriminant > 0) {
            double x1 = (-b - Math.sqrt(discriminant)) / (2 * a);
            double x2 = (-b + Math.sqrt(discriminant)) / (2 * a);
            result = "Корни уравнения: x1 = " + x1 + ", x2 = " + x2;
        } else if (discriminant == 0) {
            int x;
            x = -b / (2 * a);
            result = "Уравнение имеет единственный корень: x = " + x;
        } else {
            result = "Уравнение не имеет действительных корней";
        }

        System.out.println(result);
    }
}
