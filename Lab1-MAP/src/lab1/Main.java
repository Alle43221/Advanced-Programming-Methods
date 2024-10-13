package lab1;

import java.util.Arrays;
import java.math.BigDecimal;
//TIP To <b>Run</b> code, press <shortcut actionId="Run"/> or
// click the <icon src="AllIcons.Actions.Execute"/> icon in the gutter.
public class Main {
    public static void main(String[] args) {
        System.out.println(Arrays.toString(args));
        ExpressionParser parser =new ExpressionParser(args);
        ComplexExpression expression=parser.build();

        System.out.println(expression.execute());
    }
}