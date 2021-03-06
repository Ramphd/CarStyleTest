package test.socket.test;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;

import com.alibaba.fastjson.JSON;

public class ClientRunImpl {
	private String caffe_root = File.separator + "home" + File.separator + "cari" + File.separator + "caffe"
			+ File.separator;

	private ExecutorService executor = Executors.newCachedThreadPool();

	public HashMap<String, Object> singleCar(String imageAddr)
			throws IOException, InterruptedException, ExecutionException {
		System.out.print("This func is singleCar and this socket is ");
		String imageDir = caffe_root + imageAddr;
		String execode = "a|" + imageDir;
		// send execode to Server
		// carStylePRun(execode);
		return taskSubmit(execode);
	}

	public HashMap<String, Object> oneImageMultiCars(String imageAddr)
			throws IOException, InterruptedException, ExecutionException {
		System.out.print("This func is oneImageMutiCars and this socket is ");
		String imageDir = caffe_root + imageAddr;
		String execode = "b|" + imageDir;
		// send execode to Server
		// carStylePRun(execode);
		return taskSubmit(execode);
	}

	public HashMap<String, Object> oneDirMultiCars(String Dir)
			throws IOException, InterruptedException, ExecutionException {
		System.out.print("This func is oneDirMultiCars and this socket is ");
		String imageDir = caffe_root + Dir;
		String execode = "c|" + imageDir;
		// send execode to Server
		// carStylePRun(execode);
		return taskSubmit(execode);
	}

	private HashMap<String, Object> taskSubmit(String ecode) throws IOException, InterruptedException, ExecutionException {
		Future<String[]> result = executor.submit(new Client(ecode));
		// executor.shutdown();
		String[] s = new String[2];

		try {
			s = result.get();

		} catch (Exception e) {
			e.printStackTrace();
		}

		return pResult(s);
	}

	@SuppressWarnings("unchecked")
	private HashMap<String, Object> pResult(String[] s) {

		String j_image_name_list = s[0];
		String j_predict_result = s[1];
		ArrayList<String> al = new ArrayList<String>();
		al = JSON.parseObject(j_image_name_list, ArrayList.class);

		HashMap<String, ArrayList<String>> hm = new HashMap<String, ArrayList<String>>();
		hm = JSON.parseObject(j_predict_result, HashMap.class);
		HashMap<String, Object> tmp = new HashMap<String, Object>();
		tmp.put("list", al);
		tmp.put("result", hm);
		return tmp;
	}

	public void terminal() {
		executor.shutdown();
	}
}
