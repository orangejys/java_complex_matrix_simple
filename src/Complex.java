public class Complex {
    private double real;
    private double imaginary;

    public Complex(double real, double imaginary) {
        this.real = real;
        this.imaginary = imaginary;
    }

    public double getReal() {
        return real;
    }

    public double getImaginary() {
        return imaginary;
    }

    public Complex add(Complex other) {
        return new Complex(
                real + other.real,
                imaginary + other.imaginary
        );
    }

    public Complex subtract(Complex other) {
        return new Complex(
                real - other.real,
                imaginary - other.imaginary
        );
    }

    public Complex multiply(Complex other) {
        double newReal = real * other.real - imaginary * other.imaginary;
        double newImaginary = real * other.imaginary + imaginary * other.real;
        return new Complex(newReal, newImaginary);
    }

    public Complex divide(Complex other) {
        double znamenatel = other.real * other.real + other.imaginary * other.imaginary;


        if (znamenatel == 0){
            throw new ArithmeticException("На ноль делиьт нельзя!")
        }

        double newReal = (real * other.real + imaginary * other.imaginary) / znamenatel;

        double newImaginary = (imaginary * other.real - real * other.imaginary) / znamenatel;

        return new Complex(NewReal, NewImaginary)
    }

    public boolean isZero() {
        return Math.abs(real) < 0.000000001
                && Math.abs(imaginary) < 0.000000001;
    }

    public String toString() {
        return real + (imaginary >= 0 ? " + " : " - ")
                + Math.abs(imaginary) + "i";
    }
}
