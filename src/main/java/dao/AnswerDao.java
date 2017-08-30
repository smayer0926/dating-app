package dao;


import models.Answer;
import models.Question;

import java.util.List;

public interface AnswerDao {
    void add (Answer answer);

    List<Answer> getAllForAnswersSpecificUser(int userId);

    List<Integer> getQuestionIdsFromUsersAnsweredQuestions (int userId);

    void setAnswerBooleans (Question question, String answer);
}
