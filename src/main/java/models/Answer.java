package models;


public class Answer {
    private int id;
    private int userId;
    private int questionId;
    private String answer;
    private String acceptableAnswer;

    public Answer(int userId, int questionId, String answer, String acceptableAnswer){
        this.userId = userId;
        this.questionId = questionId;
        this.answer = answer;
        this.acceptableAnswer = acceptableAnswer;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Answer answer = (Answer) o;

        if (getId() != answer.getId()) return false;
        if (getUserId() != answer.getUserId()) return false;
        if (getQuestionId() != answer.getQuestionId()) return false;
        if (!getAnswer().equals(answer.getAnswer())) return false;
        return getAcceptableAnswer().equals(answer.getAcceptableAnswer());
    }

    @Override
    public int hashCode() {
        int result = getId();
        result = 31 * result + getUserId();
        result = 31 * result + getQuestionId();
        result = 31 * result + getAnswer().hashCode();
        result = 31 * result + getAcceptableAnswer().hashCode();
        return result;
    }


    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
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

    public String getAnswer() {
        return answer;
    }

    public void setAnswer(String answer) {
        this.answer = answer;
    }

    public String getAcceptableAnswer() {
        return acceptableAnswer;
    }

    public void setAcceptableResponse(String acceptableAnswer) {
        this.acceptableAnswer = acceptableAnswer;
    }
}
