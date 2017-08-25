package models;

import java.lang.reflect.Array;
import java.util.Arrays;

public class Question {
    private int id;
    private String prompt;

    public Question(String prompt){
        this.prompt = prompt;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Question question = (Question) o;

        if (id != question.id) return false;
        return prompt.equals(question.prompt);
    }

    @Override
    public int hashCode() {
        int result = id;
        result = 31 * result + prompt.hashCode();
        return result;
    }

    public String getPrompt() {
        return prompt;
    }

    public void setPrompt(String prompt) {
        this.prompt = prompt;
    }



    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }
}
