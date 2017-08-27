package dao;

import models.Question;
import models.User;
import org.sql2o.Connection;
import org.sql2o.Sql2oException;

public interface QuestionResponseDao {


    void addQuestionToUser(int userId, int questionId, String questionResponse);
}
