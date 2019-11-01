package model;
import java.util.ArrayList;
import java.util.Calendar;

class Tutee extends Student {
    private ArrayList<Sections> sectionsLearning;

    public Tutee(String studentID, String email, String name, String username) {
        super(studentID, email, name, username);
        sectionsLearning = new ArrayList<Sections>();
    }


    //pulls data from database, search the list by time, and return a list of matching results
    private ArrayList<Calendar> SearchTutor(String timeBegin, String timeEnd){
        //TODO pull tutor's availability
        return null;

    }

    //creates and returns a request object
    private Request createRequest(String timeBegin, String timeEnd, String tutorName, String className){
        Request add = new Request(getName(), tutorName, className ,timeBegin, timeEnd);
        return add;
    }

    private void writeReview(String tutorName, String review, int rating){
        /*if tutor exists,
          add reviews and ratings to the reviews list of the corresponding tutor
         */
    }
}
