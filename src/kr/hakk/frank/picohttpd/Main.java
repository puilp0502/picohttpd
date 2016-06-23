package kr.hakk.frank.picohttpd;

import kr.hakk.frank.picohttpd.servlets.TestServlet;

public class Main {

	public static void main(String[] args) {
		ConnectionWrapper.setServlet((Class<?>)TestServlet.class);
		ConnectionHandler.open("localhost", 8081);
	}

}
