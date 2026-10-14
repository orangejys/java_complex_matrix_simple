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

    public ComplexMatrix transpose() {
        ComplexMatrix result = new ComplexMatrix(columns, rows);

        for (int i = 0; i < rows; i++) {
            for (int j = 0; j < columns; j++) {
                result.elements[j][i] = elements[i][j];
            }
        }

        return result;
    }

    public Complex determinant() {
        if (rows != columns) {
            throw new IllegalArgumentException("Определитель существует только у квадратной матрицы");
        }

        Complex[][] copy = new Complex[rows][columns];

        for (int i = 0; i < rows; i++) {
            for (int j = 0; j < columns; j++) {
                copy[i][j] = elements[i][j];
            }
        }

        Complex result = new Complex(1, 0);

        for (int column = 0; column < columns; column++) {
            int pivotRow = column;

            for (int row = column + 1; row < rows; row++) {
                if (copy[row][column].SquaredElement() > copy[pivotRow][column].SquaredElement()) {
                    pivotRow = row;
                }
            }

            if (copy[pivotRow][column].isZero()) {
                return new Complex(0, 0);
            }

            if (pivotRow != column) {
                Complex[] temporary = copy[column];
                copy[column] = copy[pivotRow];
                copy[pivotRow] = temporary;

                result = new Complex(-result.getReal(), -result.getImaginary());
            }

            Complex pivot = copy[column][column];
            result = result.multiply(pivot);

            for (int row = column + 1; row < rows; row++) {
                Complex factor = copy[row][column].divide(pivot);

                for (int j = column; j < columns; j++) {
                    Complex product = factor.multiply(copy[column][j]);

                    copy[row][j] = copy[row][j].subtract(product);
                }
            }
        }

        return result;
    }

    private ComplexMatrix minor(int excludedRow, int excludedColumn){
        ComplexMatrix result = new ComplexMatrix(rows - 1, columns - 1);
        int resultRow = 0;

        for (int i = 0; i < rows; i++){
            if (i == excludedRow) {
                continue;
            }

            int resultColumn = 0;

            for (int j = 0; j < columns; j++) {
                if (j == excludedColumn) {
                    continue;
                }

                result.elements[resultRow][resultColumn] = elements[i][j];

                resultColumn++;
            }
        resultRow++;
        }

        return result;
    }

    public ComplexMatrix inverse() {
        if (rows != columns) {
            throw new IllegalArgumentException("Обратная существует только для квадратной матрицы");
        }

        Complex determinant = determinant();

        if (determinant.isZero()) {
            throw new ArithmeticException("Матрица не имеет обратной");
        }

        ComplexMatrix result = new ComplexMatrix(rows, columns);

        for (int i = 0; i < rows; i++) {
            for (int j = 0; j < columns; j++) {
                Complex algeb_dopolnenie = minor(i, j).determinant();

                if ((i + j) % 2 != 0) {
                    algeb_dopolnenie = new Complex(-algeb_dopolnenie.getReal(), -algeb_dopolnenie.getImaginary());
                }

                result.elements[j][i] = algeb_dopolnenie.divide(determinant);
            }
        }

        return result;
    }
    //  A / B = A · B⁻¹
    public ComplexMatrix divide(ComplexMatrix other) {
        return multiply(other.inverse());
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
