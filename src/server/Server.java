/**
 * 
 */
package server;
import rface.MeetingInterface;
import rface.UserInterface;

import java.net.MalformedURLException;
import java.rmi.Naming;
import java.rmi.RemoteException;
import java.rmi.registry.LocateRegistry;
/**
 * 远程服务器
 * @author macforsong
 *
 */
public class Server {

	public static void main(String[] args) throws RemoteException,
	MalformedURLException {

	/**
	 * Step 1:创建远程对象
	 */
	MeetingInterface meetingInterface = new MeetingImplement();
	UserInterface userInterface = new UserImplement();
	/**
	 * Step 2:注册端口号
	 */
	LocateRegistry.createRegistry(1099);
	/**
	 * Step 3:绑定远程对象
	 */
	Naming.rebind("MeetingRemote", meetingInterface);
	Naming.rebind("UserRemote", userInterface);
	/**
	 * Step 4:服务器启动完毕
	 */
	System.out.println("服务器启动成功");
	}
}
