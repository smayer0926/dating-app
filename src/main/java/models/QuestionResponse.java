package models;

public class QuestionResponse {
    private int userId;
    private int questionId;
    private String userResponse;

    public QuestionResponse (String userResponse){
        this.setUserResponse(userResponse);
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        QuestionResponse that = (QuestionResponse) o;

        if (userId != that.userId) return false;
        if (questionId != that.questionId) return false;
        return userResponse.equals(that.userResponse);
    }

    @Override
    public int hashCode() {
        int result = userId;
        result = 31 * result + questionId;
        result = 31 * result + userResponse.hashCode();
        return result;
    }

    public int getUserId() {
        return userId;
    }

    public void setUserId(int userId) {
        this.userId = userId;
    }

    public int getQuestionId() {
        return questionId;
    }

    public void setQuestionId(int questionId) {
        this.questionId = questionId;
    }

    public String getUserResponse() {
        return userResponse;
    }

    public void setUserResponse(String userResponse) {
        this.userResponse = userResponse;
    }
}
