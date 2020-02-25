package educative.mergeintervals;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.PriorityQueue;
import java.util.stream.Collectors;

import org.apache.commons.lang3.tuple.ImmutablePair;
import org.apache.commons.lang3.tuple.Pair;

public class MergeIntervals {
	/**
	 * Merge different intervals to make a list of mutually exclusive list of intervals.
	 * @param intervals List of intervals
	 * @return list of mutually exclusive intervals.
	 */
	List<Interval> mergeIntervals(List<Interval> intervals) {
		if(intervals.size() < 2) {
			return intervals;
		}
		
		List<Interval> mergedIntervals = new ArrayList<>();
		int start = intervals.get(0).start;
		int end = intervals.get(0).end;
		
		// Sort the intervals in increasing order of start interval
		Collections.sort(intervals, (a,b) -> Integer.compare(a.start, b.start));
		
		for(int i = 1; i < intervals.size(); ++i) {
			Interval interval = intervals.get(i);
			if(interval.start <= end) { // This should be merged
				end = Math.max(end, interval.end);
			}
			else {
				// no overlap from the old interval
				// Add the old interval into merged list
				mergedIntervals.add(new Interval(start, end));
				start = interval.start;
				end = interval.end;
			}
		}
		
		mergedIntervals.add(new Interval(start, end));
		
		return mergedIntervals;
	}
	
	/**
	 * Insert Interval
	 * Given a list of non-overlapping intervals sorted by their start time,
	 * insert a given interval at the correct position and merge all necessary
	 * intervals to produce a list that has only mutually exclusive intervals.
	 * Input: Intervals=[[1,3], [5,7], [8,12]], New Interval=[4,6]
	 * Output: [[1,3], [4,7], [8,12]]
	 * Explanation: After insertion, since [4,6] overlaps with [5,7], we merged them into one [4,7].
	 */
	public List<Interval> insert(List<Interval> intervals, Interval newInterval) {
		// Sort the intervals
		Collections.sort(intervals, (a,b) -> Integer.compare(a.start, b.start));
		
		List<Interval> res = new ArrayList<>();
		
		int idx = 0; // Index for insertion of newInterval
		for(; idx < intervals.size(); ++idx) {
			Interval interval = intervals.get(idx);
			// Keep moving right until (intervals.end < newInterval.start)
			if(interval.end < newInterval.start) {
				res.add(interval);
			}
			else {
				break;
			}
		}
		
		int start = newInterval.start;
		int end = newInterval.end;
		
		for(; idx < intervals.size(); ++idx) {
			Interval intervalTemp = intervals.get(idx);
			if(intervalTemp.start > end) { // No overlap
				res.add(new Interval(start, end));
				start = intervalTemp.start;
				end = intervalTemp.end;
			}
			else { // Overlap
				start = Math.min(start, intervalTemp.start);
				end = Math.max(end, intervalTemp.end);
			}
		}
		
		res.add(new Interval(start, end));
		
		return res;
	}
	
	/**
	 * Intervals Intersection
	 * Given two lists of intervals, find the intersection of these two lists. 
	 * Each list consists of disjoint intervals sorted on their start time.
	 * Input: arr1=[[1, 3], [5, 6], [7, 9]], arr2=[[2, 3], [5, 7]]
	 * Output: [2, 3], [5, 6], [7, 7]
	 * Explanation: The output list contains the common intervals between the two lists.
	 */
	public Interval[] intervalIntersection(Interval[] arr1, Interval[] arr2) {
		List<Interval> res = new ArrayList<>();
		
		// Decide where to start
		if(arr1[0].start > arr2[0].start) {
			Interval[] temp = arr1;
			arr1 = arr2;
			arr2 = temp;
		}
		
		int i = 0;
		int j = 0;
		
		while(i < arr1.length && j < arr2.length) {
			if(arr1[i].end < arr2[j].start) { // No overlap
				i++;
			}
			else {
				int start = Math.max(arr1[i].start, arr2[j].start);
				int end = Math.min(arr1[i].end, arr2[j].end);
				if(end == arr1[i].end) {
					i++;
				}
				if(end == arr2[j].end) {
					j++;
				}
				res.add(new Interval(start, end));
			}
		}
		
		return res.toArray(new Interval[0]);
	}
	
	// Don't need to decide which arr should be left
	public Interval[] intervalIntersection2(Interval[] arr1, Interval[] arr2) {
		List<Interval> res = new ArrayList<>();
		
		int i = 0;
		int j = 0;
		
		while(i < arr1.length && j < arr2.length) {
			// Check if either has an overlapping range
			if( (arr1[i].start <= arr2[j].start && arr1[i].end >= arr2[j].start) || 
					(arr1[i].start >= arr2[j].start && arr1[i].start <= arr2[j].end) ) {
				res.add(new Interval(Math.max(arr1[i].start, arr2[j].start), Math.min(arr1[i].end, arr2[j].end)));
			}
			
			if(arr1[i].end > arr2[j].end) {
				j++;
			}
			else if(arr1[i].end < arr2[j].end) {
				i++;
			}
			else {
				i++;
				j++;
			}
		}
		
		return res.toArray(new Interval[0]);
	}
	
	/**
	 * Conflicting Appointments
	 * Given an array of intervals representing ‘N’ appointments, 
	 * find out if a person can attend all the appointments.
	 * Appointments: [[1,4], [2,5], [7,9]]
	 * Output: false
	 * Explanation: Since [1,4] and [2,5] overlap, a person cannot attend both of these appointments.
	 */
	 public boolean canAttendAllAppointments(Interval[] intervals) {
		 // Sort in ascending order of start
		 Arrays.sort(intervals, (a,b) -> Integer.compare(a.start, b.start));
		 
		 for(int i = 0; i < intervals.length-1; ++i) {
			 if(intervals[i].end > intervals[i+1].start) {
				 return false;
			 }
		 }
		 
		 return true; 
	 }
	 
	 /**
	  *  Given a list of appointments, find all the conflicting appointments.
	  *  Appointments: [[4,5], [2,3], [3,6], [5,7], [7,8]]
	  *  Output: 
	  *  4,5] and [3,6] conflict. 
	  *  3,6] and [5,7] conflict.
	  *  // TODO : This algo takes n-square. A more optimized algo is using a interval tree (nlogn).
	  */
	 List<Pair<Interval, Interval>> conflictingAppointments(List<Interval> intervals) {
		 Collections.sort(intervals, (a,b) -> Integer.compare(a.start, b.start));
		 
		 List<Pair<Interval, Interval>> res = new ArrayList<>();
		 
		 for(int i = 0; i < intervals.size(); ++i) {
			 for(int j = i+1; j < intervals.size(); ++j) {
				 if(intervals.get(i).end > intervals.get(j).start) {
					 res.add(new ImmutablePair<>(intervals.get(i), intervals.get(j)));
				 }
			 }
		 }
		 
		 return res;
	 }
	 
	 /**
	  * Given a list of intervals representing the start and end time of ‘N’ meetings, 
	  * find the minimum number of rooms required to hold all the meetings.
	  * Meetings: [[1,4], [2,5], [7,9]]
	  * Output: 2
	  * Explanation: Since [1,4] and [2,5] overlap, we need two rooms to hold these two meetings. 
	  * [7,9] can occur in any of the two rooms later.
	  */
	 public int findMinimumMeetingRooms(List<Interval> meetings) {
		 // Use min heap on end of the interval to keep track of the overlapping intervals.
		 // The room will be vacant, if the new Interval starts after the end of previous meetings.
		 
		 // Sort the meeting based on the start time
		 Collections.sort(meetings, (a,b) -> Integer.compare(a.start, b.start));
		 
		 int minRooms = 0;
		 
		 // Creating a min heap on end of the interval.
		 PriorityQueue<Interval> meetingQueue = new PriorityQueue<>((a,b) -> Integer.compare(a.end, b.end));
		 
		 for(Interval newMeeting : meetings) {
			 // Remove all the meetings which are done before the current meeting
			 while(!meetingQueue.isEmpty() && meetingQueue.peek().end <= newMeeting.start) {
				 meetingQueue.poll();
			 }
			 
			 meetingQueue.offer(newMeeting);
			 
			 minRooms = Math.max(minRooms, meetingQueue.size());
		 }
		 
		 return minRooms;
	 }
	 
	 /**
	  * Maximum CPU Load
	  * We are given a list of Jobs. Each job has a Start time, an End time, and a CPU load when it is running. 
	  * Our goal is to find the maximum CPU load at any time if all the jobs are running on the same machine.
	  */
	 public int findMaxCPULoad(List<Job> jobs) {
		 // Sort the jobs on start time
		 Collections.sort(jobs, (a,b) -> Integer.compare(a.start, b.start));
		 
		 int maxCPULoad = 0;
		 int currCPULoad = 0;
		 
		 PriorityQueue<Job> jobQueue = new PriorityQueue<>((a,b) -> Integer.compare(a.end, b.end));
		 
		 for(Job job : jobs) {
			 // Removing all the completed jobs
			 while(!jobQueue.isEmpty() && jobQueue.peek().end <= job.start) {
				 currCPULoad -= jobQueue.poll().cpuLoad;
			 }
			 
			 jobQueue.offer(job);
			 
			 // Add the cpu load of the job to the current CPULoad
			 currCPULoad += job.cpuLoad;
			 
			 maxCPULoad = Math.max(maxCPULoad, currCPULoad);
		 }
		 
		 return maxCPULoad;
	 }
	 
	 /**
	  * Employee Free Time
	  * For ‘K’ employees, we are given a list of intervals representing the working hours of each employee. 
	  * Our goal is to find out if there is a free interval that is common to all employees.
	  * You can assume that each list of employee working hours is sorted on the start time.
	  * Input: Employee Working Hours=[[[1,3], [9,12]], [[2,4]], [[6,8]]]
	  * Output: [4,6], [8,9]
	  * Explanation: All employees are free between [4,6] and [8,9].
	  */
	 public List<Interval> findEmployeeFreeTime(List<List<Interval>> schedule) {
		 //Merge all the lists in one list
		 List<Interval> allSchedules = schedule.stream().flatMap(List::stream).collect(Collectors.toList());
		 // Sort all the schedules
		 Collections.sort(allSchedules, (a,b) -> Integer.compare(a.start, b.start));
		 
		 //Merge all the schedules that is overlapping
		 int start = allSchedules.get(0).start;
		 int end = allSchedules.get(0).end;
		 
		 List<Interval> overlappingSchedules = new ArrayList<>();
		 
		 for(int i = 1; i < allSchedules.size(); ++i) {
			 Interval interval = allSchedules.get(i);
			 if(end > interval.start) { // Overlapping
				 end = Math.max(end, interval.end);
			 }
			 else {
				 overlappingSchedules.add(new Interval(start, end));
				 start = interval.start;
				 end = interval.end;
			 }
		 }
		 // Adding the last interval
		 overlappingSchedules.add(new Interval(start, end));
		 
		 if(overlappingSchedules.isEmpty()) {
			 return Collections.emptyList();
		 }
		 
		 List<Interval> freeSchedules = new ArrayList<>();
		 
		 start = overlappingSchedules.get(0).start;
		 end = overlappingSchedules.get(0).end;

		 for(int i = 1; i < overlappingSchedules.size(); ++i) {
			 Interval interval = overlappingSchedules.get(i);
			 if(end == interval.start) {
				 continue; // Don't need values like 6,6
			 }
			 
			 freeSchedules.add(new Interval(end, interval.start));
			 start = interval.start;
			 end = interval.end;
		 }
		 
		 return freeSchedules;
	 }
	 
	 public List<Interval> findEmployeeFreeTimeOpt(List<List<Interval>> schedule) {
		 List<Interval> result = new ArrayList<>();
		 
		 PriorityQueue<EmployeeInterval> heap = new PriorityQueue<>((a,b) -> Integer.compare(a.interval.start, b.interval.start));
		 // Push the 1st interval of each of the employee to the heap
		 for(int i = 0; i < schedule.size(); ++i) {
			 heap.offer(new EmployeeInterval(schedule.get(i).get(0), i, 0));
		 }
		 
		 // Remove one element from heap at a time and check if it has no overlap with the next element.
		 // Since heap is min Sorted, if we don't have overlap with the next element, we won't have overlap with
		 // next to next element and so on. So all the employees will have that window as the free window.
		 
		 // Whatever we pop from the heap, the same employees next interval has to be pushed to the heap,
		 // since we want to find the free windows of these employees.
		 
		 Interval prevInterval = heap.peek().interval; // Can't remove as it will be hard to push back the same employee's interval

		 while(!heap.isEmpty()) {
			 EmployeeInterval heapFront = heap.poll();
			 
			 // Check if there is overlap with the previous or not
			 if(heapFront.interval.start > prevInterval.end) { // No overlap
				 result.add(new Interval(prevInterval.end, heapFront.interval.start));
				 prevInterval = heapFront.interval;
			 }
			 else { // If overlapping, we need to keep the one which has larger end to compare to the upcoming intervals
				 if(prevInterval.end < heapFront.interval.end) {
					 prevInterval = heapFront.interval;
				 }
			 }
			 
			 // Add an interval from the same employee which was removed
			 int employeeIndex = heapFront.employeeIndex;
			 int intervalIndex = heapFront.intervalIndex;
			 // last index >= intervalIndex+1
			 if(schedule.get(employeeIndex).size()-1 >= intervalIndex+1) {
				 heap.offer(new EmployeeInterval(schedule.get(employeeIndex).get(intervalIndex+1), employeeIndex, intervalIndex+1));
			 }
		 }
		 
		 return result;
	 }
}
