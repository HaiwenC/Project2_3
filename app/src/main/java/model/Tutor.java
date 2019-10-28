package model;

class Tutor extends Student {
    private String teachingExperience;
    private double rating;
    private String reviews;

    public double getRating() {
        return rating;
    }

    public void setRating(double rating) {
        this.rating = rating;
    }

    public String getReviews() {
        return reviews;
    }

    public void setReviews(String reviews) {
        this.reviews = reviews;
    }

    public String getTeachingExperience() {
        return teachingExperience;
    }

    public void setTeachingExperience(String teachingExperience) {
        this.teachingExperience = teachingExperience;
    }
}
