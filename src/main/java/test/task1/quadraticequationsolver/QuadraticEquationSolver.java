package test.task1.quadraticequationsolver;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@SpringBootApplication
@RestController
public class QuadraticEquationSolver {

    public static void main(String[] args) {

        SpringApplication.run(QuadraticEquationSolver.class, args);
    }

    @GetMapping("/solve")
    public String solveQuadraticEquation(
            @RequestParam(value = "a") int a,
            @RequestParam(value = "b") int b,
            @RequestParam(value = "c") int c) {
        double discriminant = b * b - 4 * a * c;
        if (discriminant > 0) {
            double root1 = (-b + Math.sqrt(discriminant)) / (2 * a);
            double root2 = (-b - Math.sqrt(discriminant)) / (2 * a);
            return "Корни уравнения: " + root1 + " и " + root2;
        } else if (discriminant == 0) {
            double root = -b / (2 * a);
            return "Уравнение имеет один корень:  " + root;
        } else {
            return "Уравнение не имеет действительных корней";
        }
    }
}
