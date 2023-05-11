package e_commerce.epda_assignment.dao;

import e_commerce.epda_assignment.model.ProductType;
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
import java.util.Locale;

@Component
public class ProductTypes implements ProductTypes_I {
    @Autowired
    @Qualifier("emf")
    private EntityManagerFactory emf;


    public boolean create(ProductType input) {
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
    public boolean update(ProductType input) {
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
    public boolean updateMultiple (List<ProductType> list, String updateField, String updateValue) {
        EntityManager em = emf.createEntityManager();
        EntityTransaction transaction = em.getTransaction();
        try {
            transaction.begin();
            CriteriaBuilder criteriaBuilder = em.getCriteriaBuilder();
            CriteriaUpdate<ProductType> update = criteriaBuilder.createCriteriaUpdate(ProductType.class);
            Root<ProductType> root = update.from(ProductType.class);
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
    public boolean delete(ProductType input){
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
    public ProductType getProductbyId(String id) {
        EntityManager em = emf.createEntityManager();
        return em.find(ProductType.class, id);
    }

    public List<ProductType> getAllProduct(){
        EntityManager em = emf.createEntityManager();
        CriteriaBuilder cb = em.getCriteriaBuilder();
        CriteriaQuery<ProductType> cq = cb.createQuery(ProductType.class);
        Root<ProductType> rootEntry = cq.from(ProductType.class);
        CriteriaQuery<ProductType> all = cq.select(rootEntry);
        return em.createQuery(all).getResultList();
    }
    public List<ProductType> findByConditionEqual (String whereField, Object whereValue){
        EntityManager em = emf.createEntityManager();
        CriteriaBuilder cb = em.getCriteriaBuilder();
        CriteriaQuery<ProductType> cq = cb.createQuery(ProductType.class);
        Root<ProductType> rootEntry = cq.from(ProductType.class);
        cq.where(cb.equal(rootEntry.get(whereField),whereValue));
        return em.createQuery(cq).getResultList();
    }

    public List<ProductType> findByConditionLike (String whereField , String criteria) {
        EntityManager em = emf.createEntityManager();
        CriteriaBuilder cb = em.getCriteriaBuilder();
        CriteriaQuery<ProductType> cq = cb.createQuery(ProductType.class);
        Root<ProductType> rootEntry = cq.from(ProductType.class);
        cq.where(cb.like(cb.lower(rootEntry.get(whereField)),criteria.toLowerCase(Locale.ROOT)));
        return em.createQuery(cq).getResultList();
    }

    public List<ProductType> searchProductBySeller (String sellerId , String name) {
        EntityManager em = emf.createEntityManager();
        CriteriaBuilder cb = em.getCriteriaBuilder();
        CriteriaQuery<ProductType> cq = cb.createQuery(ProductType.class);
        Root<ProductType> rootEntry = cq.from(ProductType.class);
        cq.where(
                cb.and(cb.like(cb.lower(rootEntry.get("productName")),name.toLowerCase(Locale.ROOT)),
                cb.like(cb.lower(rootEntry.get("sellerID")),sellerId.toLowerCase(Locale.ROOT)))
        );
        return em.createQuery(cq).getResultList();
    }


}
