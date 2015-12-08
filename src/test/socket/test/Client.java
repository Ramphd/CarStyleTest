package test.socket.test;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.io.PrintWriter;
import java.net.InetAddress;
import java.net.Socket;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.concurrent.Callable;

import com.alibaba.fastjson.JSON;

public class Client implements Callable<String[]> {
	private static int portNum = 2333;
	private Socket socket;
	private BufferedReader in;
	private PrintWriter out;
	private String execode;

	public Client(String execode) throws IOException {
		// TODO Auto-generated constructor stub
		InetAddress addr = InetAddress.getByName("localhost");
		socket = new Socket(addr, portNum);
		socket.setSoLinger(true, 30);
		in = new BufferedReader(new InputStreamReader(socket.getInputStream()));
		out = new PrintWriter(new BufferedWriter(new OutputStreamWriter(socket.getOutputStream())), true);
		this.execode = execode;

	}

	@SuppressWarnings("unchecked")
	private String[] carStylePRun() throws IOException {
		try {
			System.out.println(socket);
			out.println(execode);
			System.out.println("'" + execode + "'" + " has been sended");
			// Receive json_image_name_list
			String j_image_name_list = in.readLine();
			System.out.println("json_: " + j_image_name_list);
			// send json_image_name_list feedback
			out.println("imageNameList has been received");
			// receive json_predict_result
			String j_predict_result = in.readLine();
			System.out.println("json_: " + j_predict_result);
			// send json_predict_result feedback
			out.println("predictResult has been received");
//			ArrayList<String> al = new ArrayList<String>();
//			al = JSON.parseObject(j_image_name_list, ArrayList.class);
//			System.out.println("the first image is :" + al);
//			HashMap<String, ArrayList<String>> hm = new HashMap<String, ArrayList<String>>();
//			hm = JSON.parseObject(j_predict_result, HashMap.class);
//			System.out.println("total predict result is :" + hm);
			String[] rstr = new String[2];
			rstr[0] = j_image_name_list;
			rstr[1] = j_predict_result;
			return rstr;
		} finally {
			System.out.println("close the Client socket and the io.");
			in.close();
			out.close();
			socket.close();
		}
	}

	@Override
	public String[] call() throws IOException {
		// TODO Auto-generated method stub
		return carStylePRun();
	}
}