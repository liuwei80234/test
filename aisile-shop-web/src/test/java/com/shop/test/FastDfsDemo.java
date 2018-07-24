package com.shop.test;

import org.csource.fastdfs.ClientGlobal;
import org.csource.fastdfs.StorageClient;
import org.csource.fastdfs.StorageServer;
import org.csource.fastdfs.TrackerClient;
import org.csource.fastdfs.TrackerServer;
import org.junit.Test;

public class FastDfsDemo {

	@Test
	 public void FastDfsTest(){
		
		try {
			//第一步加载		
			ClientGlobal.init("E:/bawei/bigone/workspace/aisile-parent/aisile-shop-web/target/classes/conf/client.conf");
			//创建一个TrackerClient对象
			TrackerClient trackerClient = new TrackerClient();
			//通过TrackClient获得一个TrackerServer对象
			TrackerServer trackerServer = trackerClient.getConnection();
			//创建一个StrorageServer的引用，可以是null
			StorageServer storageServer = null;
			//创建一个StorageClient，参数需要TrackerServer和StrorageServer
			StorageClient storageClient = new StorageClient(trackerServer, storageServer);
			//使用StorageClient上传文件。
			String[] strings = storageClient.upload_file("D:/呀！/meme.jpg","jpg", null);
			for (String string : strings) {
				System.out.println(string);
			}
		
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} 
	}
}
