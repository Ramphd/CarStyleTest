package test.socket.test;
import java.io.IOException;

import test.socket.test.Client;
public class GetInfoTest {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		try {
			Client c = new Client();
			c.oneDirMultiCars();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}

}
