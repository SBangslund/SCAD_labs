package bang.scad.p01;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.logging.Level;
import java.util.logging.Logger;

import bang.scad.libaws.TestHandler;
import bang.scad.libaws.invokers.AWSInvoker;
import bang.scad.libaws.invokers.interfaces.Invoker;
import bang.scad.libaws.readers.ReadJsonFile;
import bang.scad.libaws.readers.interfaces.StringReader;

public class P01Application {

	private static final Logger LOGGER = Logger.getLogger(P01Application.class.getName());

	public static void main(String[] args) {
		Invoker invoker = new AWSInvoker();
		StringReader reader = new ReadJsonFile();

		String e1Payload = reader.read("input/test1.json");

		TestHandler e1Tester = new TestHandler(invoker, "arn:aws:lambda:us-east-1:682613002205:function:p01_e1", e1Payload.toString(), 100);
		TestHandler e2Tester = new TestHandler(invoker, "arn:aws:lambda:us-east-1:682613002205:function:p01_e2", null, 1);
		//TestHandler e7Tester = new TestHandler(invoker, "arn:aws:lambda:us-east-1:682613002205:function:p01_e7", null, 10);
		TestHandler e8Tester = new TestHandler(invoker, "arn:aws:lambda:us-east-1:682613002205:function:p01_e8", null, 1);

		Thread e1Thread = new Thread(e1Tester);
		Thread e2Thread = new Thread(e2Tester);
		//Thread e7Thread = new Thread(e7Tester);
		Thread e8Thread = new Thread(e8Tester);

		e1Thread.start();
		e2Thread.start();
		//e7Thread.start();
		e8Thread.start();

		try {
			e1Thread.join();
			e2Thread.join();
			//e7Thread.join();
			e8Thread.join();

			StringBuilder builder = new StringBuilder();
			builder.append(String.format("\nTested E1: %s", e1Tester.getStatistics()));
			builder.append(String.format("\nTested E2: %s", e2Tester.getStatistics()));
			//builder.append(String.format("\nTested E7: %s", e7Tester.getStatistics()));
			builder.append(String.format("\nTested E8: %s", e8Tester.getStatistics()));
			
			LOGGER.log(Level.INFO, builder.toString());
		} catch (InterruptedException e) {
			LOGGER.log(Level.WARNING, "Thread was interrupted", e);
		}
	}
}
