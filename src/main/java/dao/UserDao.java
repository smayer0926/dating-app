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
    List<Question> getAllQuestionsAnsweredByUser(int userId);

    //update
     void update(int id, String newName, int newAge, String newGender, String newGenderPreference, int newMinAge, int newMaxAge, String newZip, String newEmail, String newPassword);


    public User getUser(String email);
        //find
    User findById(int id);

    //find matching user
    int countNumberOfUserIdMatches(int userId);
}
