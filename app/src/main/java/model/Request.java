package model;

public class Request {
    private Tutee tutee;
    private Tutor tutor;
    private String subject;
    private int timeBegin;
    private int index;
    private String status;

    public Request(Tutee tutee, Tutor tutor, String className, int begin, int ind){
        this.tutee = tutee;
        this.tutor = tutor;
        this.subject = className;
        this.timeBegin = begin;
        this.index = ind;
        this.status = "available";
    }

    public void acceptRequest(String newStatus) {
        status = newStatus;
//        Sections newSession = new Sections(timeBegin, timeEnd);

        //TODO add session to database
    }

}
