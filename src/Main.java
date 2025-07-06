import java.util.Arrays;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

public class Main {
    private final static StepTracker tracker = new StepTracker();

    public static void main(String[] args) {
        int userInput = -1;

        while (userInput != 4) {
            printMenu();
            userInput = inputNumber("пункт меню", 1, 4);//scanner.nextInt();
            switch (userInput) {
                case 1:
                    inputSteps();
                    break;
                case 2:
                    printStatistics();
                    break;
            }
        }
    }

    private static void printMenu() {
        System.out.println("1. Ввести количество шагов за определенный день");
        System.out.println("2. Напечатать статистику за определенный месяц");
        System.out.println("3. Изменить цель по количеству шагов в день");
        System.out.println("4. Выйти из приложения");
    }

    private static void inputSteps() {
        int month = inputNumber("месяц", 1, 12);
        int day = inputNumber("день", 1, 30);
        int count = inputNumber("количество шагов", 0,  1000000);
        tracker.saveStepsPerDay(month - 1, day - 1, count);
        System.out.println("Количество шагов записано успешно!");
    }

    private static void printStatistics() {
        int month = inputNumber("месяц", 1, 12);
        int[] steps = tracker.monthData.get(month - 1).steps;

        String result = IntStream.range(0, steps.length)
                .mapToObj(i -> i + 1 + " день: " + steps[i])
                .collect(Collectors.joining(", "));
        int countTotal = Arrays.stream(steps).sum();
        int countMax  = Arrays.stream(steps).max().orElse(0);
        int countAverage  = (int)Arrays.stream(steps).average().orElse(0);

        System.out.println("Общее количество шагов за месяц: " + countTotal + "\n" +
                "Максимальное количество шагов за месяц: " + countMax + "\n" +
                "Среднее количество шагов за месяц: " + countAverage + "\n" +
                "Пройденная дистанция (в км): " + Converter.getKilometresFromSteps(countTotal) + "\n" +
                        result + "\n");
    }

    private static int inputNumber(String title, int minValue, int maxValue) {
        int number = -1;

        while (number < minValue || number > maxValue)  {
            System.out.println("Введите " + title + " (" + minValue + " - " + maxValue + "):");
            String inputLine = System.console().readLine();
            try {
                number = Integer.parseInt(inputLine);
            } catch (NumberFormatException e) {
                System.out.println("Ошибка: Введите число! " + e.getMessage());
            }
        }

        return number;
    }
}
