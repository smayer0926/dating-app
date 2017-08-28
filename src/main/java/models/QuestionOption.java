package models;


public class QuestionOption {
    private int id;
    private String choice;
    private int questionId;

    public QuestionOption(String choice, int questionId){
        this.choice = choice;
        this.questionId = questionId;
    }


    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        QuestionOption that = (QuestionOption) o;

        if (id != that.id) return false;
        if (questionId != that.questionId) return false;
        return choice.equals(that.choice);
    }

    @Override
    public int hashCode() {
        int result = id;
        result = 31 * result + choice.hashCode();
        result = 31 * result + questionId;
        return result;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getChoice() {
        return choice;
    }

    public void setChoice(String choice) {
        this.choice = choice;
    }

    public int getQuestionId() {
        return questionId;
    }

    public void setQuestionId(int questionId) {
        this.questionId = questionId;
    }
}
