package test.socket.test;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.io.PrintWriter;
import java.net.InetAddress;
import java.net.Socket;
import java.util.ArrayList;
import java.util.HashMap;
import com.alibaba.fastjson.JSON;

public class Client {
	private static int portNum = 2333;
	private Socket socket = null;
	private BufferedReader in = null;
	private PrintWriter out = null;
	private String caffe_root = File.separator + "home" + File.separator + "cari" + File.separator + 
			"caffe" + File.separator;
	public Client() throws IOException {
		// TODO Auto-generated constructor stub
		InetAddress addr = InetAddress.getByName("localhost");
		socket = new Socket(addr,portNum);
		in = new BufferedReader(new InputStreamReader(socket.getInputStream()));
		out = new PrintWriter(new BufferedWriter(
				new OutputStreamWriter(socket.getOutputStream())), true);
	}
	public static void main(String[] args) throws IOException {
		// TODO Auto-generated method stub
		//Client c = new Client();
		
	
	}
	@SuppressWarnings("unchecked")
	public void oneDirMultiCars() throws IOException{
		try{
			System.out.println("This func is oneDirMultiCars and this socket is " + socket);
			String imageDir = caffe_root + "CarStyle_test" + File.separator;
			String execode = "c|" + imageDir;
			//send execode to Server
			out.println(execode);
			System.out.println(execode + "has been sended");
			//Receive json_image_name_list
			String j_image_name_list = in.readLine();
			System.out.println(j_image_name_list);
			//send json_image_name_list feedback
			out.println("imageNameList has been received");
			//receive json_predict_result 
			String j_predict_result = in.readLine();
			System.out.println(j_predict_result);
			//send json_predict_result feedback
			out.println("predictResult has been received");
			ArrayList<String> al = new ArrayList<String>();
			al = JSON.parseObject(j_image_name_list, ArrayList.class);
			System.out.println("the first image is :" + al.get(1));
			HashMap<String,ArrayList<String>> hm = new HashMap<String,ArrayList<String>>();
			hm = JSON.parseObject(j_predict_result, HashMap.class);
			System.out.println("total predict result is :" + hm);
		}finally{
			System.out.println("close the Client socket and the io.");
			in.close();
			out.close();
            socket.close();
		}
	}
	public void singleCar(String imageAddr){
		
	}
	public void oneImageMultiCars(String imageAddr){
		
	}
}