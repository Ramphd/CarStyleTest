package test.socket.test;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.io.PrintWriter;
import java.net.Socket;

public class ServerRun extends Thread {

	private Socket clientSocket;
	private BufferedReader sin;
	private PrintWriter sout;

	public ServerRun(Socket s) throws IOException {
		clientSocket = s;
		sin = new BufferedReader(new InputStreamReader(clientSocket.getInputStream()));
		sout = new PrintWriter(new BufferedWriter(new OutputStreamWriter(clientSocket.getOutputStream())), true);
		start();
	}

	@Override
	public void run() {
		// TODO Auto-generated method stub
		sout.println("U has been conneted to the server");
		try {
			String str = null;
			while (true) {
				str = sin.readLine();
				if ("exit".equals(str))
					break;
				if(str != null){
				System.out.println("Server recivied info :" + str);
				sout.println("Info has been recivied by Server");
				}
			}
			System.out.println("colsing server");
		} catch (IOException e) {
			e.printStackTrace();
		} finally {
			System.out.println("close server and io");
			try {
				sin.close();
				sout.close();
				clientSocket.close();
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
	}
}
