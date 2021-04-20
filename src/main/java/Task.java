import com.fasterxml.jackson.annotation.JsonFormat;

import java.time.LocalDate;

public class Task {

	private Long id;

	private String task;

	@JsonFormat(pattern = "yyyy-MM-dd")
	private String dueDate;
	
	public Task() {}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getTask() {
		return task;
	}

	public void setTask(String task) {
		this.task = task;
	}
	
	public String getDueDate() {
		return dueDate;
	}

	public void setDueDate(String dueDate) {
		this.dueDate = dueDate;
	}
}
