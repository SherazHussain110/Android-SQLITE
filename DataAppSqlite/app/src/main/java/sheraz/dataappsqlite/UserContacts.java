package sheraz.dataappsqlite;

/**
 * Created by AST International on 08-Aug-17.
 */

public class UserContacts
{
    int id;
    String userName;
    String fatherName;
    String phoneNumber;
    String emailAddress;

    //Empty Constructor
    public UserContacts()
    {
    }

    public UserContacts(int id, String userName, String fatherName, String phoneNumber, String emailAddress)
    {
        this.id = id;
        this.userName = userName;
        this.fatherName = fatherName;
        this.phoneNumber = phoneNumber;
        this.emailAddress = emailAddress;
    }

    public UserContacts(String userName, String fatherName, String phoneNumber, String emailAddress)
    {
        this.userName = userName;
        this.fatherName = fatherName;
        this.phoneNumber = phoneNumber;
        this.emailAddress = emailAddress;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getFatherName() {
        return fatherName;
    }

    public void setFatherName(String fatherName) {
        this.fatherName = fatherName;
    }

    public String getPhoneNumber() {
        return phoneNumber;
    }

    public void setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
    }

    public String getEmailAddress() {
        return emailAddress;
    }

    public void setEmailAddress(String emailAddress) {
        this.emailAddress = emailAddress;
    }

    @Override
    public String toString() {
        return
                "ID= " + id + "\n" +
                "Name= " + userName + "\n" +
                "Father Name= " + fatherName + "\n" +
                "Phone Number= " + phoneNumber + "\n" +
                "Email= " + emailAddress + "\n" ;
    }
}
