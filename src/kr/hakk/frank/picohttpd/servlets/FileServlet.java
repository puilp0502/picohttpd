package kr.hakk.frank.picohttpd.servlets;


import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;

import kr.hakk.frank.picohttpd.Request;
import kr.hakk.frank.picohttpd.Response;
import kr.hakk.frank.picohttpd.Servlet;

import java.nio.file.Path;

public class FileServlet extends Servlet {
	public static final String serveDir = "C:\\Webserver";
	@Override
	public Response serve(Request r, String data) {
		String relativeUri = r.getUri();
		while(relativeUri.contains("../")){
			relativeUri.replaceAll("../", ""); //LFI Protection
		}
		String fileuri = serveDir+relativeUri;
		try{
			Path path = Paths.get(fileuri);
			byte[] binarydata = Files.readAllBytes(path);
			return new Response(200, binarydata);
		}catch(IOException e){
			return new Response(404);
		}
		
	}

}
