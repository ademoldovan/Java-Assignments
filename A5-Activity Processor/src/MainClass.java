
import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.util.HashMap;
import java.util.List;
import java.util.LongSummaryStatistics;
import java.util.Map;
import java.util.Map.Entry;
import java.util.stream.Collectors;

public class MainClass {

	public static void main(String[] args) {
		ReadData rd = new ReadData();
		task_2(rd.getList());
		task_3(rd.getList());
		task_4(rd.getList());
		task_5(rd.getList());
		task_6(rd.getList());
	}

	// Filter the activities that have more than 90% of the monitoring records with
	// duration less than 5 minutes
	public static void task_6(List<MonitoredData> list) {
		Map<String, Long> activ1 = list.stream()
				.collect(Collectors.groupingBy(MonitoredData::getActivity, Collectors.counting()));
		Map<String, Long> activ2 = list.stream().filter(a -> a.getDuration() < 5)
				.collect(Collectors.groupingBy(MonitoredData::getActivity, Collectors.counting()));
		try {
			FileWriter writer = new FileWriter("Task_6.txt", true);
			BufferedWriter bufferedWriter = new BufferedWriter(writer);
			for (String i : activ1.keySet()) {
				for (String j : activ2.keySet()) {
					if (i.compareTo(j) == 0) {
						if (activ1.get(i) * 0.9 <= activ2.get(i)) {
							bufferedWriter.write(i + "\n");
						}
					}
				}
			}
			bufferedWriter.close();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	// For each activity compute the entire duration over the monitoring period.
	public static void task_5(List<MonitoredData> list) {
		Map<String, LongSummaryStatistics> durations = list.stream().collect(Collectors
				.groupingBy(MonitoredData::getActivity, Collectors.summarizingLong(MonitoredData::getDuration)));
		try {
			FileWriter writer = new FileWriter("Task_5.txt", true);
			BufferedWriter bufferedWriter = new BufferedWriter(writer);
			for (String i : durations.keySet()) {
				bufferedWriter.write("Activity: " + i + "  duration: " + durations.get(i).getSum() + " \n");
			}
			bufferedWriter.close();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	// Count for how many times each activity has appeared for each day over the
	// monitoring period.
	public static void task_4(List<MonitoredData> data) {
		Map<Object, Map<Object, Long>> date = data.stream()
				.collect(Collectors.groupingBy(a -> a.getStartTime().toLocalDate().toString(),
						Collectors.groupingBy(MonitoredData::getActivity, Collectors.counting())));
		try {
			FileWriter writer = new FileWriter("Task_4.txt", true);
			BufferedWriter bufferedWriter = new BufferedWriter(writer);
			for (Entry<Object, Map<Object, Long>> entry : date.entrySet()) {
				bufferedWriter.write(entry.getKey() + "\n");
				for (Entry<Object, Long> entr : entry.getValue().entrySet()) {
					bufferedWriter.write("  ->" + entr.getKey() + " : " + entr.getValue() + "\n");
				}
				bufferedWriter.write("\n");
			}
			bufferedWriter.close();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	// count how many times each activity has apperead over entire monitoring period
	public static void task_3(List<MonitoredData> list) {
		Map<String, Long> date = new HashMap<String, Long>();
		date = list.stream().collect(Collectors.groupingBy(MonitoredData::getActivity, Collectors.counting()));
		try {
			FileWriter writer = new FileWriter("Task_3.txt", true);
			BufferedWriter bufferedWriter = new BufferedWriter(writer);
			for (String i : date.keySet()) {
				bufferedWriter.write("Activitatea " + i + " a fost desfasurata de " + date.get(i) + " ori.");
				bufferedWriter.write("\n");
			}
			bufferedWriter.close();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	// count the distinct days that appear in the monitoring data
	public static void task_2(List<MonitoredData> list) {
		try {
			FileWriter writer = new FileWriter("Task_2.txt", true);
			BufferedWriter bufferedWriter = new BufferedWriter(writer);
			bufferedWriter.write("Numarul de zile distincte este: ");
			bufferedWriter
					.write((int) list.stream().map(a -> a.getStartTime().getDayOfYear()).distinct().count() + ".");
			bufferedWriter.write("\n");
			bufferedWriter.close();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
}
