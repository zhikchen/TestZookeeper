package cn.edu.jxufe.testZookeeper.demo;

import java.io.IOException;
import java.util.List;

import org.apache.zookeeper.CreateMode;
import org.apache.zookeeper.WatchedEvent;
import org.apache.zookeeper.Watcher;
import org.apache.zookeeper.ZooKeeper;
import org.apache.zookeeper.data.Stat;
import org.apache.zookeeper.ZooDefs.Ids;
import org.junit.Before;
import org.junit.Test;

public class ZookeeperDemo {

	private String connectString = "master:2181,slave1:2181,slave2:2181";
	private int sessionTimeout = 3000;
	private ZooKeeper zooKeeperClient;
	
	
	//创建zookeeper客户端
	@Test
	@Before
	public void init() throws IOException {
		zooKeeperClient = new ZooKeeper(connectString , sessionTimeout , new Watcher() {
			
			@Override
			public void process(WatchedEvent event) {
				try {
					zooKeeperClient.getChildren("/", true);
				} catch (Exception e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
				System.out.println("-------");
				System.out.println(event.getPath());
				System.out.println(event.getType());
				System.out.println("-------");
			}
		});
	}
	//创建子节点
	@Test
	public void createzNode() throws Exception {
		//参数：路径     znode的数据          权限       创建子节点的方式
		zooKeeperClient.create("/demo", "testzookeeper".getBytes(), Ids.OPEN_ACL_UNSAFE,CreateMode.PERSISTENT_SEQUENTIAL);
	}
	
	//获取子节点
	@Test
	public void getChild() throws Exception{
		//对某一路径监听，并返回子节点
		List<String> children = zooKeeperClient.getChildren("/", true);
		for (String child : children) {
			System.out.println(child);
		}
		Thread.sleep(Long.MAX_VALUE);
	}
	//判断路径是否存在
	@Test
	public void isExist() throws Exception{
		Stat stat = zooKeeperClient.exists("/hbase", false);
		System.out.println(stat != null);
	}
}
