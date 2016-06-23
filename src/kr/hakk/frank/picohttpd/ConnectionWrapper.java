package kr.hakk.frank.picohttpd;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.net.Socket;

public class ConnectionWrapper implements Runnable {
	private Socket socket = null;
	private BufferedReader is;
	private BufferedWriter os;
	private static Class<Servlet> servlet = null;
	static final int timeout = 10000;
	public static void setServlet(Class<?> s){
		if(Servlet.class.isAssignableFrom(s))
			ConnectionWrapper.servlet = (Class<Servlet>) s;
		else{
			System.err.println("!!! setServlet Received Invalid Class !!!");
			return;
		}
	}
	public ConnectionWrapper(Socket socket){
		System.out.println("ConnectionWrapper Initialized");
		this.socket = socket;
		try {
			is = new BufferedReader(new InputStreamReader(this.socket.getInputStream()));
			os = new BufferedWriter(new OutputStreamWriter(this.socket.getOutputStream()));	
			this.socket.setSoTimeout(timeout);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	@Override
	public void run() {
		// TODO Auto-generated method stub
		try{
			StringBuilder sb = new StringBuilder();
			String buffer;
			System.out.println("Started Reading");
			int CRLFCounter=0;
			while((buffer=is.readLine())!=null){
				sb.append(buffer).append("\r\n");
				if(buffer.equals("")){
					CRLFCounter++;
					System.out.println(CRLFCounter);
				}else{
					CRLFCounter=0;
				}
				if(CRLFCounter>=1) break;
			}
			System.out.println("Read Completed");
			String input = sb.toString();
			System.out.println(input);
			String[] splitRequest = input.split("\r\n\r\n",2);
			Request r = Utils.parseRequest(splitRequest[0]);
			String body = splitRequest[1];
			try {
				System.out.println("Processing request...");
				Servlet s = ConnectionWrapper.servlet.newInstance();
				Response resp = s.serve(r, body);
				System.out.println("Response:");
				System.out.println(resp.toString());
				socket.getOutputStream().write(resp.toByte());
				os.flush();
				
			} catch (InstantiationException | IllegalAccessException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
				os.write(new Response(500).toString());
			}
			socket.close();
		}catch(IOException e){
			e.printStackTrace();
		}
	}
}
