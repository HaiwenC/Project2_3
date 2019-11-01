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

    public Request(Tutee tutee, Tutor t, String s, Integer integer, Integer integer1, int i) {
    }

    public void acceptRequest(String newStatus) {
        status = newStatus;
//        Session newSession = new Sections(timeBegin, timeEnd);
        Session newSession;
        tutee.addSession(newSession);
        tutor.addSections(newSession);
        //TODO add session to database
    }


    public Tutor getTutor() {
        return tutor;
    }


}
