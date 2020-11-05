/**
 *
 */
package bean;

import java.io.Serializable;
import java.util.ArrayList;

/**
 * USER CLASS
 * @author macforsong
 *
 */
public class User implements Serializable {

    private String name;//�û���
    private String password;//����

    /**
     * �޲ι��캯��
     */
    public User() {

    }

    /**
     * �вι��캯��
     * @param name �û�
     * @param password ����
     */
    public User(String name, String password) {
        super();
        this.name = name;
        this.password = password;

    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    @Override
    public String toString() {
        return "User [name=" + name + "]";
    }


}
