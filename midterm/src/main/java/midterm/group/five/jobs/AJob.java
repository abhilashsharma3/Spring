package midterm.group.five.jobs;

import org.quartz.Job;
import org.quartz.JobExecutionContext;
import org.quartz.JobExecutionException;
import org.springframework.beans.factory.annotation.Autowired;

public class AJob implements Job {
	@Autowired
	private AService aservice;

	@Override
	public void execute(JobExecutionContext context) throws JobExecutionException {
		// TODO Auto-generated method stub
		//aservice.display();
		System.out.print("reached");
	}

}
