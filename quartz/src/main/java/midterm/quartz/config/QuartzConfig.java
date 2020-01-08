package midterm.quartz.config;

import org.quartz.CronScheduleBuilder;
import org.quartz.DisallowConcurrentExecution;
import org.quartz.JobBuilder;
import org.quartz.JobDetail;
import org.quartz.SimpleScheduleBuilder;
import org.quartz.Trigger;
import org.quartz.TriggerBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import midterm.quartz.jobs.firstJob;
import midterm.quartz.jobs.secondJob;

@Configuration
@DisallowConcurrentExecution
public class QuartzConfig {
	
	@Bean
	public JobDetail jobDetailFirst() {
		return JobBuilder.newJob(firstJob.class).withIdentity("FirstJob","Group").storeDurably().build();
	}
	
	@Bean
	public Trigger jobTriggerFirst(JobDetail jobDetailFirst) {
		return  TriggerBuilder.newTrigger()
				.forJob(jobDetailFirst).withIdentity("FirstTrigger","Group1")
				//.withSchedule(CronScheduleBuilder.cronSchedule("0/10 * * ? * * *"))
				.withSchedule(SimpleScheduleBuilder.repeatSecondlyForever(10).withIntervalInSeconds(15))
				.build();
	}
	
	@Bean
	public JobDetail jobDetailSecond() {
		return JobBuilder.newJob(secondJob.class).withIdentity("SecondJob","Group").storeDurably().build();
	}
	@Bean
	public Trigger jobTriggerSecond(JobDetail jobDetailSecond) {
		return TriggerBuilder.newTrigger()
				.forJob(jobDetailSecond)
				.withIdentity("SecondTrigger","Group2")
			//	.withSchedule(CronScheduleBuilder.cronSchedule("0/8 * * ? * * *"))
				.withSchedule(SimpleScheduleBuilder.repeatSecondlyForever(15).withIntervalInSeconds(15))
				.build();
	}

	
}
