package dao;

import models.Question;
import models.User;

import java.util.List;

public interface UserDao {
    //create
    void add (User user);

    void addUserToQuestion(User user, Question question);

    //get all
    List<User> getAll();
    List<User> getAllMatches(int minAge, int maxAge);
    List<Question> getAllQuestionsAnsweredByUser(int userId);

    //find
    User findById(int id);

    //find matching user
    int countNumberOfUserIdMatches(int userId);
}
