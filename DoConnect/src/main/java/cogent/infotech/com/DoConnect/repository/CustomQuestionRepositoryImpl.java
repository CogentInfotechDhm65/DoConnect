package cogent.infotech.com.DoConnect.repository;

import cogent.infotech.com.DoConnect.entity.Question;
import org.springframework.beans.factory.annotation.Autowired;

import javax.persistence.EntityManager;
import javax.persistence.TypedQuery;
import java.util.List;

public class CustomQuestionRepositoryImpl implements CustomQuestionRepository {

    @Autowired
    private EntityManager entityManager;

    @Override
    public List<Question> findAllByText(String text) {
        String sql = "Select q From Questions q Where q.title Like :text OR q.description_question Like :text";
        final TypedQuery<Question> query = entityManager.createQuery(sql, Question.class);
        query.setParameter("text", text);
        return query.getResultList();
    }

    @Override
    public List<Question> findAllByTopic(String topic) {
        String sql = "Select q From Questions q Where q.topic = :topic";
        final TypedQuery<Question> query = entityManager.createQuery(sql, Question.class);
        query.setParameter("topic", topic);
        return query.getResultList();
    }

    @Override
    public Question findByDescription(String description) {
        String sql = "Select q From Questions q Where q.description_question = :description";
        final TypedQuery<Question> query = entityManager.createQuery(sql, Question.class);
        query.setParameter("description", description);
        return query.getSingleResult();
    }
}
