package models;

public class Question {
    private int id;
    private String prompt;
    private String choice1;
    private String choice2;
    private String choice3;
    private String choice4;
    private boolean answerIs1;
    private boolean answerIs2;
    private boolean answerIs3;
    private boolean answerIs4;
    private String usersWhoHaveAnswered;


    public Question(String prompt, String choice1, String choice2, String choice3, String choice4, boolean answerIs1, boolean answerIs2, boolean answerIs3, boolean answerIs4, String usersWhoHaveAnswered){
        this.prompt = prompt;
        this.choice1 = choice1;
        this.choice2 = choice2;
        this.choice3 = choice3;
        this.choice4 = choice4;
        this.setAnswerIs1(false);
        this.setAnswerIs2(false);
        this.setAnswerIs3(false);
        this.setAnswerIs4(false);
        this.setUsersWhoHaveAnswered("");
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Question question = (Question) o;

        if (id != question.id) return false;
        if (answerIs1 != question.answerIs1) return false;
        if (answerIs2 != question.answerIs2) return false;
        if (answerIs3 != question.answerIs3) return false;
        if (answerIs4 != question.answerIs4) return false;
        if (!prompt.equals(question.prompt)) return false;
        if (!choice1.equals(question.choice1)) return false;
        if (!choice2.equals(question.choice2)) return false;
        if (!choice3.equals(question.choice3)) return false;
        if (!choice4.equals(question.choice4)) return false;
        return getUsersWhoHaveAnswered() != null ? getUsersWhoHaveAnswered().equals(question.getUsersWhoHaveAnswered()) : question.getUsersWhoHaveAnswered() == null;
    }

    @Override
    public int hashCode() {
        int result = id;
        result = 31 * result + prompt.hashCode();
        result = 31 * result + choice1.hashCode();
        result = 31 * result + choice2.hashCode();
        result = 31 * result + choice3.hashCode();
        result = 31 * result + choice4.hashCode();
        result = 31 * result + (answerIs1 ? 1 : 0);
        result = 31 * result + (answerIs2 ? 1 : 0);
        result = 31 * result + (answerIs3 ? 1 : 0);
        result = 31 * result + (answerIs4 ? 1 : 0);
        result = 31 * result + (getUsersWhoHaveAnswered() != null ? getUsersWhoHaveAnswered().hashCode() : 0);
        return result;
    }

    public String getPrompt() {
        return prompt;
    }

    public void setPrompt(String prompt) {
        this.prompt = prompt;
    }

    public String getChoice1() {
        return choice1;
    }

    public void setChoice1(String choice1) {
        this.choice1 = choice1;
    }

    public String getChoice2() {
        return choice2;
    }

    public void setChoice2(String choice2) {
        this.choice2 = choice2;
    }

    public String getChoice3() {
        return choice3;
    }

    public void setChoice3(String choice3) {
        this.choice3 = choice3;
    }

    public String getChoice4() {
        return choice4;
    }

    public void setChoice4(String choice4) {
        this.choice4 = choice4;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public boolean getAnswerIs1() {
        return answerIs1;
    }

    public void setAnswerIs1(boolean answerIs1) {
        this.answerIs1 = answerIs1;
    }

    public boolean isAnswerIs2() {
        return answerIs2;
    }

    public void setAnswerIs2(boolean answerIs2) {
        this.answerIs2 = answerIs2;
    }

    public boolean isAnswerIs3() {
        return answerIs3;
    }

    public void setAnswerIs3(boolean answerIs3) {
        this.answerIs3 = answerIs3;
    }

    public boolean isAnswerIs4() {
        return answerIs4;
    }

    public void setAnswerIs4(boolean answerIs4) {
        this.answerIs4 = answerIs4;
    }

    public String getUsersWhoHaveAnswered() {
        return usersWhoHaveAnswered;
    }

    public void setUsersWhoHaveAnswered(String usersWhoHaveAnswered) {
        this.usersWhoHaveAnswered = usersWhoHaveAnswered;
    }
}
