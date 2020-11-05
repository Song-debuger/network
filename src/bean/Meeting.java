/**
 * 
 */
package bean;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
/**
 * MEET CLASS
 * @author macforsong
 *
 */
public class Meeting implements Serializable{

	private int meetingId;//�����ID
	private Date startTime;//��ʼʱ��
	private Date endTime;//����ʱ��
	private String title;//����
	private ArrayList<User> participants=new ArrayList<User>();//������

	/**
	 * �޲ι��캯��
	 */
	public Meeting(){
	}

	/**
	 * �вι��캯��
	 * @param meetingId �����ID
	 * @param startTime ��ʼʱ��
	 * @param endTime ����ʱ��
	 * @param title ����ı���
	 * @param participants ������
	 */
	public Meeting(int meetingId, Date startTime, Date endTime, String title,
			ArrayList<User> participants) {
		super();
		this.meetingId = meetingId;
		this.startTime = startTime;
		this.endTime = endTime;
		this.title = title;
		this.participants = participants;
	}


	public int getMeetingId() {
		return meetingId;
	}


	public void setMeetingId(int meetingId) {
		this.meetingId = meetingId;
	}


	public Date getStartTime() {
		return startTime;
	}


	public void setStartTime(Date startTime) {
		this.startTime = startTime;
	}


	public Date getEndTime() {
		return endTime;
	}


	public void setEndTime(Date endTime) {
		this.endTime = endTime;
	}


	public String getTitle() {
		return title;
	}


	public void setTitle(String title) {
		this.title = title;
	}


	public ArrayList<User> getParticipants() {
		return participants;
	}


	public void setParticipants(ArrayList<User> participants) {
		this.participants = participants;
	}


	@Override
	public String toString() {
		return "Meeting"+meetingId+" [startTime=" + startTime
				+ ", endTime=" + endTime + ", title=" + title
				+ ", participants=" + participants +"]";
	}
	
}
