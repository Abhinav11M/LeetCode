package programcreek.algolist;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Scanner;

class Subject {
	Subject(String sub, String sTime, String eTime) {
		this.subject = sub;
		this.startTime = getTimeInInt(sTime);
		this.endTime = getTimeInInt(eTime);
	}

	private int getTimeInInt(String eTime) {
		String[] splits = eTime.split(":");
		return Integer.parseInt(splits[0]) * 100 + Integer.parseInt(splits[1]);
	}

	String subject;
	public int startTime;
	public int endTime;
}

public class ScheduleClass {
	
	public static void main(String[] args) {
		Scanner scanner = new Scanner(System.in);
		int nTests = scanner.nextInt();
		scanner.nextLine();
		for(int i = 0; i < nTests; ++i) {
			int nSubjects = scanner.nextInt();
			scanner.nextLine();
			List<Subject> subjects = new ArrayList<>();
			for(int j = 0; j < nSubjects; ++j) {
				String input = scanner.nextLine();
				String[] arr = input.split(" ");
				subjects.add(new Subject(arr[0], arr[1], arr[2]));
			}
			
			System.out.println(findMaxSubjects(subjects));
		}
		
		scanner.close();
	}

	private static int findMaxSubjects(List<Subject> subjects) {
		Collections.sort(subjects, (a,b) -> Integer.compare(a.startTime, b.startTime));
		
		int count = 1;
		int end = subjects.get(0).endTime;
		
		for(int i = 1; i < subjects.size(); ++i) {
			Subject sub = subjects.get(i);
			if(sub.startTime >= end) {
				++count;
				end = sub.endTime;
			}
			else {
				end = Math.min(end, sub.endTime);
			}
		}
		
		return count;
	}
}
