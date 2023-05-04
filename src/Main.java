import java.util.ArrayList;
import java.util.Arrays;

public class Main {
    public static void main(String[] args) {
        sum1();
        sum2();
        queens();
    }

    private static void queens() {
        ArrayList<Integer> list = new ArrayList<>();
        int[][] field = new int[8][8];
        int count = 0;
        int i = 0, j = 0;
        boolean f = false;  // Флаг того, что поставлен ферзь в текущей строке
        while (i < 8) {
            f = false;
            while (j < 8) {
                if (field[i][j] == 0) {
                    // Клетка свободна - ставим туда ферзя
                    addQueen(i, j, field);
                    list.add(j);
                    i++;
                    j = 0;
                    f = true;
                    break;
                }
                j++;
            }
            if (!f) {
                // Если ферзя поставить не удалось
                // Извлекаем из стека последнего поставленного ферзя и ищем ему другое место
                if (list.size() > 0) {
                    j = list.remove(list.size() - 1);
                    removeQueen(i - 1, j, field);
                    i--;
                    j++;
                } else {
                    // Если в стеке уже нет ферзей, то значит и нет уже решений - заканчиваем
                    break;
                }
            } else {
                // Очередного ферзя удалось поставить
                if (list.size() == 8) {
                    // Нашли решение - выводим его
                    count++;
                    print(count, list);
                    j = list.remove(list.size() - 1);
                    removeQueen(7, j, field);
                    i = 7;
                    j++;
                }
            }
        }
    }

    private static void print(int count, ArrayList<Integer> list) {
        System.out.println(count);
        for (int i = 0; i < 8; i++) {
            int column = list.get(i);
            for (int j = 0; j < 8; j++) {
                if (j != column) {
                    System.out.print("0");
                } else {
                    System.out.print("*");
                }
            }
            System.out.println();
        }
    }

    private static void addQueen(int row, int column, int[][] field) {
        for (int i = 0; i < 8; i++) {
            field[row][i]++;
            field[i][column]++;
        }
        // TODO Переделать на более красивые циклы
        int i = row, j = column;
        while (i < 8 && j < 8) {
            field[i][j]++;
            i++;
            j++;
        }
        i = row;
        j = column;
        while (i < 8 && j >= 0) {
            field[i][j]++;
            i++;
            j--;
        }
        i = row;
        j = column;
        while (i >= 0 && j >= 0) {
            field[i][j]++;
            i--;
            j--;
        }
        i = row;
        j = column;
        while (i >= 0 && j < 8) {
            field[i][j]++;
            i--;
            j++;
        }
        field[row][column] = -1;
    }

    private static void removeQueen(int row, int column, int[][] field) {
        for (int i = 0; i < 8; i++) {
            field[row][i]--;
            field[i][column]--;
        }
        // TODO Переделать на более красивые циклы
        int i = row, j = column;
        while (i < 8 && j < 8) {
            field[i][j]--;
            i++;
            j++;
        }
        i = row;
        j = column;
        while (i < 8 && j >= 0) {
            field[i][j]--;
            i++;
            j--;
        }
        i = row;
        j = column;
        while (i >= 0 && j >= 0) {
            field[i][j]--;
            i--;
            j--;
        }
        i = row;
        j = column;
        while (i >= 0 && j < 8) {
            field[i][j]--;
            i--;
            j++;
        }
        field[row][column] = 0;
    }

    private static void sum1() {
        int[] number = {3, 4, 5};
        int s = 0;
        int len = number.length;
        for (int i = 0; i < len; i++) {
            s = s + number[len - i - 1] * (int) Math.pow(10, i);
        }
        s = s + 1;
        ArrayList<Integer> number2 = new ArrayList<>();
        while (s > 0) {
            number2.add(0, s % 10);
            s = s / 10;
        }
        System.out.println(number2);
    }

    private static void sum2() {
        int[] number = {9, 9, 9};
        for (int i = number.length - 1; i >= 0; i--) {
            if (number[i] != 9) {
                number[i] = number[i] + 1;
                break;
            }
            number[i] = 0;
        }
        if (number[0] == 0) {
            int[] number2 = new int[number.length + 1];
            number2[0] = 1;
            number = number2;
        }
        System.out.println(Arrays.toString(number));
    }
}