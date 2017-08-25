package models;

import java.util.Arrays;

public class Question {
    private int id;
    private String prompt;
    private String[] choices;

    public Question(String prompt, String[] choices){
        this.prompt = prompt;
        this.choices = choices;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Question question = (Question) o;

        if (id != question.id) return false;
        if (!prompt.equals(question.prompt)) return false;
        // Probably incorrect - comparing Object[] arrays with Arrays.equals
        return Arrays.equals(choices, question.choices);
    }

    @Override
    public int hashCode() {
        int result = id;
        result = 31 * result + prompt.hashCode();
        result = 31 * result + Arrays.hashCode(choices);
        return result;
    }

    public String getPrompt() {
        return prompt;
    }

    public void setPrompt(String prompt) {
        this.prompt = prompt;
    }

    public String[] getChoices() {
        return choices;
    }

    public void setChoices(String[] choices) {
        this.choices = choices;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }
}
