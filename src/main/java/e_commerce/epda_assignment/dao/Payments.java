package e_commerce.epda_assignment.dao;

import e_commerce.epda_assignment.model.Payment;
import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityManagerFactory;
import jakarta.persistence.EntityTransaction;
import jakarta.persistence.criteria.CriteriaBuilder;
import jakarta.persistence.criteria.CriteriaQuery;
import jakarta.persistence.criteria.CriteriaUpdate;
import jakarta.persistence.criteria.Root;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class Payments implements Payment_I {
    @Autowired
    @Qualifier("emf")
    private EntityManagerFactory emf;


    public boolean create(Payment input) {
        EntityManager em = emf.createEntityManager();
        EntityTransaction transaction = em.getTransaction();
        try {
            transaction.begin();
            em.merge(input);
            transaction.commit();
            return true;
        } catch (Exception e) {
            if (transaction.isActive()) {
                transaction.rollback();
            }
            return false;
        }
    }
    public Payment update(Payment input) {
        EntityManager em = emf.createEntityManager();
        EntityTransaction transaction = em.getTransaction();
        try {
            transaction.begin();
            Payment payment= em.merge(input);
            transaction.commit();
            return payment;
        } catch (Exception e) {
            if (transaction.isActive()) {
                transaction.rollback();
            }
            return null;
        }
    }
    public boolean updateMultiple (List<Payment> list, String updateField, String updateValue) {
        EntityManager em = emf.createEntityManager();
        EntityTransaction transaction = em.getTransaction();
        try {
            transaction.begin();
            CriteriaBuilder criteriaBuilder = em.getCriteriaBuilder();
            CriteriaUpdate<Payment> update = criteriaBuilder.createCriteriaUpdate(Payment.class);
            Root<Payment> root = update.from(Payment.class);
            update.set(root.get(updateField), updateValue);
            em.createQuery(update).executeUpdate();
            em.getTransaction().commit();
            return true;
        }
        catch (Exception e){
            em.getTransaction().rollback();
            return false;
        }
    }
    public boolean delete(Payment input){
        EntityManager em = emf.createEntityManager();
        EntityTransaction transaction = em.getTransaction();
        try {
            transaction.begin();
            em.remove(input);
            transaction.commit();
            return true;
        } catch (Exception e) {
            if (transaction.isActive()) {
                transaction.rollback();
            }
            return false;
        }
    }
    public Payment getPaymentbyId(String id) {
        EntityManager em = emf.createEntityManager();
        return em.find(Payment.class, id);
    }

    public List<Payment> getAllPayment (){
        EntityManager em = emf.createEntityManager();
        CriteriaBuilder cb = em.getCriteriaBuilder();
        CriteriaQuery<Payment> cq = cb.createQuery(Payment.class);
        Root<Payment> rootEntry = cq.from(Payment.class);
        CriteriaQuery<Payment> all = cq.select(rootEntry);
        return em.createQuery(all).getResultList();
    }
    public List<Payment> findByConditionEqual (String whereField, Object whereValue){
        EntityManager em = emf.createEntityManager();
        CriteriaBuilder cb = em.getCriteriaBuilder();
        CriteriaQuery<Payment> cq = cb.createQuery(Payment.class);
        Root<Payment> rootEntry = cq.from(Payment.class);
        cq.where(cb.equal(rootEntry.get(whereField),whereValue));
        return em.createQuery(cq).getResultList();
    }
}
