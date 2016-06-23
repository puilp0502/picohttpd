package kr.hakk.frank.picohttpd;

import java.io.ByteArrayOutputStream;
import java.io.IOException;

public class Response {
	private int code;
	private String message;
	private String httpVer="HTTP/1.1";
	public byte[] body;
	
	public static String getMessage(int code){
		switch(code){
		case 100:
			return "Continue";
		case 200:
			return "OK";
		case 204:
			return "Non Content";
		case 301:
			return "Moved Permanently";
		case 302:
			return "Moved Temporarily";
		case 304:
			return "Not modified";
		case 400:
			return "Bad Request";
		case 403:
			return "Forbidden";
		case 404:
			return "Not found";
		case 405:
			return "Method not allowed";
		case 500:
			return "Internal Server Error";
		case 501:
			return "Not Implemented";
		case 503:
			return "Service Unavailable";
		}
		return "Not Implemented";
	}
	public static String getDefaultBody(int code){
		switch(code){
		case 403:
			return "403 Forbidden";
		case 404:
			return "404 Not Found";
		case 500:
			return "500 Internal Server Error";
		}
		return "";
	}
	public Response(){
		this(200);
	}
	public Response(int code){
		this(code, getDefaultBody(code));
	}
	public Response(int code, String body){
		this(code, body, getMessage(code));
	}
	public Response(int code, byte[] body){
		setCode(code, getMessage(code));
		this.body = body;
	}
	public Response(int code, String body, String message){
		this(code, body, message, "HTTP/1.1");
	}
	public Response(int code, String body, String message, String httpVer){
		setCode(code, message);
		this.body = body.getBytes();
		this.httpVer = httpVer;
	}
	public void setCode(int code){
		setCode(code, getMessage(code));
	}
	public void setCode(int code, String message){
		this.code = code;
		this.message = message;
	}
	public byte[] toByte() {
		byte[] resp = (httpVer+" "+code+" "+message+"\r\n\r\n").getBytes();
		ByteArrayOutputStream outputStream = new ByteArrayOutputStream( );
		try {
			outputStream.write( resp );
			outputStream.write( body );
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return outputStream.toByteArray( );
	}
	
}
