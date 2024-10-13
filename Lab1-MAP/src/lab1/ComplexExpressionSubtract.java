package lab1;

public class ComplexExpressionSubtract extends ComplexExpression{
    public ComplexExpressionSubtract(NumarComplex[] args) {
        super(args);
    }

    @Override
    public NumarComplex executeOneOperation(NumarComplex a, NumarComplex b) {
        return a.scadere(b);
    }
}
