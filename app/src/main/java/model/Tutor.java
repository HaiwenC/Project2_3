package model;


import java.util.ArrayList;
import java.util.*;
// @Philip since we know what all known sections will be in our dropdown,
// let's use enum to keep a range of constants and just add/delete from this list
enum Sections {
    CSCI109, CSCI102, CSCI103, CSCI104, CSCI201, CSCI270,CSCI310,CSCI356, CSCI350, CSCI360,
    EE109, EE250, EE364;
}


class Tutor extends Student {
    private ArrayList<Sections> sectionsTeaching;
    //private double rating;  // @Philip won't this variable just be an average that's computed from DB?
                            // so we shouldn't necessarily keep it as a local private variable
    //private String reviews; // same as above, what reviews are we keeping in class?
                            // can't they just be stored/retrieved from DB?

    public Tutor(String studentID, String email) {
        super(studentID, email);
        sectionsTeaching = new ArrayList<Sections>();
    }
    public boolean addSections(Sections s) {
        if (sectionsTeaching.contains(s)) return false;
        else sectionsTeaching.add(s);
        return true;
    }
    public boolean removeSections(Sections s) {
       return sectionsTeaching.remove(s);
        // returns true if removed, false otherwise
    }


    public double getRating() {

        double rating = 0;
        // perform sql command AVG to retrieve
        // the average rating of this tutor from db
        // if rating does not exist, should we return a flag that will display N/A?
        return rating;
    }


    public List<String> getReviews() {
        ArrayList<String> reviews = new ArrayList<String>();
        // perform sql command to retrieve all reviews into resultset
        // and store that list in result set to this reviews arraylist
        // On the other hand, should this not be a string, but Review objects
        // because reviews will also hold session info like rating, student, subject, etc.
        return reviews;
    }

}
