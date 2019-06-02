package cn.edu.jxufe.testZookeeper.demo;

import java.io.IOException;

import org.apache.zookeeper.WatchedEvent;
import org.apache.zookeeper.Watcher;
import org.apache.zookeeper.ZooKeeper;
import org.junit.Test;

public class ZookeeperDemo {

	private String connectString = "master:2181,slave1:2181,slave2:2181";
	private int sessionTimeout = 3000;
	private ZooKeeper zooKeeperClient;

	
	@Test
	//创建zookeeper客户端
	public void init() throws IOException {
		zooKeeperClient = new ZooKeeper(connectString , sessionTimeout , new Watcher() {
			
			@Override
			public void process(WatchedEvent event) {
				System.out.println(".....");
				System.out.println(event.getPath());
				System.out.println(event.getState());
				System.out.println(event.getType());
				System.out.println(event.getWrapper());
			}
		});
	}
	
	public void createZnode() {
		
	}
	
}
