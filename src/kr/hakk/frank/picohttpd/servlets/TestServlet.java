package kr.hakk.frank.picohttpd.servlets;

import kr.hakk.frank.picohttpd.Request;
import kr.hakk.frank.picohttpd.Response;
import kr.hakk.frank.picohttpd.Servlet;

public class TestServlet extends Servlet {

	@Override
	public Response serve(Request r, String data) {
		String ua = r.getField("User-Agent");
		String host = r.getField("Host");
		String method = r.getMethod();
		String respBody = "<html><body>UA:"+ua+"<br/>"
				+ "Host:"+host+"<br/>"
				+ "Request Method:"+method+"<br/>"
				+ "Request Uri:"+r.getUri()+"<br/></html></body>";
		Response resp = new Response(404,respBody);
		return resp;
	}

}
