public class Test {
    public static void main(String[] args) {
        ComplexMatrix matrix = new ComplexMatrix(2, 2);

        matrix.setElement(0, 0, new Complex(1, 0));
        matrix.setElement(0, 1, new Complex(2, 0));
        matrix.setElement(1, 0, new Complex(3, 0));
        matrix.setElement(1, 1, new Complex(4, 0));

        System.out.println("Матрица:");
        System.out.println(matrix);

        System.out.println("Транспонированная:");
        System.out.println(matrix.transpose());

        System.out.println("Определитель:");
        System.out.println(matrix.determinant());

        System.out.println("Обратная:");
        ComplexMatrix inverse = matrix.inverse();
        System.out.println(inverse);

        System.out.println("Проверка A * A^-1:");
        System.out.println(matrix.multiply(inverse));
    }
}