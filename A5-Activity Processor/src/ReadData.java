
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Stream;

public class ReadData {
	private static List<MonitoredData> list;

	public ReadData() {
		String fileName = "Activities.txt";
		DateTimeFormatter format = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");
		list = new ArrayList<MonitoredData>();
		try (Stream<String> stream = Files.lines(Paths.get(fileName))) {
			stream.forEach(line -> {
				String[] s = line.split("		");
				MonitoredData info = new MonitoredData(LocalDateTime.parse(s[0], format),
						LocalDateTime.parse(s[1], format), s[2]);
				list.add(info);
			});
		} catch (IOException e) {
			e.getMessage();
		}
	}
	public List<MonitoredData> getList() {
		return list;
	}
	public static void setList(List<MonitoredData> list) {
		ReadData.list = list;
	}
}
