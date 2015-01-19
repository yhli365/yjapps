package starter.jmeter.java;

import org.apache.jmeter.config.Arguments;
import org.apache.jmeter.protocol.java.sampler.JavaSamplerContext;
import org.junit.Test;

public class OutputServicePerfSClientTest {

	@Test
	public void testSampler() {
		Arguments params = new Arguments();
		params.addArgument("a", "0");// 设置参数，并赋予默认值0
		params.addArgument("b", "0");// 设置参数，并赋予默认值0
		params.addArgument("filename", "target/abc.txt");
		JavaSamplerContext arg0 = new JavaSamplerContext(params);
		OutputServicePerfSClient test = new OutputServicePerfSClient();
		test.setupTest(arg0);
		test.runTest(arg0);
		test.teardownTest(arg0);
	}
}
