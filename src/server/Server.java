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
 * Զ�̷�����
 * @author macforsong
 *
 */
public class Server {

    public static void main(String[] args) throws RemoteException,
            MalformedURLException {

        /**
         * ����Զ�̶���
         */
        MeetingInterface meetingInterface = new MeetingImplement();
        UserInterface userInterface = new UserImplement();
        /**
         * ע��˿ں�
         */
        LocateRegistry.createRegistry(1099);
        /**
         * ��Զ�̶���
         */
        Naming.rebind("MeetingRemote", meetingInterface);
        Naming.rebind("UserRemote", userInterface);
        /**
         * �������������
         */
        System.out.println("======�����������ɹ�======");
    }
}
