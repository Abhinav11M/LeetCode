package educative.mergeintervals;

import lombok.AllArgsConstructor;
import lombok.ToString;

@AllArgsConstructor
@ToString
public class EmployeeInterval {
	public Interval interval;
	public int employeeIndex; // index of the list containing working hours of this employee
	public int intervalIndex; //index of the interval in the employee list
}
