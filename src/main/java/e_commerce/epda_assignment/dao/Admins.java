package e_commerce.epda_assignment.dao;

import e_commerce.epda_assignment.model.Admin;
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
public class Admins implements Admin_I {
    @Autowired
    @Qualifier("emf")
    private EntityManagerFactory emf;


    public Admin create(Admin input) {
        EntityManager em = emf.createEntityManager();
        EntityTransaction transaction = em.getTransaction();
        try {
            transaction.begin();
            Admin admin = em.merge(input);
            transaction.commit();
            return admin;
        } catch (Exception e) {
            if (transaction.isActive()) {
                transaction.rollback();
            }
            return null;
        }
    }
    public Admin update(Admin input) {
        EntityManager em = emf.createEntityManager();
        EntityTransaction transaction = em.getTransaction();
        try {
            transaction.begin();
            Admin admin = em.merge(input);
            transaction.commit();
            return admin;
        } catch (Exception e) {
            if (transaction.isActive()) {
                transaction.rollback();
            }
            return null;
        }
    }
    public boolean updateMultiple (List<Admin> list, String updateField, String updateValue) {
        EntityManager em = emf.createEntityManager();
        EntityTransaction transaction = em.getTransaction();
        try {
            transaction.begin();
            CriteriaBuilder criteriaBuilder = em.getCriteriaBuilder();
            CriteriaUpdate<Admin> update = criteriaBuilder.createCriteriaUpdate(Admin.class);
            Root<Admin> root = update.from(Admin.class);
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
    public boolean delete(Admin input){
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
    public Admin getAdminbyId(String id) {
        EntityManager em = emf.createEntityManager();
        return em.find(Admin.class, id);
    }

    public List<Admin> getAllAdmin (){
        EntityManager em = emf.createEntityManager();
        CriteriaBuilder cb = em.getCriteriaBuilder();
        CriteriaQuery<Admin> cq = cb.createQuery(Admin.class);
        Root<Admin> rootEntry = cq.from(Admin.class);
        CriteriaQuery<Admin> all = cq.select(rootEntry);
        return em.createQuery(all).getResultList();
    }
    public List<Admin> findByConditionEqual (String whereField, Object whereValue){
        EntityManager em = emf.createEntityManager();
        CriteriaBuilder cb = em.getCriteriaBuilder();
        CriteriaQuery<Admin> cq = cb.createQuery(Admin.class);
        Root<Admin> rootEntry = cq.from(Admin.class);
        cq.where(cb.equal(rootEntry.get(whereField),whereValue));
        return em.createQuery(cq).getResultList();
    }
}
