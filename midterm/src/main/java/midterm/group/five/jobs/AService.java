package midterm.group.five.jobs;

import java.util.Date;

import org.springframework.stereotype.Service;

@Service
public class AService {
	
	public void display() {
		System.out.println("Hi this message is scheduled to display at"+new Date());
	}
}


