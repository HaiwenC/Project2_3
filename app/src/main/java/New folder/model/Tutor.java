package csci310.myapplication.model;

public class Tutor {

    private String username;
    private String name;
    private String password;
    private int totalreview;
    private int reviewNum;

    public Tutor() {
        // Default constructor required for calls to DataSnapshot.getValue(User.class)
    }

    public Tutor(String username, String password, String name, int totalreview, int reviewNum) {
        this.username    = username;
        this.password    = password;
        this.name        = name;
        this.totalreview = totalreview;
        this.reviewNum   = reviewNum;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public void setName(String name) {
        this.name = name;
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

    public int getTotalreview() {
        return totalreview;
    }

    public void setTotalreview(int totalreview) {
        this.totalreview = totalreview;
    }

    public int getReviewNum() {
        return reviewNum;
    }

    public void setReviewNum(int reviewNum) {
        this.reviewNum = reviewNum;
    }
}
