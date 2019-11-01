package model;

public class Request {
    private String tutee_name;
    private String tutor_name;
    private String subject;
    private String timeBegin;
    private String timeEnd;
    private boolean accepted;

    public Request(String tutee, String tutor, String className, String begin, String end){
        tutee_name = tutee;
        tutor_name = tutor;
        subject = className;
        timeBegin = begin;
        timeEnd = end;
        accepted = false;
    }

    public void acceptRequest() {
        accepted = true;
//        Sections newSession = new Sections(timeBegin, timeEnd);

        //TODO add session to database
    }

}
