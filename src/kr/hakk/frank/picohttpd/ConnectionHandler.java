package kr.hakk.frank.picohttpd;


import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class ConnectionHandler {
	private static int PORT = 8080;
	private static final int MAX_THREAD = 10;
	private static ExecutorService threadPool = Executors.newFixedThreadPool(MAX_THREAD);
	public static void open(String host, int port){
		ConnectionHandler.PORT = port;
		try{
			ServerSocket serverSocket = new ServerSocket(PORT);
			System.out.println("Server listening @ "+host+":"+port);
			while(true){
				Socket socket = serverSocket.accept();
				try{
					threadPool.execute(new ConnectionWrapper(socket));
				}catch(Exception e){
					e.printStackTrace();
				}
			}
		}catch(IOException e){
			e.printStackTrace();
		}
	}
}
