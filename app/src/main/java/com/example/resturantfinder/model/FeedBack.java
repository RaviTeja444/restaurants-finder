package com.example.resturantfinder.model;

public class FeedBack {
    private String feedbackRating;
    private String  feedbackReview;
    private String feedbackSuggestion;

    public FeedBack() {
    }

    public String getFeedbackRating() {
        return feedbackRating;
    }

    public void setFeedbackRating(String feedbackRating) {
        this.feedbackRating = feedbackRating;
    }

    public String getFeedbackReview() {
        return feedbackReview;
    }

    public void setFeedbackReview(String feedbackReview) {
        this.feedbackReview = feedbackReview;
    }

    public String getFeedbackSuggestion() {
        return feedbackSuggestion;
    }

    public void setFeedbackSuggestion(String feedbackSuggestion) {
        this.feedbackSuggestion = feedbackSuggestion;
    }

    public FeedBack(String feedbackRating, String feedbackReview, String feedbackSuggestion) {
        this.feedbackRating = feedbackRating;
        this.feedbackReview = feedbackReview;
        this.feedbackSuggestion = feedbackSuggestion;
    }
}
