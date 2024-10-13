package lab1;

public class ExpressionParser {
    String[] expression;
    ExpressionFactory factory = ExpressionFactory.getInstance();

    public ExpressionParser(String[] expression) {
        this.expression = expression;
    }

    private boolean verify() {
        if (expression.length < 3) {
            return false;
        }
        if (expression.length % 2 != 1) {
            return false;
        }
        if (expression[1].length() != 1)
            return false;
        char sign = expression[1].charAt(0);
        if (sign != '+' && sign != '-' && sign != '*' && sign != '/') {
            return false;
        }
        for (int i = 0; i < expression.length; i++) {
            if (i % 2 == 1) {
                if (sign != expression[i].charAt(0))
                    return false;
            } else {
                if (!expression[i].matches("-*\\d+[.]*\\d*[+,-]\\d[.]*\\d*+\\*i") &&
                        !expression[i].matches("-*\\d+[.]*\\d*") &&
                        !expression[i].matches("-*\\d+[.]*\\d*\\*i") &&
                        !expression[i].matches("-*\\d+[.]*\\d*[+,-]i") &&
                        !expression[i].matches("-*i")) {
                    return false;
                }
            }
        }
        return true;
    }

    public ComplexExpression build() {
        if (verify()) {
            Operation operation = switch (expression[1].charAt(0)) {
                case '+' -> Operation.ADDITION;
                case '-' -> Operation.SUBTRACTION;
                case '*' -> Operation.MULTIPLICATION;
                case '/' -> Operation.DIVISION;
                default -> null;
            };
            NumarComplex[] args = new NumarComplex[expression.length / 2 + 1];
            double re, im;
            boolean has_im;
            for (int i = 0; i < expression.length; i += i + 2) {

                has_im = expression[i].matches("-*\\d+[.]*\\d*\\*i") ||
                        expression[i].matches("-*\\d+[.]*\\d*[+,-]i") ||
                        expression[i].matches("-*i");

                if (expression[i].matches("-*\\d+[.]*\\d*[+,-]i") || expression[i].matches("-*i")) {
                    expression[i] = expression[i].replaceFirst("i", "1*i");
                }
                String result = expression[i].replaceAll("[^\\d.\\-+]", " ");
                result = result.replaceAll("-", " -");
                result = result.replaceAll("\\+", " +");
                result = result.trim();
                result = result.replaceAll(" +", " ");
                //avem numere separate printr-un singur spatiu

                String[] result_splited = result.split(" ");

                if (result_splited.length == 2) { // de forma a+b*i
                    re = Double.parseDouble(result_splited[0]);
                    im = Double.parseDouble(result_splited[1]);
                } else {
                    if (has_im) {// de forma b*i
                        re = 0;
                        im = Double.parseDouble(result_splited[0]);
                    } else {// de forma a
                        im = 0;
                        re = Double.parseDouble(result_splited[0]);
                    }
                }
                //System.out.println(re+" "+im);

                args[i / 2] = new NumarComplex(re, im);
            }
            return factory.createExpression(operation, args);
        } else {
            return null;
        }
    }
}
