public class Test {
    public static void main(String[] args){
        ComplexMatrix matrix = new ComplexMatrix(2, 3);

        matrix.setElement(0, 0, new Complex(1, 0));
        matrix.setElement(0, 1, new Complex(2, 0));
        matrix.setElement(0, 2, new Complex(3, 0));
        matrix.setElement(1, 0, new Complex(4, 0));
        matrix.setElement(1, 1, new Complex(5, 0));
        matrix.setElement(1, 2, new Complex(6, 0));

        System.out.println(matrix);
        System.out.println(matrix.transpose());

        /*
        Вывод моего теста
        G:\Java_jdk\bin\java.exe "-javaagent:G:\Intel_IJ\IntelliJ IDEA 2026.2.3\lib\idea_rt.jar=58992" -Dfile.encoding=UTF-8 -Dsun.stdout.encoding=UTF-8 -Dsun.stderr.encoding=UTF-8 -classpath G:\IdeaProjects\java_matrix\src\out\production\java_matrix Test
        1.0 + 0.0i  2.0 + 0.0i  3.0 + 0.0i
        4.0 + 0.0i  5.0 + 0.0i  6.0 + 0.0i

        1.0 + 0.0i  4.0 + 0.0i
        2.0 + 0.0i  5.0 + 0.0i
        3.0 + 0.0i  6.0 + 0.0i


        Process finished with exit code 0
         */
    }
}
