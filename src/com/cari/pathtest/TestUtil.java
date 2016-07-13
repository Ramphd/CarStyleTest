package com.cari.pathtest;

import java.io.IOException;
import java.io.PrintWriter;
import java.nio.file.FileAlreadyExistsException;
import java.nio.file.FileVisitResult;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.SimpleFileVisitor;
import java.nio.file.StandardOpenOption;
import java.nio.file.attribute.BasicFileAttributes;

public class TestUtil {
	private static Path writeFile = null;
	//private static Path valFile = Paths.get("D:", "valFile.txt");
	private static PrintWriter pw =null;
	private static int count = 0;
	private static int directory_depth = 0;
//	public static void getImgPath(String startDir) throws IOException{
//		if(pw == null)
//		try{
//			pw = new PrintWriter(Files.newBufferedWriter(trainFile, StandardOpenOption.CREATE,StandardOpenOption.WRITE));
//		}catch(FileAlreadyExistsException e){
//			pw = new PrintWriter(Files.newBufferedWriter(trainFile, StandardOpenOption.APPEND));
//		}
//		Path dir = Paths.get(startDir);
//		DirectoryStream<Path> stream  = Files.newDirectoryStream(dir);
//		for(Path file : stream){
//			if(Files.isDirectory(file,LinkOption.NOFOLLOW_LINKS) == true){
//				count ++ ;
//				getImgPath(file.toString());
//			}
//			else{
//			System.out.println(file);
//			pw.println(file.toString() + " "+count);
//			}
//		}
//		pw.flush();
//		pw.close();
//	}
	public static void getImgPath1(String startDir,boolean reWrite,int startLabel) throws IOException{
		MyFileVisitor visitor = new MyFileVisitor();  
		Path dir = Paths.get(startDir);
		if(startDir.endsWith("train")){
			System.out.println("write trainFile");
			writeFile = Paths.get("D:", "trainFile.txt");
		}
		else{		
			System.out.println("write valFile");
			writeFile = Paths.get("D:", "valFile.txt");
		}
		getFileWriteReady(reWrite);
				
        Files.walkFileTree(dir, visitor); 
        pw.flush();
        pw.close();
        System.out.println("DONE");
	}
	private static void getFileWriteReady(boolean reWrite) throws IOException{
		if(reWrite == true){
			try{
			pw = new PrintWriter(Files.newBufferedWriter(writeFile, StandardOpenOption.CREATE_NEW));
			}catch(FileAlreadyExistsException e){
				Files.delete(writeFile);
				pw = new PrintWriter(Files.newBufferedWriter(writeFile, StandardOpenOption.CREATE));
			}
		}else{
			if(reWrite == false){
				try{
					pw = new PrintWriter(Files.newBufferedWriter(writeFile, StandardOpenOption.CREATE_NEW,StandardOpenOption.APPEND));
				}catch(FileAlreadyExistsException e){
					pw = new PrintWriter(Files.newBufferedWriter(writeFile,StandardOpenOption.APPEND));
				}
			}
		}
	}
	private static class MyFileVisitor extends SimpleFileVisitor<Path>{
		@Override
		public FileVisitResult preVisitDirectory(Path dir, BasicFileAttributes attrs) throws IOException {
			// TODO Auto-generated method stub
			directory_depth ++ ;
			return FileVisitResult.CONTINUE;
		}
		@Override
		public FileVisitResult postVisitDirectory(Path dir, IOException exc) throws IOException {
			// TODO Auto-generated method stub
			
			if(--directory_depth == 2)
				count ++ ;
			
			return FileVisitResult.CONTINUE;
		}
		@Override
		public FileVisitResult visitFile(Path file, BasicFileAttributes attrs) throws IOException {
			// TODO Auto-generated method stub
			if(file.toFile().getName().endsWith(".jpg"))
			pw.println(file.toString().substring(8) + " "+count);
			
			return FileVisitResult.CONTINUE;


		}
	}
}

