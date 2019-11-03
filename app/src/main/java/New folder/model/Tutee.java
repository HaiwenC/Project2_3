package csci310.myapplication.model;

public class Tutee {

    private String username;
    private String name;
    private int role;
    private String password;

    public Tutee() {
        // Default constructor required for calls to DataSnapshot.getValue(User.class)
    }

    public Tutee(String username, String password, String name, int role) {
        this.username = username;
        this.password = password;
        this.name     = name;
        this.role     = role;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setRole(int role) {
        this.role = role;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getUsername() {
        return username;
    }

    public String getPassword() {
        return password;
    }

    public String getName() {
        return name;
    }

    public int getRole() {
        return role;
    }

}
