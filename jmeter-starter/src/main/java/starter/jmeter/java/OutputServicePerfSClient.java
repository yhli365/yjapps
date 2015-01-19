package starter.jmeter.java;

import org.apache.jmeter.config.Arguments;
import org.apache.jmeter.protocol.java.sampler.AbstractJavaSamplerClient;
import org.apache.jmeter.protocol.java.sampler.JavaSamplerContext;
import org.apache.jmeter.samplers.SampleResult;

public class OutputServicePerfSClient extends AbstractJavaSamplerClient {
	private SampleResult result;
	private String a;
	private String b;
	private String filename;

	// 设置传入的参数，可以设置多个，已设置的参数会显示到Jmeter的参数列表中
	public Arguments getDefaultParameters() {
		Arguments params = new Arguments();
		params.addArgument("filename", "0");// 设置参数，并赋予默认值0
		params.addArgument("a", "0");// 设置参数，并赋予默认值0
		params.addArgument("b", "0");// 设置参数，并赋予默认值0
		return params;
	}

	// 初始化方法，实际运行时每个线程仅执行一次，在测试方法运行前执行
	public void setupTest(JavaSamplerContext arg0) {
		result = new SampleResult();
	}

	// 结束方法，实际运行时每个线程仅执行一次，在测试方法运行结束后执行
	public void teardownTest(JavaSamplerContext arg0) {
	}

	// 测试执行的循环体，根据线程数和循环次数的不同可执行多次
	@Override
	public SampleResult runTest(JavaSamplerContext arg0) {
		b = arg0.getParameter("b"); // 获取在Jmeter中设置的参数值
		a = arg0.getParameter("a"); // 获取在Jmeter中设置的参数值
		filename = arg0.getParameter("filename"); // 获取在Jmeter中设置的参数值
		result.sampleStart();// jmeter 开始统计响应时间标记
		try {
			OutputService test = new OutputService();
			test.output(filename, Integer.parseInt(a), Integer.parseInt(b));
			result.setSuccessful(true);
			// 被测对象调用
		} catch (Throwable e) {
			result.setSuccessful(false);
			e.printStackTrace();
		} finally {
			result.sampleEnd();// jmeter 结束统计响应时间标记
		}
		return result;
	}

}
