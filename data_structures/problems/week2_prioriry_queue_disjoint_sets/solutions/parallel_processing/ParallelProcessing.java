package algorithms1;

import java.util.Comparator;
import java.util.PriorityQueue;
import java.util.Queue;

public class ParallelProcessing {
	PriorityQueue<Thread> availableThreads;
	PriorityQueue<Job> pendingJobs;
	PriorityQueue<Thread> assignedThreads;
	PriorityQueue<Job> jobsCompleted;

	public ParallelProcessing(int threads, int[] jobs) {
		availableThreads = new PriorityQueue<>(comparatorThread);
		pendingJobs = new PriorityQueue<>(comparatorJob);
		assignedThreads = new PriorityQueue<>(comparatorJobExec);
		jobsCompleted = new PriorityQueue<>(comparatorJob);

		for (int i = 0; i < threads; i++) {
			availableThreads.add(new Thread(i, 0));
		}

		for (int i = 0; i < jobs.length; i++) {
			pendingJobs.add(new Job(i, jobs[i]));
		}
	}

	public void exec() {

		while (pendingJobs.size() > 0) {
			
			/* assign the jobs to the threads */
			while (availableThreads.size() > 0 && pendingJobs.size() > 0) { // n
				Thread availableThread = availableThreads.poll(); // O(log(n))
				Job pendingJob = pendingJobs.poll(); // O(log(m))
				availableThread.assignJob(pendingJob); // O(1)
				assignedThreads.add(availableThread); // O(log(n))
			}

			/* process the jobs */
			while (assignedThreads.size() > 0) {
				Thread thread = assignedThreads.poll();
				Job job = thread.getJob();
				job.thread = thread;
				jobsCompleted.add(job);
				thread.time += job.processTime;
				availableThreads.add(thread);
			}
		}

		printjobs();

	}

	public void printjobs() {
		System.out.println("done");
		return;
		/*
		while (jobsCompleted.size() > 0) {
			Job job = jobsCompleted.poll();
			Thread thread = job.thread;
			System.out.println(thread.index + " , " + job.startingTime);
		}
		*/
	}

	public void pQtest() {
		availableThreads = new PriorityQueue<>(comparatorThread);
		Thread t1 = new Thread(0, 10);
		Thread t2 = new Thread(2, 10);
		Thread t3 = new Thread(1, 10);
		availableThreads.add(t1);
		availableThreads.add(t2);
		availableThreads.add(t3);
		while (availableThreads.size() > 0) {
			Thread a = availableThreads.poll();
			System.out.println(a.toString());
		}
	}

	public static void test(boolean forever) {

		do {

			int threads = 1000000;// 100000;//ThreadLocalRandom.current().nextInt(1,50000);
			int m = 1000000;// 100000;//ThreadLocalRandom.current().nextInt(1,50000);

			int[] jobs = new int[m];
			
			for (int i = 1; i < m; i++) {
				jobs[i] = 1;
			}

			System.out.println("calculating efficient...");
			ParallelProcessing pp = new ParallelProcessing(threads,jobs);
			pp.exec();
			

		} while (forever);

	}

	private class Thread {
		public int index;
		public int time;
		public Job assignedJob;

		public Thread(int index, int time) {
			this.index = index;
			this.time = time;
		}

		public Job getJob() {
			return this.assignedJob;
		}

		public void assignJob(Job job) {
			this.assignedJob = job;
			this.assignedJob.startingTime = this.time;
			// this.time += job.processTime;
		}

		@Override
		public String toString() {
			// TODO Auto-generated method stub
			return String.valueOf(this.index);
		}

	}

	private class Job {
		public int index;
		public int processTime;
		public int startingTime;
		Thread thread;

		public Job(int index, int time) {
			this.index = index;
			this.processTime = time;
		}
	}

	public static Comparator<Thread> comparatorThread = new Comparator<Thread>() {

		@Override
		public int compare(Thread t1, Thread t2) {
			if (t1.time == t2.time) {
				return t1.index < t2.index ? -1 : 1;
			} else {
				return t1.time < t2.time ? -1 : 1;
			}
		}
	};

	public static Comparator<Job> comparatorJob = new Comparator<Job>() {

		@Override
		public int compare(Job j1, Job j2) {
			return j1.index < j2.index ? -1 : 1;
		}
	};

	public static Comparator<Thread> comparatorJobExec = new Comparator<Thread>() {

		@Override
		public int compare(Thread t1, Thread t2) {
			return t1.getJob().processTime < t2.getJob().processTime ? -1 : 1;
		}
	};

}
