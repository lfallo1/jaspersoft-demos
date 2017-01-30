
public class Department {

	private Integer department_id;
	private String department_description;

	public Department(Integer department_id, String department_description) {
		this.department_id = department_id;
		this.department_description = department_description;
	}

	public Integer getDepartment_id() {
		return department_id;
	}

	public void setDepartment_id(Integer department_id) {
		this.department_id = department_id;
	}

	public String getDepartment_description() {
		return department_description;
	}

	public void setDepartment_description(String department_description) {
		this.department_description = department_description;
	}

}
