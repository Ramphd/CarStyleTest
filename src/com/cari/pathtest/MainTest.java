package com.cari.pathtest;
import static com.cari.pathtest.TestUtil.*;

import java.io.File;
import java.io.IOException;
public class MainTest {

	public static void main(String[] args) throws IOException {
		// TODO Auto-generated method stub
		//eg:DIR NAME MUST BE IN THIS FORM: "D:" + File.separator + "pic"
		String sp = File.separator;
		String startDir = "D:" + sp +"¹¤×÷ÎÄµµ" + sp + "Done"+ sp + "val";
		//usage : startDir , if need reWrite , String "train" for write trainFile ,
		//"val" for write valFile,startLabel from 0 to what u want
		getImgPath1(startDir,true,0);
	}

}
