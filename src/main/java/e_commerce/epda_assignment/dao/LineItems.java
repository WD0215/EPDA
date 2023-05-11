package e_commerce.epda_assignment.dao;

import e_commerce.epda_assignment.model.Line_Item;
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
public class LineItems implements LineItem_I{
    @Autowired
    @Qualifier("emf")
    private EntityManagerFactory emf;


    public boolean create(Line_Item input) {
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
    public boolean update(Line_Item input) {
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
    public boolean updateMultiple (List<Line_Item> list, String updateField, String updateValue) {
        EntityManager em = emf.createEntityManager();
        EntityTransaction transaction = em.getTransaction();
        try {
            transaction.begin();
            CriteriaBuilder criteriaBuilder = em.getCriteriaBuilder();
            CriteriaUpdate<Line_Item> update = criteriaBuilder.createCriteriaUpdate(Line_Item.class);
            Root<Line_Item> root = update.from(Line_Item.class);
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
    public boolean delete(Line_Item input){
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
    public Line_Item getLineItembyId(String id) {
        EntityManager em = emf.createEntityManager();
        return em.find(Line_Item.class, id);
    }

    public List<Line_Item> getAllLineItem (){
        EntityManager em = emf.createEntityManager();
        CriteriaBuilder cb = em.getCriteriaBuilder();
        CriteriaQuery<Line_Item> cq = cb.createQuery(Line_Item.class);
        Root<Line_Item> rootEntry = cq.from(Line_Item.class);
        CriteriaQuery<Line_Item> all = cq.select(rootEntry);
        return em.createQuery(all).getResultList();
    }
    public List<Line_Item> findByConditionEqual (String whereField, Object whereValue){
        EntityManager em = emf.createEntityManager();
        CriteriaBuilder cb = em.getCriteriaBuilder();
        CriteriaQuery<Line_Item> cq = cb.createQuery(Line_Item.class);
        Root<Line_Item> rootEntry = cq.from(Line_Item.class);
        cq.where(cb.equal(rootEntry.get(whereField),whereValue));
        return em.createQuery(cq).getResultList();
    }
}
