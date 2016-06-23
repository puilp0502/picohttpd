package kr.hakk.frank.picohttpd;

import java.util.Scanner;

public class Utils {
	public static Request parseRequest(String rawreq){
		Scanner s = new Scanner(rawreq);
		String[] request_line = s.nextLine().split(" ");
		Request r = new Request(request_line[0], request_line[1]);
		while(s.hasNextLine()){
			String header = s.nextLine();
			String[] line = header.split(": ");
			String name = line[0];
			String value = line[1];
			r.setField(name, value);
		}
		s.close();
		return r;
	}
}
