package dao;

import models.QuestionOption;

import java.util.List;

/**
 * Created by Guest on 8/28/17.
 */
public interface QuestionOptionDao {

    void add (QuestionOption questionOption);

    List<QuestionOption> getAll();

    List<QuestionOption> getAllForSpecificQuestion(int questionId);

}
