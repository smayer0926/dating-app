package dao;

import models.Question;
import models.User;

import java.util.List;

public interface QuestionDao {
    //create
    void add (Question question);

    void addQuestionToUser(User user, Question question);

    //get all
    List<Question> getAllUnanswered(int userId);

//    List<QuestionOption> getAllForSpecificQuestion(int questionId);
//    List<Question> getAllAnswered();
//    List<Question> getAllUnanswered();
//    List<User> getAllUsersThatAnsweredQuestion(int questionId);

    //find
    Question findById(int id);

    //count id matches
//    int countNumberOfQuestionIdMatches(int questionId);

    //delete by id
    void deleteById(int id);

    void addUsertoUsersWhoHaveAnsweredThisQuestion (int userId, Question question);
}
