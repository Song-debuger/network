/**
 * 
 */
package server;
import rface.MeetingInterface;

import java.rmi.RemoteException;
import java.rmi.server.UnicastRemoteObject;
import java.util.ArrayList;
import java.util.Date;

import bean.Meeting;
import bean.User;

/**
 * MeetingInterface��ʵ����
 * @author macforsong
 *
 */
public class MeetingImplement extends UnicastRemoteObject implements
MeetingInterface {
	private static final long serialVersionUID = 1L;
	private ArrayList<Meeting> meetingList = new ArrayList<Meeting>();// �����б�

/**
* �޲ι��캯����������ڣ����׳�RemoteException
* 
* @throws RemoteException
*/
public MeetingImplement() throws RemoteException {
// System.out.println("MeetingImplement--OK");
}

@Override
public ArrayList<Meeting> getMeetingList() throws RemoteException {
	return this.meetingList;//����meetingList
}

@Override
	public ArrayList<Meeting> queryByTime(Date startTime, Date endTime)
	throws RemoteException {

	if (meetingList.isEmpty())
	return null;// ��ǰ�����б�Ϊ�գ�ֱ���˳�����
	ArrayList<Meeting> list = new ArrayList<Meeting>();// ����б�
/*
 * Step 1:�������з��������Ļ���
 * ����[startTime,endTime]�������ڵ����л���
 */
	for (Meeting meeting : meetingList) {
	if (!meeting.getStartTime().before(startTime)
			&& !meeting.getEndTime().after(endTime)) {
		list.add(meeting);
	}
}
/*
 * Step 2:�жϽ���б��Ƿ�Ϊ�գ�Ϊ��ֱ����������
 */
	if (list.isEmpty())
		return null;
/*
 * Step 3:�Խ���б��еĻ��飬��ʱ��˳������
 */
	Meeting[] meetings = new Meeting[list.size()];
	list.toArray(meetings);//ת��Ϊ����
	Sort(meetings);// ����
	ArrayList<Meeting> result = new ArrayList<Meeting>();// �ؽ�����б�
	for (int i = 0; i < list.size(); i++) {
		result.add(meetings[i]);
}
	return result;// ���ؽ��
}

/**
* ����
* 
* @param a
*            ����������
*/
private static void Sort(Meeting[] a) {
	int n = a.length;
//ð������
	for (int i = 0; i < n - 1; i++) {
		for (int j = 0; j < n - i - 1; j++) {
			if (a[j].getStartTime().after(a[j + 1].getStartTime())) {
				swap(a, j, j + 1);
			}
		}
	}
}

/**
* ���������Ԫ��λ��
* @param list ����������
* @param k λ��
* @param i λ��
*/
private static void swap(Meeting[] list, int k, int i) {
	Meeting temp = list[k];
	list[k] = list[i];
	list[i] = temp;
}

@Override
public boolean addMeeting(Meeting meeting) throws RemoteException {
	Date start = meeting.getStartTime();
	Date end = meeting.getEndTime();
/*
 * ʱ���ص����
 */
	if (!meetingList.isEmpty()) {// �����ǰ�����б�Ϊ�գ����ؼ��
		for (Meeting i : meetingList) {
			if (!i.getStartTime().after(start)
					&& !start.after(i.getEndTime())) {
				//start�����м�
				return false;// ���ؽ�������ʧ��
			} else if (!i.getStartTime().after(end)
					&& !end.after(i.getEndTime())) {
				//end�����м�
				return false;// ���ؽ�������ʧ��
			} else if (!start.after(i.getStartTime())
					&& !i.getStartTime().after(end)) {
				//getStartTime�����м�
				return false;// ���ؽ�������ʧ��
			} else if (!start.after(i.getEndTime())
					&& !i.getEndTime().after(end)) {
				//getEndTime�����м�
				return false;// ���ؽ�������ʧ��
			}
		}
	}
	this.meetingList.add(meeting);// ���ۻ���
	return true;// ���ؽ������ӳɹ�
}

@Override
public void clearMeeting() throws RemoteException {
	this.meetingList.clear();//���meetingList
}

@Override
public boolean deleteById(int meetingID, User user) throws RemoteException {
/*
 * ���ҷ��������Ļ���
 */
	for (Meeting i : meetingList) {
		if (i.getMeetingId() == meetingID) {// ID����
			ArrayList<User> u = i.getParticipants();//��ȡ�������б�
			if(u.get(0).getName().equals(user.getName())){// ִ�в��������Ǵ����������
	//			System.out.println(u.get(0).getName());
	//			System.out.println(u.get(1).getName());
				meetingList.remove(i);// �Ƴ�����
				return true;// ���ؽ����ɾ���ɹ�
			}
		}
	}
	return false;// ���ؽ����ɾ��ʧ��
	}
}
