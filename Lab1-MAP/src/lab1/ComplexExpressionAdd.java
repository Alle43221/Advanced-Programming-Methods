package lab1;

public class ComplexExpressionAdd extends ComplexExpression {
    public ComplexExpressionAdd(NumarComplex[] args) {
        super(args);
    }

    @Override
    public NumarComplex executeOneOperation(NumarComplex a, NumarComplex b) {
        return a.adunare(b);
    }
}
