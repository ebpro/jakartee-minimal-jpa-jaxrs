package fr.univtln.bruno.samples.jakartaee.jpajaxrs.repository;

import jakarta.enterprise.context.ApplicationScoped;
import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import jakarta.transaction.Transactional;

import java.util.List;
import java.util.UUID;

import static jakarta.transaction.Transactional.TxType.REQUIRED;

@ApplicationScoped
public class MessageDAO {

    @PersistenceContext(unitName = "MainPU")
    private EntityManager em;

    @Transactional(REQUIRED)
    public void create(Message message) {
        em.persist(message);
    }

    public Message find(UUID id) {
        return em.find(Message.class, id);
    }

    public List<Message> findAll() {
        return em.createNamedQuery("Message.findAll", Message.class).getResultList();
    }

    @Transactional(REQUIRED)
    public Message update(Message message) {
        Message last=em.find(Message.class, message.getId());
        message.setCreationTime(last.getCreationTime());
        message.setLastModifiedTime(last.getLastModifiedTime());
        return em.merge(message);
    }

    @Transactional(REQUIRED)
    public void delete(Message message) {
        em.remove(em.merge(message));
    }
}
