package models;


public class Answer {
    private int id;
    private int userId;
    private int questionId;
    private String answer;
    private String acceptableAnswer;

    public Answer(int userId, int questionId, String answer, String acceptableAnswer) {
        this.userId = userId;
        this.questionId = questionId;
        this.answer = answer;
        this.acceptableAnswer = acceptableAnswer;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Answer answer1 = (Answer) o;

        if (id != answer1.id) return false;
        if (userId != answer1.userId) return false;
        if (questionId != answer1.questionId) return false;
        if (!answer.equals(answer1.answer)) return false;
        return acceptableAnswer != null ? acceptableAnswer.equals(answer1.acceptableAnswer) : answer1.acceptableAnswer == null;
    }

    @Override
    public int hashCode() {
        int result = id;
        result = 31 * result + userId;
        result = 31 * result + questionId;
        result = 31 * result + answer.hashCode();
        result = 31 * result + (acceptableAnswer != null ? acceptableAnswer.hashCode() : 0);
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
