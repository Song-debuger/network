
package client;
import interface_.MeetingInterface;
import interface_.UserInterface;

import java.net.MalformedURLException;
import java.rmi.Naming;
import java.rmi.NotBoundException;
import java.rmi.RemoteException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.Scanner;

import bean.Meeting;
import bean.User;

/**
 * Զ�̿ͻ���
 * @author ZhangHan
 *
 */
public class Client {

	static Meeting meeting;// ����
	static int meetingID;
	static User user;// �û�
	static MeetingInterface meetingInterface;// ��������Ľӿ�
	static UserInterface userInterface;// �����û��Ľӿ�
	static SimpleDateFormat format = new SimpleDateFormat("M-d-k:m");// ��ʽ������

	public static void main(String[] args) throws RemoteException,
			MalformedURLException, NotBoundException, ParseException {
		/**
		 * Step 1:��ѯԶ�̶���
		 */
		meetingInterface = (MeetingInterface) Naming.lookup("MeetingRemote");
		userInterface = (UserInterface) Naming.lookup("UserRemote");
		/**
		 * Step 2:��ȡ���룬�����û��������봴���ͻ���
		 */
		String name;// �û���
		String password;// ����
		Scanner in = new Scanner(System.in);
		while (true) {
			System.out.println("�����û���");
			name = in.nextLine();
			System.out.println("��������");
			password = in.nextLine();
			user = new User(name, password);// �����û�
			boolean success = userInterface.addUser(user);// ����û�
			if (success) {// ��ӳɹ�
				break;// �˳�ѭ��
			} else {// ���ʧ�ܣ����û����ظ�ʱ����
				System.err.println("��������");
			}
		}
		/**
		 * Step 3:����û����������û���������1ʱ����ѭ��
		 */
		int falg = 0;// ȷ���Ƿ������ʾ�ı��
		while (true) {
			if (userInterface.getUserList().size() > 1) {// �û���������1
				break;// �˳�ѭ��
			} else {// �û���������1��
				if (falg == 0) {// ��һ�μ�⵽ֻ��һ���û���������ʾ
					System.out.println("ֻ��һ���û�����������û�");
					falg = 1;// ��ǣ�֮���ѭ��������ʾ
				}
			}
		}
		/**
		 * Step 4:���������ɹ�����ӡ�˵�
		 */
		System.out.println("�ͻ��������ɹ�");
		printIntroduction();// ����˵�
		/**
		 * Step 5:��ȡ���룬��������
		 */
		String line = in.nextLine();
		int tag = handle(line);// ����Ƿ��˳�����
		while (true) {
			if (tag == 1)
				break;// �û�����quitʱ�������Ϊ1���˳�ѭ�����������
			line = in.nextLine();// ������ȡ�û�����
			tag = handle(line);// ��ȡ���
		}
	}

	/**
	 * �����û�����
	 * 
	 * @param line
	 *            �û�����
	 * @return ����Ƿ��˳�����0λ����ִ�г���1λ�˳�ѭ����
	 * @throws RemoteException
	 * @throws ParseException
	 */
	public static int handle(String line) throws RemoteException,
			ParseException {
		if (line.equals("help")) {// ����Ϊhelpʱ��ӡ�˵�
			printIntroduction();
			return 0;
		} else if (line.equals("quit")) {// ����Ϊquitʱ�������
			System.out.println("Bye");
			return 1;
		} else if (line.equals("clear")) {// ����Ϊclearʱ��ջ����¼
			meetingInterface.clearMeeting();// ����Զ�̷���
			System.out.println("Clear finished.");
			return 0;
		} else if (line.startsWith("add")) {// ����Ϊaddʱ��ӻ���
			addMeeting(line);
			return 0;
		} else if (line.startsWith("delete")) {// ����Ϊdeleteʱɾ������
			deleteMeeting(line);
			return 0;
		} else if (line.startsWith("query")) {// ����Ϊqueryʱ��ѯ����
			queryMeeting(line);
			return 0;
		} else {
			System.err.println("�Ƿ�����");// ���ڲ����ڵ��������������ʾ
			return 0;
		}
	}

	/**
	 * ��ӻ���
	 * 
	 * @param line
	 *            ����
	 * @throws RemoteException
	 */
	public static void addMeeting(String line) throws RemoteException {
		String[] arg = line.split(" ");
		int length = arg.length;
		if (arg.length != 5) {// ����������Ϊ5ʱ������������ʾ
			System.err.println("������������");
		} else {
			ArrayList<User> userList = userInterface.getUserList();// ��ȡ�û��б�
			User user2 = userInterface.getUserByName(arg[1]);// �����ڶ����û���ʵ��
			if (user2 == null) {// ���δ�ҵ����û�������������ʾ
				System.err.println("�Ҳ������û�");
				return;// �˳�������������ȡ����
			} else if (user2.getName().equals(user.getName())) {// ���������û���Ϊ���ˣ�����������ʾ
				System.err.println("�������뱾��");
				return;// �˳�������������ȡ����
			} else {
				meetingID ++;// �����ID
				try {
					Date start = format.parse(arg[2]);// ����Ŀ�ʼʱ��
					Date end = format.parse(arg[3]);// ����Ľ���ʱ��

					if (start.after(end) || start.equals(end)) {// ʱ��˳����󣬸���������ʾ
						System.err.println("��������ȷʱ��˳��");
						return;// �˳�������������ȡ����
					}
					String title = arg[4];// ����ı���
					ArrayList<User> users = new ArrayList<User>();// �û��б�
					users.add(user);
					users.add(user2);
					meeting = new Meeting(meetingID, start, end, title, users);// ʵ��������
					if (!meetingInterface.addMeeting(meeting)) {// ����Զ�̷���
						System.err.println("ʱ���ص�");// Զ�̷��������ʱ���ص����
					} else {
						System.out.println("��ӳɹ�");
					}
				} catch (ParseException e) {// ʱ���ʽ����
					System.err.println("��������ȷ��ʱ���ʽ");
					return;// �˳�������������ȡ����
				}
			}
		}
	}
	/**
	 * ɾ������
	 * @param line ����
	 * @throws RemoteException
	 */
	public static void deleteMeeting(String line) throws RemoteException {

		String[] arg = line.split(" ");
		if (arg.length != 2) {// ����������Ϊ2ʱ������������ʾ
			System.err.println("������������");
		} else {
			int id = Integer.parseInt(arg[1]);// �����ID
			boolean success = meetingInterface.deleteById(id, user);// ����Զ�̷���
			if (success) {
				System.out.println("ɾ������ɹ�");
			} else {
				System.err.println("ɾ��ʧ��");
			}
		}
	}

	public static void queryMeeting(String line) throws RemoteException {
		String[] arg = line.split(" ");
		ArrayList<Meeting> meetingList;
		if (arg.length != 3) {// ����������Ϊ3ʱ������������ʾ
			System.err.println("������������");
			return;
		} else {
			try {
				Date start = format.parse(arg[1]);// ���鿪ʼʱ��
				Date end = format.parse(arg[2]);// �������ʱ��
				if (start.after(end) || start.equals(end)) {// ʱ��˳�����
					System.err.println("��������ȷʱ��˳��");
					return;
				}
				meetingList = meetingInterface.queryByTime(start, end);// ����Զ�̷���
				if (meetingList == null) {
					System.out.println("��ѯΪ��");
				} else {// ��ʱ��˳�򣬴�ӡ������Ϣ
					for (Meeting i : meetingList) {
						System.out.println(i.toString());
					}
				}
			} catch (ParseException e) {// ʱ���ʽ���󣬸���������ʾ
				System.err.println("��������ȷʱ���ʽ");
				return;
			}
		}
	}

	/**
	 * ��ӡ�˵�
	 */
	public static void printIntroduction() {
		System.out.println("RMI Menu:");
		System.out.println("    1.add");
		System.out
				.println("        arguments: <username> <start> <end> <title>���磬12��23��16:00��ʾΪ12-23-16:00");
		System.out.println("    2.delete");
		System.out.println("        arguments: meetingid");
		System.out.println("    3.clear");
		System.out.println("        arguments: no args");
		System.out.println("    4.query");
		System.out.println("        arguments: <start> <end>");
		System.out.println("    5.help");
		System.out.println("        arguments: no args");
		System.out.println("    6.quit");
		System.out.println("        arguments: no args");
	}
}
