package dao;

import models.Question;
import models.User;

import java.util.List;

public interface QuestionDao {
    //create
    void add (Question question);

    void addQuestionToUser(User user, Question question);

    //get all
    List<Question> getAll();
    List<User> getAllUsersThatAnsweredQuestion(int userId);

    //find
    Question findById(int id);
}
