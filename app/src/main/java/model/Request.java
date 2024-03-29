package model;

public class Request {
    private String tutee;
    private String tutor;
    private String subject;
    private int dayOfWeek;
    private int time;
//    private int index;
    private String status;
    private String tutorEmail;
    private String tuteeEmail;

    public Request(String tutee, String tutor, String className, int day ,int begin, String tutorEmail, String tuteeEmail){
        this.tutee = tutee;
        this.tutor = tutor;
        this.subject = className;
        this.dayOfWeek = day;
        this.time = begin;
        this.tutorEmail = tutorEmail;
        this.tuteeEmail = tuteeEmail;
//        this.index = ind;

        //possible status: 1:"accepted" 2:"rejected" 3:"available"
        this.status = "available";

    }

    public Request(Tutee tutee, Tutor t, String s, Integer integer, Integer integer1, int i) {
    }

    public String getTutorEmail() {
        return tutorEmail;
    }

    public void setTutorEmail(String tutorEmail) {
        this.tutorEmail = tutorEmail;
    }

    public String getTuteeEmail() {
        return tuteeEmail;
    }

    public void setTuteeEmail(String tuteeEmail) {
        this.tuteeEmail = tuteeEmail;
    }

    public void acceptRequest(String newStatus) {
        status = newStatus;
//        Session newSession = new Sections(timeBegin, timeEnd);
        Session newSession = new Session(tutor, tutee, subject, dayOfWeek, time, tutorEmail, tuteeEmail);
//        tutee.addSession(newSession);
//        tutor.addSections(newSession, dayOfWeek, timeBegin);
        status = "accepted";
        //TODO add session to database
    }

    public void rejectRequest(){
        status = "rejected";
    }

    public int getDayOfWeek(){
        return dayOfWeek;
    }

    public int getTime(){
        return time;
    }

    public String getTutor() {
        return tutor;
    }
    public String getTutee() {return tutee;}
    public String getSubject(){return this.subject;}

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }
}
