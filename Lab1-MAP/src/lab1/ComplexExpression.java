package lab1;

abstract public class ComplexExpression {
    private final NumarComplex[] args;

    public ComplexExpression(NumarComplex[] args) {
        this.args = args;
    }

    public NumarComplex execute() {
        NumarComplex result = args[0];
        for (int i = 1; i < args.length; i++) {
            result = executeOneOperation(result, args[i]);
        }
        return result;
    }

    public abstract NumarComplex executeOneOperation(NumarComplex a, NumarComplex b);
}
