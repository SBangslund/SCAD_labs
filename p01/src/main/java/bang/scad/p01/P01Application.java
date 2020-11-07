package bang.scad.p01;

import java.util.logging.Level;
import java.util.logging.Logger;

import bang.scad.libaws.TestHandler;
import bang.scad.libaws.invokers.AWSInvoker;
import bang.scad.libaws.invokers.interfaces.Invoker;
import bang.scad.p01.handlers.E2Handler;

public class P01Application {

	private static final Logger LOGGER = Logger.getLogger(P01Application.class.getName());

	public static void main(String[] args) {
		Invoker invoker = new AWSInvoker();
		//TestHandler e1Tester = new TestHandler(invoker, "arn:aws:lambda:us-east-1:682613002205:function:p01_e1", null, 10);
		TestHandler e2Tester = new TestHandler(invoker, "arn:aws:lambda:us-east-1:682613002205:function:p01_e2", null, 100);
		//TestHandler e3Tester = new TestHandler(invoker, "arn:aws:lambda:us-east-1:682613002205:function:p01_e3", null, 10);
		//TestHandler e7Tester = new TestHandler(invoker, "arn:aws:lambda:us-east-1:682613002205:function:p01_e7", null, 10);

		//Thread e1Thread = new Thread(e1Tester);
		Thread e2Thread = new Thread(e2Tester);
		//Thread e3Thread = new Thread(e3Tester);
		//Thread e7Thread = new Thread(e7Tester);

		//e1Thread.start();
		e2Thread.start();
		//e3Thread.start();
		//e7Thread.start();

		try {
			//e1Thread.join();
			e2Thread.join();
			//e3Thread.join();
			//e7Thread.join();

			StringBuilder builder = new StringBuilder();
			//builder.append(String.format("\nTested E1: %s", e1Tester.getStatistics()));
			//builder.append(e1Tester.getResults().get(0));
			builder.append(String.format("\nTested E2: %s", e2Tester.getStatistics()));
			//builder.append(String.format("\nTested E3: %s", e3Tester.getStatistics()));
			//builder.append(String.format("\nTested E7: %s", e7Tester.getStatistics()));

			// e2Tester.getResults().stream().forEach(r -> builder.append("\t" + r + "\n"));
			LOGGER.log(Level.INFO, builder.toString());
		} catch (InterruptedException e) {
			LOGGER.log(Level.WARNING, "Thread was interrupted", e);
		}
	}
}
