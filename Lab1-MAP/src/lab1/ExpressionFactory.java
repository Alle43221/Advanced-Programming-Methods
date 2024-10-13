package lab1;

public class ExpressionFactory {
    private static final ExpressionFactory instance = new ExpressionFactory();

    private ExpressionFactory() {
    }

    public static ExpressionFactory getInstance() {
        return instance;
    }

    public ComplexExpression createExpression(Operation operation, NumarComplex[] args) {
        return switch (operation) {
            case ADDITION -> new ComplexExpressionAdd(args);
            case SUBTRACTION -> new ComplexExpressionSubtract(args);
            case MULTIPLICATION -> new ComplexExpressionMultiply(args);
            case DIVISION -> new ComplexExpressionDivide(args);
        };
    }
}
