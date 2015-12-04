package test.socket.test;

import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;

public class Server {
	
	static final int portNo = 2333;
	public static void main(String[] args) throws IOException{
		// TODO Auto-generated method stub
		ServerSocket s = new ServerSocket(portNo);
		System.out.println("The server is started " + s);
		try{
			while(true){
				Socket socket = s.accept();
				new ServerRun(socket);
			}
		}finally{
			s.close();
		}
	}

}
