import java.util.HashMap;

public class StepTracker {
    HashMap<Integer, MonthData> monthData = new HashMap<>();
    Integer targetStepsCount = 10000;

    public StepTracker() {
        for (int i = 0; i< 12; i++) {
            monthData.put(i, new MonthData());
        }
    }

    public void saveStepsPerDay (int month, int day, int count) {
        MonthData newMonthData = monthData.get(month);
        newMonthData.steps[day] = count;
        monthData.put(month, newMonthData);
    }

    class MonthData {
        int[] steps = new int[30];
    }
}
