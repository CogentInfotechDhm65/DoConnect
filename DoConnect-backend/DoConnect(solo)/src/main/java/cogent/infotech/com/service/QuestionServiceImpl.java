package cogent.infotech.com.service;

import cogent.infotech.com.entity.Answer;
import cogent.infotech.com.entity.Question;
import cogent.infotech.com.entity.User;
import cogent.infotech.com.repository.QuestionRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class QuestionServiceImpl implements QuestionService{

	@Autowired
	private QuestionRepository questionRepository;
	
	@Autowired
	private EmailService emailService;
	
	@Autowired
	private UserServiceImpl userService;

	

	@Override
	public List<Question> getAllQuestions() {
		return (List)questionRepository.findAll();
	}
	
	@Override
	public void addQuestion(Question question) {
		
        emailService.sendEmail("cogentcapstonetesting@gmail.com", "new question waiting to be approve", question.getTitle() + " "+ question.getDescription_question());
		questionRepository.save(question);
	}

	@Override
	public void deleteQuestionById(int id) {
		questionRepository.deleteById(id);
	}
	
	@Override
	public Question getQuestionById(int id) {
		return questionRepository.findById(id);
	}
	
	@Override
	public List<Question> getAllQuestionsByTopic(String topic) {
		return (List)questionRepository.findByTopic(topic);
	}

	@Override
	public List<Question> getAllPendingQuestions() {
		return questionRepository.findAllPending();
	}

	@Override
	public List<Question> getAllApprovedQuestions() {
		return questionRepository.findAllApproved();
	}

	@Override
	public void approveQuestion(int adminId, Question question) {
		Question questionToApprove = questionRepository.findById(question.getId());
		questionToApprove.setStatus("approved");
		questionToApprove.setQapproved_by(userService.getUserById(adminId));
		questionRepository.save(questionToApprove);
	}
	@Override
	public List<Question> searchQuestion(String q) {
		return questionRepository.findByDescriptionQuestionContaining(q, "approved");
	}
	@Override
	public void denyQuestion(Question question) {
		Question questionToApprove = questionRepository.findById(question.getId());
		questionToApprove.setStatus("denied");
		questionRepository.save(questionToApprove);
	}

	@Override
	public void updateAnswers(Question question) {
		Question questionToUpdate = questionRepository.findById(question.getId());
		questionToUpdate.setAnswers(question.getAnswers());
		questionRepository.save(questionToUpdate);
	}

	@Override
	public List<Answer> getAllAnswersForQuestion(int question_id) {
		return questionRepository.findById(question_id).getAnswers();
	}
}