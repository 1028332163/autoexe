package neu.lab.autoexe.util;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.PrintWriter;
import java.util.HashSet;
import java.util.Set;

/**
 * filter content that isn't important in log .
 * 
 * @author asus
 *
 */
public class LogScissors {
	public static void filterTrace() {
		Set<String> traceIds = new HashSet<String>();// use the first two line as id.

		try {
			PrintWriter printer = new PrintWriter(new BufferedWriter(new FileWriter("d:\\log\\min_trace.txt")));
			BufferedReader reader = new BufferedReader(new FileReader("d:\\log\\trace.txt"));
			boolean isNewTrace = true;
			String trace = "";
			String line = reader.readLine();
			while (line != null) {
				if (line.startsWith("java.")) {
					//print last trace
					if(isNewTrace) {
						printer.println(trace);
						trace = "";
					}
					//handle this trace
					String nextLine = reader.readLine();
					String traceId = line + nextLine;
					if(!traceIds.contains(traceId)) {
						traceIds.add(traceId);
						isNewTrace = true;
						trace += (line + System.lineSeparator() + nextLine);
					}else {
						isNewTrace = false;
					}
				} else {
					if (isNewTrace)
						trace += System.lineSeparator()+line;
				}
				line = reader.readLine();
			}
			printer.println(trace);
			reader.close();
			printer.close();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	public static void main(String[] args) {
		filterTrace();
	}
}
