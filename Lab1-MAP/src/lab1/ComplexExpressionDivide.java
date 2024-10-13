package lab1;

public class ComplexExpressionDivide extends ComplexExpression{
    public ComplexExpressionDivide(NumarComplex[] args) {
        super(args);
    }

    @Override
    public NumarComplex executeOneOperation(NumarComplex a, NumarComplex b) {
        return a.impartire(b);
    }
}
