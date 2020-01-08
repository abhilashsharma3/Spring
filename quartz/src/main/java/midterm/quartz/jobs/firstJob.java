package midterm.quartz.jobs;

import org.quartz.Job;
import org.quartz.JobExecutionContext;
import org.quartz.JobExecutionException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.quartz.QuartzJobBean;

public class firstJob  implements Job{

	@Autowired
	private GettingFromDB gt;
	
	@Override
	public void execute(JobExecutionContext context) throws JobExecutionException {
		gt.GettingFromDBExecute1();
		System.out.println("Hi I have got data with ID=1");
	}

//	@Override
//	protected void executeInternal(JobExecutionContext context) throws JobExecutionException {
//		// TODO Auto-generated method stub
//		gt.GettingFromDBExecute1();
//		System.out.println("Hi I have got data with ID=1");
//	}

}
