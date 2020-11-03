/**
 * 
 */
package interface_;
import java.rmi.Remote;
import java.rmi.RemoteException;
import java.util.ArrayList;

import bean.User;
/**
 * �����û��Ľӿ�
 * @author ZhangHan
 *
 */
public interface UserInterface extends Remote{
	/**
	 * ���û��������û�
	 * @param name �û���
	 * @return �û���ʵ��
	 * @throws RemoteException
	 */
	public User getUserByName(String name)throws RemoteException;
	/**
	 * ����û����������������
	 * @param user �û���ʵ��
	 * @return �Ƿ���ӳɹ�
	 * @throws RemoteException
	 */
	public boolean addUser(User user)throws RemoteException;
	/**
	 * ������ӵķ����������������ȡ�û��б�
	 * @return
	 * @throws RemoteException
	 */
	public ArrayList<User> getUserList()throws RemoteException;
	
}
