package kr.hakk.frank.picohttpd;

public abstract class Servlet {
	public abstract Response serve(Request r, String data);
}
