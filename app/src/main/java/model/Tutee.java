package model;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.HashMap;
import java.util.Map;

public class Tutee extends Student {
    private ArrayList<Session> sectionsLearning;
    private String lastSearch_subject;
    private int lastSearch_day;
    private int lastSearch_time;

    public Tutee(String studentID, String email, String name, String username) {
        super(studentID, email, name, username);
        sectionsLearning = new ArrayList<Session>();
        lastSearch_subject = "";
        lastSearch_day = -1;
        lastSearch_time = -1;
    }


    //pulls data from database, search the list by time, and return a list of matching results
    private ArrayList<Calendar> SearchTutor(String timeBegin, String timeEnd){
        //TODO pull tutor's availability
        return null;

    }

    public void addSession(Session newClass){
        sectionsLearning.add(newClass);
    }

    //when the send request button is clicked on a request
    //creates and returns a request object
    private void createRequest(Request selectedRequest){
//        Request add = new Request(getName(), tutorName, className ,timeBegin, timeEnd);
        ArrayList<Request> updated = selectedRequest.getTutor().getRequestsReceived();
        updated.add(selectedRequest);
        selectedRequest.getTutor().setRequestsReceived(updated);
    }

    private void writeReview(String tutorName, String review, int rating){
        /*if tutor exists,
          add reviews and ratings to the reviews list of the corresponding tutor
         */

    }

    //tutee can only search one hour
    public ArrayList<Request> getResults(String subject, int day, int beginTime, ArrayList<Tutor> tutors){

        if (lastSearch_subject.equals("") || lastSearch_day == -1 || lastSearch_time == -1)
        {
            return null;
        }
        ArrayList<Request> results = new ArrayList<Request>();
        for(Tutor t : tutors){
            ArrayList<String> subjects = t.getSubject();
            ArrayList<Integer> days = t.getDay();
            ArrayList<Integer> times = t.getTime();

            for (int i = 0; i<subjects.size();i++){
                if (subjects.get(i).equalsIgnoreCase((subject))
                    && days.get(i) == day
                    && times.get(i) == beginTime)
                {
                    //add to database
                    results.add(new Request(this, t, subjects.get(i), days.get(i), times.get(i),i));
                }
            }
        }

        lastSearch_subject = subject;
        lastSearch_day = day;
        lastSearch_time = beginTime;
        return results;
    }
}
