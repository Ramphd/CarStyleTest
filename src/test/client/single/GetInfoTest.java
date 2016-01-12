package test.client.single;
import java.io.IOException;
import java.util.HashMap;
import java.util.concurrent.ExecutionException;


public class GetInfoTest {
	private static String dir = "CarStyle_test/test2/";
	private static String imagePath = "CarStyle_test/" + "0019_66.jpg";
	
	public static void main(String[] args) throws IOException, InterruptedException, ExecutionException {
		// TODO Auto-generated method stub
		HashMap<String,Object> hm1 = new HashMap<String,Object>();
		HashMap<String,Object> hm2 = new HashMap<String,Object>();
		ClientRunImpl cri = new ClientRunImpl();
		hm1 = cri.oneDirMultiCars(dir);
		//cri.oneImageMultiCars(imagePath);
		//hm2 = cri.singleCar(imagePath);
		//System.out.println(hm2 + "\n" + hm1);

		System.out.println("main");
//		cri.terminal();
	}

}
