package starter.jmeter.java;

import org.apache.jmeter.config.Arguments;
import org.apache.jmeter.protocol.java.sampler.JavaSamplerContext;
import org.junit.Test;

public class HelloPerfSClientTest {

	@Test
	public void testSampler() {
		Arguments params = new Arguments();
		params.addArgument("num1", "1");// 设置参数，并赋予默认值1
		params.addArgument("num2", "2");// 设置参数，并赋予默认值2
		JavaSamplerContext arg0 = new JavaSamplerContext(params);
		HelloPerfSClient test = new HelloPerfSClient();
		test.setupTest(arg0);
		test.runTest(arg0);
		test.teardownTest(arg0);
	}

}
