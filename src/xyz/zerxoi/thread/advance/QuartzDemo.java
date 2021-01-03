package xyz.zerxoi.thread.advance;

import static org.quartz.DateBuilder.evenSecondDateAfterNow;
import static org.quartz.JobBuilder.newJob;
import static org.quartz.TriggerBuilder.newTrigger;

import org.quartz.JobDetail;
import org.quartz.Scheduler;
import org.quartz.SchedulerFactory;
import org.quartz.Trigger;
import org.quartz.impl.StdSchedulerFactory;

import java.util.Date;

public class QuartzDemo {

  public void run() throws Exception {
    SchedulerFactory sf = new StdSchedulerFactory();
    Scheduler sched = sf.getScheduler();

    Date runTime = evenSecondDateAfterNow();

    // define the job and tie it to our HelloJob class
    JobDetail job = newJob(HelloJob.class).withIdentity("job1", "group1").build();

    // Trigger the job to run on the next round minute
    Trigger trigger = newTrigger().withIdentity("trigger1", "group1").startAt(runTime).build();

    // Tell quartz to schedule the job using our trigger
    sched.scheduleJob(job, trigger);

    // Start up the scheduler (nothing can actually run until the
    // scheduler has been started)
    sched.start();

    // wait long enough so that the scheduler as an opportunity to
    // run the job!
    try {
      // wait 5 seconds to show job
      Thread.sleep(5L * 1000L);
      // executing...
    } catch (Exception e) {
      //
    }

    // shut down the scheduler
    sched.shutdown(true);
  }

  public static void main(String[] args) throws Exception {

    QuartzDemo example = new QuartzDemo();
    example.run();

  }

}
