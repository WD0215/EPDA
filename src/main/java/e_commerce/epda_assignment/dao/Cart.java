package e_commerce.epda_assignment.dao;

import e_commerce.epda_assignment.model.Cart_Item;
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
public class Cart implements Cart_I{
    @Autowired
    @Qualifier("emf")
    private EntityManagerFactory emf;


    public boolean create(Cart_Item input) {
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
    public boolean update(Cart_Item input) {
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
    public boolean updateMultiple (List<Cart_Item> list, String updateField, String updateValue) {
        EntityManager em = emf.createEntityManager();
        EntityTransaction transaction = em.getTransaction();
        try {
            transaction.begin();
            CriteriaBuilder criteriaBuilder = em.getCriteriaBuilder();
            CriteriaUpdate<Cart_Item> update = criteriaBuilder.createCriteriaUpdate(Cart_Item.class);
            Root<Cart_Item> root = update.from(Cart_Item.class);
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
    public boolean delete(Cart_Item input){
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
    public Cart_Item getCartbyId(String id) {
        EntityManager em = emf.createEntityManager();
        return em.find(Cart_Item.class, id);
    }

    public List<Cart_Item> getAllCart (){
        EntityManager em = emf.createEntityManager();
        CriteriaBuilder cb = em.getCriteriaBuilder();
        CriteriaQuery<Cart_Item> cq = cb.createQuery(Cart_Item.class);
        Root<Cart_Item> rootEntry = cq.from(Cart_Item.class);
        CriteriaQuery<Cart_Item> all = cq.select(rootEntry);
        return em.createQuery(all).getResultList();
    }
    public List<Cart_Item> findByConditionEqual (String whereField, Object whereValue){
        EntityManager em = emf.createEntityManager();
        CriteriaBuilder cb = em.getCriteriaBuilder();
        CriteriaQuery<Cart_Item> cq = cb.createQuery(Cart_Item.class);
        Root<Cart_Item> rootEntry = cq.from(Cart_Item.class);
        cq.where(cb.equal(rootEntry.get(whereField),whereValue));
        return em.createQuery(cq).getResultList();
    }
}
