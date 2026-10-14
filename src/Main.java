import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        boolean running = true;
        ComplexMatrix lastResult = null;

        while (running) {
            printMenu();
            int choice = scanner.nextInt();

            try {
                if (choice == 0) {
                    running = false;
                } else if (choice == 5) {
                    ComplexMatrix matrix = chooseMatrix(scanner, lastResult, "матрицу");
                    lastResult = matrix.transpose();

                    System.out.println("Результат:");
                    System.out.println(lastResult);
                } else if (choice == 6) {
                    ComplexMatrix matrix = chooseMatrix(scanner, lastResult, "матрицу");

                    System.out.println("Определитель: " + matrix.determinant());
                } else if (choice >= 1 && choice <= 4) {
                    ComplexMatrix first = chooseMatrix(scanner, lastResult, "первую матрицу");
                    ComplexMatrix second = readMatrix(scanner, "вторую матрицу");

                    if (choice == 1) {
                        lastResult = first.add(second);
                    } else if (choice == 2) {
                        lastResult = first.subtract(second);
                    } else if (choice == 3) {
                        lastResult = first.multiply(second);
                    } else {
                        lastResult = first.divide(second);
                    }

                    System.out.println("Результат:");
                    System.out.println(lastResult);
                } else {
                    System.out.println("Неизвестная операция");
                }
            } catch (RuntimeException exception) {
                System.out.println("Ошибка: " + exception.getMessage());
            }
        }

        scanner.close();
    }

    private static void printMenu() {
        System.out.println("1 — сложение");
        System.out.println("2 — вычитание");
        System.out.println("3 — умножение");
        System.out.println("4 — деление");
        System.out.println("5 — транспонирование");
        System.out.println("6 — определитель");
        System.out.println("0 — выход");
        System.out.print("Выберите операцию: ");
    }

    private static ComplexMatrix chooseMatrix(
            Scanner scanner,
            ComplexMatrix lastResult,
            String name
    ) {
        if (lastResult != null) {
            System.out.println("Использовать результат предыдущей операции?");
            System.out.println("1 — да");
            System.out.println("0 — нет");
            System.out.print("Ваш выбор: ");

            if (scanner.nextInt() == 1) {
                return lastResult;
            }
        }

        return readMatrix(scanner, name);
    }

    private static ComplexMatrix readMatrix(Scanner scanner, String name) {
        System.out.println("Введите " + name);

        System.out.print("Количество строк: ");
        int rows = scanner.nextInt();

        System.out.print("Количество столбцов: ");
        int columns = scanner.nextInt();

        ComplexMatrix matrix = new ComplexMatrix(rows, columns);

        for (int i = 0; i < rows; i++) {
            for (int j = 0; j < columns; j++) {
                System.out.println("Элемент [" + i + "][" + j + "]");

                System.out.print("Действительная часть: ");
                double real = scanner.nextDouble();

                System.out.print("Мнимая часть: ");
                double imaginary = scanner.nextDouble();

                matrix.setElement(i, j, new Complex(real, imaginary));
            }
        }

        return matrix;
    }
}
