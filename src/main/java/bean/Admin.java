
package bean;
/**
 * This class is a bean class
 * for admin to store information
 * @author Gui Jiayi
 * @version 1.0
 */
public class Admin {
    /**
     * number the administrator given
     */
    private String acc_No;
    /**
     * password the administrator given
     */
    private String password;

    public String getAcc_No() {
        return acc_No;
    }

    public void setAcc_No(String acc_No) {
        this.acc_No = acc_No;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }
}
