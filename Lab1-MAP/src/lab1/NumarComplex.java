package lab1;

public class NumarComplex {
    private double re, im;

    public NumarComplex() {
        this.re = 0;
        this.im = 0;
    }

    public NumarComplex(double re, double im) {
        this.re = re;
        this.im = im;
    }

    public NumarComplex adunare(NumarComplex a) {
        this.re += a.re;
        this.im += a.im;
        return this;
    }

    public NumarComplex scadere(NumarComplex a) {
        this.re -= a.re;
        this.im -= a.im;
        return this;
    }

    public NumarComplex inmultire(NumarComplex a) {
        double real = this.re * a.re - this.im * a.im;
        double imag = this.re * a.im + this.im * a.re;
        this.re = real;
        this.im = imag;
        return this;
    }

    public NumarComplex conjugatul() {
        this.im -= 2 * this.im;
        return this;
    }

    public NumarComplex impartire(NumarComplex a) {
        NumarComplex top = new NumarComplex(this.re, this.im);
        NumarComplex bottom = new NumarComplex(a.re, a.im);
        top.inmultire(a.conjugatul());
        a.conjugatul();
        bottom.inmultire(a.conjugatul());
        //System.out.println(top.re+" "+top.im+" "+bottom.re+" "+bottom.im);
        this.re = top.re / bottom.re;
        this.im = top.im / bottom.re;
        return this;
    }

    @Override
    public String toString() {
        String aux = "";
        if (re != 0) {
            aux += re;
            if (im > 0) {
                aux += "+" + im + "*i";
            } else if (im < 0) {
                aux += im + "*i";
            }
        } else {
            if (im != 0) {
                aux += im + "*i";
            } else {
                aux = "0";
            }
        }

        return aux;
    }
}
