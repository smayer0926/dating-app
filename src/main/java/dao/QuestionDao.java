package dao;

import models.Answer;
import models.Question;
import models.User;

import java.util.List;

public interface QuestionDao {
    //create
    void add (Question question);

    void addQuestionToUser(User user, Question question);

    List<Question> getAll();

//    List<QuestionOption> getAllForSpecificQuestion(int questionId);

//    List<User> getAllUsersThatAnsweredQuestion(int questionId);

    //find
    Question findById(int id);

    //count id matches
//    int countNumberOfQuestionIdMatches(int questionId);

    //delete by id
    void deleteById(int id);

    List<Question> getAllUnanswered(int userId, List<Answer>allAnswers);

    String addUserToUsersWhoHaveAnsweredThisQuestion (int userId, Question question);

    void addUserToUsersWhoHaveAnsweredThisQuestion2 (int userId, Question question, int questionId, String usersWhoHaveAnswered);
}
