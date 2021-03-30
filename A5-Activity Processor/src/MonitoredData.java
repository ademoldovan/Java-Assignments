import java.time.LocalDateTime;
import java.time.temporal.ChronoUnit;

public class MonitoredData {

	private LocalDateTime startTime;
	private LocalDateTime endTime;
	private String activity;

	public MonitoredData(LocalDateTime startTime, LocalDateTime endTime, String activity) {
		this.startTime = startTime;
		this.endTime = endTime;
		this.activity = activity;
	}

	public long getDuration() {
		return ChronoUnit.MINUTES.between(startTime, endTime);
	}
	
	public LocalDateTime getStartTime() {
		return startTime;
	}

	public void setStartTime(LocalDateTime startTime) {
		this.startTime = startTime;
	}

	public LocalDateTime getEndTime() {
		return endTime;
	}

	public void setEndTime(LocalDateTime endTime) {
		this.endTime = endTime;
	}

	public String getActivity() {
		return activity;
	}

	public void setActivity(String activity) {
		this.activity = activity;
	}
}
