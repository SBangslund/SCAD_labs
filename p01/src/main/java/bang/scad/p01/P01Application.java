package bang.scad.p01;

import java.util.logging.Level;
import java.util.logging.Logger;

import bang.scad.p01.handlers.AWSInvoker;

public class P01Application {

	private static final Logger LOGGER = Logger.getLogger(P01Application.class.getName());

	public static void main(String[] args) {
		TestHandler e2Tester = new TestHandler(
			new AWSInvoker("ASIAZ53XHZ7O75G5V76G", "B+rTGvPxJrdPoQvzsrhC5/MaMxuMzLWeokEsAJMX"), 
			"arn:aws:lambda:us-east-1:682613002205:function:p01_e2", 
			10);
		Thread e2Thread = new Thread(e2Tester);
		e2Thread.start();

		try{
			e2Thread.join();
			LOGGER.log(Level.INFO, String.format("Tested E2:\n%s, \n%s", e2Tester.getStatistics(), e2Tester.getResults()));
		} catch(InterruptedException e) {
			LOGGER.log(Level.WARNING, "Thread was interrupted", e);
		}
	}
}
