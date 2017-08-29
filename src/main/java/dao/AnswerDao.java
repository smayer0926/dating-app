package dao;


import models.Answer;
import java.util.List;

public interface AnswerDao {
    void add (Answer answer);

    List<Answer> getAllForAnswersSpecificUser(int userId);

}
