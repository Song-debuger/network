/**
 * 
 */
package interface_;
import java.rmi.Remote;
import java.rmi.RemoteException;
import java.util.ArrayList;
import java.util.Date;

import bean.Meeting;
import bean.User;

/**
 * ��������Ľӿ�
 * @author ZhangHan
 *
 */
public interface MeetingInterface extends Remote {
	/**
	 * ��ʱ���ѯ����
	 * @param startTime ��ʼʱ��
	 * @param endTime ����ʱ��
	 * @return ��ʱ��˳�����еĻ����б�
	 * @throws RemoteException
	 */
	ArrayList<Meeting> queryByTime(Date startTime, Date endTime)
			throws RemoteException;
	/**
	 * ��ӻ���
	 * @param meeting �����ʵ��
	 * @return �Ƿ���ӳɹ�
	 * @throws RemoteException
	 */
	boolean addMeeting(Meeting meeting) throws RemoteException;
	/**
	 * ��ջ���
	 * @throws RemoteException
	 */
	void clearMeeting() throws RemoteException;
	/**
	 * ���ջ�����û�ɾ������
	 * @param meetingID �����ID
	 * @param user �û���ʵ��
	 * @return �Ƿ�ɹ�ɾ������
	 * @throws RemoteException
	 */
	boolean deleteById(int meetingID, User user) throws RemoteException;
	/**
	 * ������ӵķ����������������ȡ�����б�
	 * @return �����б�
	 * @throws RemoteException
	 */
	ArrayList<Meeting> getMeetingList() throws RemoteException;

}
