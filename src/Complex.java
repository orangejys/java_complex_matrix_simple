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

    public String toString() {
        return real + (imaginary >= 0 ? " + " : " - ")
                + Math.abs(imaginary) + "i";
    }
}
