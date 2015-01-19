package starter.jmeter.java;

import java.io.BufferedWriter;
import java.io.FileOutputStream;
import java.io.OutputStreamWriter;
import java.io.PrintWriter;
import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * 服务为：将输入的两个参数通过IO存入文件
 * 
 * @author Yanhong Lee
 * 
 */
public class OutputService {
	private SimpleDateFormat df = new SimpleDateFormat(
			"[yyyy-MM-dd HH:mm:ss.SSS] ");

	public void output(String filename, int a, int b) throws Exception {
		PrintWriter out = new PrintWriter(new BufferedWriter(
				new OutputStreamWriter(new FileOutputStream(filename, true))),
				false);
		out.println(df.format(new Date()) + a + "+" + b + " = " + (a + b));
		out.close();
	}

}
