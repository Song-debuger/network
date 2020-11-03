/**
 * 
 */
package server;
import interface_.MeetingInterface;
import interface_.UserInterface;

import java.net.MalformedURLException;
import java.rmi.Naming;
import java.rmi.RemoteException;
import java.rmi.registry.LocateRegistry;
/**
 * Զ�̷�����
 * @author ZhangHan
 *
 */
public class Server {

	public static void main(String[] args) throws RemoteException,
	MalformedURLException {

	/**
	 * Step 1:����Զ�̶���
	 */
	MeetingInterface meetingInterface = new MeetingImplement();
	UserInterface userInterface = new UserImplement();
	/**
	 * Step 2:ע��˿ں�
	 */
	LocateRegistry.createRegistry(1099);
	/**
	 * Step 3:��Զ�̶���
	 */
	Naming.rebind("MeetingRemote", meetingInterface);
	Naming.rebind("UserRemote", userInterface);
	/**
	 * Step 4:�������������
	 */
	System.out.println("�����������ɹ�");
	}
}
