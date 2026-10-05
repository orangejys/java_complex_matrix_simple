public class ComplexMatrix {
    private int rows;
    private int columns;
    private Complex[][] elements;

    public ComplexMatrix(int rows, int columns) {
        this.rows = rows;
        this.columns = columns;
        elements = new Complex[rows][columns];

        for (int i = 0; i < rows; i++) {
            for (int j = 0; j < columns; j++) {
                elements[i][j] = new Complex(0, 0);
            }
        }
    }

    public void setElement(int row, int column, Complex value) {
        elements[row][column] = value;
    }

    public Complex getElement(int row, int column) {
        return elements[row][column];
    }

    public ComplexMatrix add(ComplexMatrix other) {
        if (rows != other.rows || columns != other.columns) {
            throw new IllegalArgumentException("Размеры матриц должны совпадать");
        }

        ComplexMatrix result = new ComplexMatrix(rows, columns);

        for (int i = 0; i < rows; i++) {
            for (int j = 0; j < columns; j++) {
                result.elements[i][j] = elements[i][j].add(other.elements[i][j]);
            }
        }

        return result;
    }

    public ComplexMatrix subtract(ComplexMatrix other) {
        if (rows != other.rows || columns != other.columns) {
            throw new IllegalArgumentException("Размеры матриц должны совпадать");
        }

        ComplexMatrix result = new ComplexMatrix(rows, columns);

        for (int i = 0; i < rows; i++) {
            for (int j = 0; j < columns; j++) {
                result.elements[i][j] = elements[i][j].subtract(other.elements[i][j]);
            }
        }

        return result;
    }

    public ComplexMatrix multiply(ComplexMatrix other) {
        if (columns != other.rows) {
            throw new IllegalArgumentException("Матрицы нельзя умножить");
        }

        ComplexMatrix result = new ComplexMatrix(rows, other.columns);

        for (int i = 0; i < rows; i++) {
            for (int j = 0; j < other.columns; j++) {
                Complex sum = new Complex(0, 0);

                for (int k = 0; k < columns; k++) {
                    sum = sum.add(elements[i][k].multiply(other.elements[k][j]));
                }

                result.elements[i][j] = sum;
            }
        }

        return result;
    }

    public String toString() {
        String result = "";

        for (int i = 0; i < rows; i++) {
            for (int j = 0; j < columns; j++) {
                result += elements[i][j] + "  ";
            }
            result += "\n";
        }

        return result;
    }
}
