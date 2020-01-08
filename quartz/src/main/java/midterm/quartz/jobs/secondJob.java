package midterm.quartz.jobs;

import org.quartz.DisallowConcurrentExecution;
import org.quartz.Job;
import org.quartz.JobExecutionContext;
import org.quartz.JobExecutionException;
import org.springframework.beans.factory.annotation.Autowired;

@DisallowConcurrentExecution
public class secondJob implements Job {
	
	@Autowired
	private GettingFromDB gt2;
	
	@Override
   public void execute(JobExecutionContext context) throws JobExecutionException {
		// TODO Auto-generated method stub
	gt2.GettingFromDBExecute2();
	System.out.println("Hi I have got data with ID=2");
		
	}

//	@Override
//	protected void executeInternal(JobExecutionContext context) throws JobExecutionException {
//		// TODO Auto-generated method stub
//		gt2.GettingFromDBExecute2();
//		System.out.println("Hi I have got data with ID=2");
//	}


}
