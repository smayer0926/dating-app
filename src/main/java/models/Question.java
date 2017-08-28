package models;

import java.lang.reflect.Array;
import java.util.Arrays;

public class Question {
    private int id;
    private String prompt;
    private String choice1;
    private String choice2;
    private String choice3;
    private String choice4;

    public Question(String prompt, String choice1, String choice2, String choice3, String choice4){
        this.prompt = prompt;
        this.choice1 = choice1;
        this.choice2 = choice2;
        this.choice3 = choice3;
        this.choice4 = choice4;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Question question = (Question) o;

        if (id != question.id) return false;
        if (!prompt.equals(question.prompt)) return false;
        if (!choice1.equals(question.choice1)) return false;
        if (!choice2.equals(question.choice2)) return false;
        if (choice3 != null ? !choice3.equals(question.choice3) : question.choice3 != null) return false;
        return choice4 != null ? choice4.equals(question.choice4) : question.choice4 == null;
    }

    @Override
    public int hashCode() {
        int result = id;
        result = 31 * result + prompt.hashCode();
        result = 31 * result + choice1.hashCode();
        result = 31 * result + choice2.hashCode();
        result = 31 * result + (choice3 != null ? choice3.hashCode() : 0);
        result = 31 * result + (choice4 != null ? choice4.hashCode() : 0);
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
}
