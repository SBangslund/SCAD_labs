package bang.scad.p01;

import java.util.logging.Level;
import java.util.logging.Logger;

import bang.scad.p01.handlers.AWSInvoker;
import bang.scad.p01.handlers.interfaces.Invoker;

public class P01Application {

	private static final Logger LOGGER = Logger.getLogger(P01Application.class.getName());

	public static void main(String[] args) {
		Invoker invoker = new AWSInvoker();
		TestHandler e2Tester = new TestHandler(
			invoker, 
			"arn:aws:lambda:us-east-1:682613002205:function:p01_e2", 
			100);
		Thread e2Thread = new Thread(e2Tester);
		e2Thread.start();

		try{
			e2Thread.join();
			StringBuilder builder = new StringBuilder();
			builder.append(String.format("Tested E2:\n%s", e2Tester.getStatistics()));
			e2Tester.getResults().stream().forEach(r -> builder.append("\t" + r + "\n"));
			LOGGER.log(Level.INFO, builder.toString());
		} catch(InterruptedException e) {
			LOGGER.log(Level.WARNING, "Thread was interrupted", e);
		}
	}
}
