/**
 * 
 */
package server;
import java.rmi.RemoteException;
import java.rmi.server.UnicastRemoteObject;
import java.util.ArrayList;

import bean.User;
import interface_.UserInterface;
/**
 * UserInterface��ʵ����
 * @author ZhangHan
 *
 */
public class UserImplement extends UnicastRemoteObject implements UserInterface{

	private static final long serialVersionUID = 1L;
	private ArrayList<User> userList=new ArrayList<User>();//�û��б�
	/**
	 * �޲ι��캯����������ڣ����׳��쳣RemoteException
	 * @throws RemoteException
	 */
	public UserImplement() throws RemoteException{
//		System.out.println("UserImplement--OK");
	}
	@Override
	public ArrayList<User> getUserList()throws RemoteException {
		return this.userList;//����userList
	}
	@Override
	public User getUserByName(String name) throws RemoteException {
		for(User user:userList){
			if(user.getName().equals(name)){//��������һ����User
				return user;//���ط���������Userʵ��
			}
		}
		return null;//δ�ҵ���Ӧ�û�������null
	}
	@Override
	public boolean addUser(User user) throws RemoteException {
		int tag=0;//��ǣ��Ƿ�����
		for(User i:userList){
			if(i.getName().equals(user.getName())){//�ҵ���ͬ�û������û�
				tag=1;//�����Ϊ1
				break;//�˳�ѭ��
			}
		}
		if(tag==0){//���Ϊ0��˵�����ظ�
			userList.add(user);//���û��б�������û�
			System.out.println("����ӵ��û�"+user.toString());
			return true;//���ؽ������ӳɹ�
		}else{//���Ϊ1���û����ظ�
			return false;//���ؽ�������ʧ��
		}
	}
}
