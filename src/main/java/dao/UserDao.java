package dao;

import models.Answer;
import models.Question;
import models.User;

import java.util.List;

public interface UserDao {
    //create
    void add (User user);

    void addUserToQuestion(User user, Question question);

    //get all
    List<User> getAll();

    List<User> getAllMatches(int userId, int minAge, int maxAge, String genderPreference);

//    List<Integer> getQuestionIdOfAnswersForSpecificUser(int userId);


    List<Answer> getAllAnswers(int userId);

    //update
     void update(int id, String newName, int newAge, String newGender, String newGenderPreference, int newMinAge, int newMaxAge, String newZip, String newEmail, String newPassword);


    public User getUser(String email);
        //find
    User findById(int id);
    Answer findAnswerByQuestionId(int questionId, int userId);

    //find matching user
    int countNumberOfUserIdMatches(int userId);

    int evaluateCompatibility(List<Integer> user1QuestionIds, List<Answer> user2Answers, int userId);
}
