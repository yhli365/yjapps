package starter.jmeter.java;

import java.text.SimpleDateFormat;
import java.util.Date;

import org.apache.jmeter.config.Arguments;
import org.apache.jmeter.protocol.java.sampler.AbstractJavaSamplerClient;
import org.apache.jmeter.protocol.java.sampler.JavaSamplerContext;
import org.apache.jmeter.samplers.SampleResult;

/**
 * @author Yanhong Lee
 * 
 */
public class HelloPerfSClient extends AbstractJavaSamplerClient {

	private String a;
	private String b;
	/** Holds the result data (shown as Response Data in the Tree display). */
	private String resultData;

	private long start;
	private SimpleDateFormat df = new SimpleDateFormat(
			"yyyy-MM-dd HH:mm:ss.SSS");

	// 这个方法是用来自定义java方法入参的。
	// params.addArgument("num1","");表示入参名字叫num1，默认值为空。
	// 设置可用参数及的默认值；
	public Arguments getDefaultParameters() {
		Arguments params = new Arguments();
		params.addArgument("num1", "");
		params.addArgument("num2", "");
		return params;
	}

	// 每个线程测试前执行一次，做一些初始化工作；
	public void setupTest(JavaSamplerContext arg0) {
		start = System.currentTimeMillis();
		System.out.println("setupTest# " + df.format(new Date(start)));
	}

	// 测试结束时调用；
	public void teardownTest(JavaSamplerContext arg0) {
		long end = System.currentTimeMillis();
		System.out.println("teardownTest# " + df.format(new Date(end))
				+ ", cost = " + (end - start) + "ms");
	}

	// 开始测试，从arg0参数可以获得参数值；
	@Override
	public SampleResult runTest(JavaSamplerContext arg0) {
		a = arg0.getParameter("num1");
		b = arg0.getParameter("num2");
		SampleResult sr = new SampleResult();
		sr.setSampleLabel("Java请求哦");
		try {
			sr.sampleStart();// jmeter 开始统计响应时间标记
			Hello test = new Hello();
			// 通过下面的操作就可以将被测方法的响应输出到Jmeter的察看结果树中的响应数据里面了。
			resultData = String.valueOf(test.sum(Integer.parseInt(a),
					Integer.parseInt(b)));
			if (resultData != null && resultData.length() > 0) {
				sr.setResponseData("结果是：" + a + "+" + b + "=" + resultData,
						null);
				sr.setDataType(SampleResult.TEXT);
			}
			System.out.println("结果是：" + a + "+" + b + "=" + resultData);
			sr.setSuccessful(true);
		} catch (Throwable e) {
			sr.setSuccessful(false);
			e.printStackTrace();
		} finally {
			sr.sampleEnd();// jmeter 结束统计响应时间标记
		}
		return sr;
	}
}
