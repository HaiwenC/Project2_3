package model;

public class Request {
    private Tutee tutee;
    private Tutor tutor;
    private String subject;
    private int day;
    private int time;
//    private int index;
    private String status;

    public Request(Tutee tutee, Tutor tutor, String className, int day ,int begin, int ind){
        this.tutee = tutee;
        this.tutor = tutor;
        this.subject = className;
        this.day = day;
        this.time = begin;
//        this.index = ind;

        //possible status: 1:"accepted" 2:"rejected" 3:"available"
        this.status = "available";

    }

    public Request(Tutee tutee, Tutor t, String s, Integer integer, Integer integer1, int i) {
    }

    public void acceptRequest(String newStatus) {
        status = newStatus;
//        Session newSession = new Sections(timeBegin, timeEnd);
        Session newSession = new Session(tutor.getName(), tutee.getName(), subject, day, time);
//        tutee.addSession(newSession);
//        tutor.addSections(newSession, dayOfWeek, timeBegin);
        status = "accepted";
        //TODO add session to database
    }

    public void rejectRequest(){
        status = "rejected";
    }

    public int getDayOfWeek(){
        return day;
    }

    public int getTime(){
        return time;
    }

    public Tutor getTutor() {
        return tutor;
    }
    public Tutee getTutee() {return tutee;}
    public String getSubject(){return this.subject;}


}
