package lab1;

public class ComplexExpressionMultiply extends ComplexExpression{
    public ComplexExpressionMultiply(NumarComplex[] args) {
        super(args);
    }

    @Override
    public NumarComplex executeOneOperation(NumarComplex a, NumarComplex b) {
        return a.inmultire(b);
    }
}
