package educative.mergeintervals;

import java.util.List;
import java.util.PriorityQueue;

import org.apache.commons.lang3.tuple.Pair;

import com.google.common.collect.Lists;

public class MergeIntervalsMain {
	
	public static void main(String[] args) {
		MergeIntervals mergeIntervals = new MergeIntervals();
		List<Interval> res = mergeIntervals.mergeIntervals(
				Lists.newArrayList(
						new Interval(1, 4), new Interval(2, 5), new Interval(7, 9)
						)
				);
//		System.out.println(res);
		
		res = mergeIntervals.insert(
				Lists.newArrayList(
						new Interval(1, 3), new Interval(5, 7), new Interval(8, 12)
						), new Interval(4,10)
				);
//		System.out.println(res);

		res = mergeIntervals.insert(
				Lists.newArrayList(
						new Interval(1, 3), new Interval(5, 7), new Interval(8, 12)
						), new Interval(4,6)
				);
//		System.out.println(res);

		res = mergeIntervals.insert(
				Lists.newArrayList(
						new Interval(2, 3), new Interval(5, 7)
						), new Interval(1,4)
				);
//		System.out.println(res);
		
		Interval[] intersectRes = mergeIntervals.intervalIntersection2(
				new Interval[] {new Interval(1, 3), new Interval(5, 6), new Interval(7, 9)},
				new Interval[] {new Interval(2, 3), new Interval(5, 7)}
				);
		
//		printIntervalArr(intersectRes);
		
		intersectRes = mergeIntervals.intervalIntersection2(
				new Interval[] {new Interval(1, 3), new Interval(5, 7), new Interval(9, 12)},
				new Interval[] {new Interval(5, 10)}
				);
		
//		printIntervalArr(intersectRes);
		
		System.out.println(mergeIntervals.canAttendAllAppointments(
				new Interval[] {new Interval(1, 4), new Interval(2, 5), new Interval(7, 9)}
				));
		
		System.out.println(mergeIntervals.canAttendAllAppointments(
				new Interval[] {new Interval(6, 7), new Interval(2, 4), new Interval(8, 12)}
				));
		
		System.out.println(mergeIntervals.canAttendAllAppointments(
				new Interval[] {new Interval(4, 5), new Interval(2, 3), new Interval(3, 6)}
				));
		
		List<Pair<Interval, Interval>> conflicts = mergeIntervals.conflictingAppointments(Lists.newArrayList(
				new Interval(1, 5), new Interval(2, 4), new Interval(3, 4), new Interval(4,5))
				);
		
		System.out.println(conflicts);
		
		List<Interval> meetings = Lists.newArrayList(new Interval(1,4), new Interval(2,5), new Interval(7,9));
		System.out.println(mergeIntervals.findMinimumMeetingRooms(meetings));

		meetings = Lists.newArrayList(new Interval(6,7), new Interval(2,4), new Interval(8,12));
		System.out.println(mergeIntervals.findMinimumMeetingRooms(meetings));
		
		meetings = Lists.newArrayList(new Interval(1,4), new Interval(2,3), new Interval(3,6));
		System.out.println(mergeIntervals.findMinimumMeetingRooms(meetings));
		
		List<Job> jobs = Lists.newArrayList(new Job(1,4,3), new Job(2,5,4), new Job(7,9,6));
		System.out.println("Max CPU Load : " + mergeIntervals.findMaxCPULoad(jobs));

		jobs = Lists.newArrayList(new Job(6,7,10), new Job(2,4,11), new Job(8,12,15));
		System.out.println("Max CPU Load : " + mergeIntervals.findMaxCPULoad(jobs));

		jobs = Lists.newArrayList(new Job(1,4,2), new Job(2,4,1), new Job(3,6,5));
		System.out.println("Max CPU Load : " + mergeIntervals.findMaxCPULoad(jobs));
		
		List<Interval> freeSchedule = mergeIntervals.findEmployeeFreeTimeOpt(Lists.newArrayList
				(
						Lists.newArrayList(new Interval(1,3), new Interval(5,6)), 
						Lists.newArrayList(new Interval(2,3), new Interval(6,8)))
				);
		System.out.println("Free Schedules : " + freeSchedule);

		freeSchedule = mergeIntervals.findEmployeeFreeTimeOpt(Lists.newArrayList
				(
						Lists.newArrayList(new Interval(1,3), new Interval(9,12)), 
						Lists.newArrayList(new Interval(2,4), new Interval(6,8)))
				);
		System.out.println("Free Schedules : " + freeSchedule);

		freeSchedule = mergeIntervals.findEmployeeFreeTimeOpt(Lists.newArrayList
				(
						Lists.newArrayList(new Interval(1,3)), 
						Lists.newArrayList(new Interval(2,4)), 
						Lists.newArrayList(new Interval(3,5), new Interval(7,9)))
				);
		System.out.println("Free Schedules : " + freeSchedule);
	}

	private static void printIntervalArr(Interval[] intersectRes) {
		for(Interval i : intersectRes) {
			System.out.println(i.toString());
		}
	}

}
