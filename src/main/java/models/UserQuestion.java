package models;

public class UserQuestion {
    private int userId;
    private int questionId;
    private int userResponse;



    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        UserQuestion that = (UserQuestion) o;

        if (userId != that.userId) return false;
        if (questionId != that.questionId) return false;
        return userResponse == that.userResponse;
    }

    @Override
    public int hashCode() {
        int result = userId;
        result = 31 * result + questionId;
        result = 31 * result + userResponse;
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

    public int getUserResponse() {
        return userResponse;
    }

    public void setUserResponse(int userResponse) {
        this.userResponse = userResponse;
    }
}
