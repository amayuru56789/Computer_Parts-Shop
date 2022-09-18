package model;

public class SignUp {
    private String userName;
    private String gmail;
    private String contact;
    private String password;
    private String conPass;

    public SignUp() {
    }

    public SignUp(String userName, String gmail, String contact, String password, String conPass) {
        this.setUserName(userName);
        this.setGmail(gmail);
        this.setContact(contact);
        this.setPassword(password);
        this.setConPass(conPass);
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getGmail() {
        return gmail;
    }

    public void setGmail(String gmail) {
        this.gmail = gmail;
    }

    public String getContact() {
        return contact;
    }

    public void setContact(String contact) {
        this.contact = contact;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getConPass() {
        return conPass;
    }

    public void setConPass(String conPass) {
        this.conPass = conPass;
    }

    @Override
    public String toString() {
        return "SignUp{" +
                "userName='" + userName + '\'' +
                ", gmail='" + gmail + '\'' +
                ", contact='" + contact + '\'' +
                ", password='" + password + '\'' +
                ", conPass='" + conPass + '\'' +
                '}';
    }
}
