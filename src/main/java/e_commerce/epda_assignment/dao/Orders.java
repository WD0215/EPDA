package e_commerce.epda_assignment.dao;

import e_commerce.epda_assignment.model.Order;
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
public class Orders implements Order_I{
    @Autowired
    @Qualifier("emf")
    private EntityManagerFactory emf;


    public Order create(Order input) {
        EntityManager em = emf.createEntityManager();
        EntityTransaction transaction = em.getTransaction();
        try {
            transaction.begin();
            Order order = em.merge(input);
            transaction.commit();
            return order;
        } catch (Exception e) {
            if (transaction.isActive()) {
                transaction.rollback();
            }
            return null;
        }
    }
    public Order update(Order input) {
        EntityManager em = emf.createEntityManager();
        EntityTransaction transaction = em.getTransaction();
        try {
            transaction.begin();
            Order order = em.merge(input);
            transaction.commit();
            return order;
        } catch (Exception e) {
            if (transaction.isActive()) {
                transaction.rollback();
            }
            return null;
        }
    }
    public boolean updateMultiple (List<Order> list, String updateField, String updateValue) {
        EntityManager em = emf.createEntityManager();
        EntityTransaction transaction = em.getTransaction();
        try {
            transaction.begin();
            CriteriaBuilder criteriaBuilder = em.getCriteriaBuilder();
            CriteriaUpdate<Order> update = criteriaBuilder.createCriteriaUpdate(Order.class);
            Root<Order> root = update.from(Order.class);
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
    public boolean delete(Order input){
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
    public Order getOrderbyId(String id) {
        EntityManager em = emf.createEntityManager();
        return em.find(Order.class, id);
    }

    public List<Order> getAllOrder (){
        EntityManager em = emf.createEntityManager();
        CriteriaBuilder cb = em.getCriteriaBuilder();
        CriteriaQuery<Order> cq = cb.createQuery(Order.class);
        Root<Order> rootEntry = cq.from(Order.class);
        CriteriaQuery<Order> all = cq.select(rootEntry);
        return em.createQuery(all).getResultList();
    }
    public List<Order> findByConditionEqual (String whereField, Object whereValue){
        EntityManager em = emf.createEntityManager();
        CriteriaBuilder cb = em.getCriteriaBuilder();
        CriteriaQuery<Order> cq = cb.createQuery(Order.class);
        Root<Order> rootEntry = cq.from(Order.class);
        cq.where(cb.equal(rootEntry.get(whereField),whereValue));
        return em.createQuery(cq).getResultList();
    }
}
