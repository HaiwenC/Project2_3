package model;

public class Request {
    private String tutee_name;
    private String tutor_name;
    private boolean accepted;

    Request(String tutee, String tutor){
        tutee_name = tutee;
        tutor_name = tutor;
        accepted = false;
    }

    public void acceptRequest(){
        accepted = true;
    }


}
