package midterm.group.five.instance;


import org.quartz.JobBuilder;
import org.quartz.JobDetail;
import org.quartz.SimpleScheduleBuilder;
import org.quartz.Trigger;
import org.quartz.TriggerBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import midterm.group.five.jobs.AJob;

@Configuration
public class QuartzInstance {	
	@Bean
	public JobDetail jobDetails() {
		return JobBuilder.newJob(AJob.class)
				.withIdentity("A_test_job").storeDurably()
				.build();
	}
	@Bean
	public Trigger jobTrigger(JobDetail jobDetails) {
		return (Trigger)TriggerBuilder.newTrigger().forJob(jobDetails).withSchedule(SimpleScheduleBuilder.repeatSecondlyForever()).build();
	}
}
