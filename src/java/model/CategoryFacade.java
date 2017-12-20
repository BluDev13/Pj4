package model;

import entities.Category;
import entities.Products;
import java.util.Calendar;
import java.util.List;
import javax.annotation.PreDestroy;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;

@Stateless
public class CategoryFacade extends AbstractFacade<Category> {

    @PersistenceContext(unitName = "Project_Team4PU")
    private EntityManager em;

    @Override
    protected EntityManager getEntityManager() {
        return em;
    }

    public CategoryFacade() {
        super(Category.class);
    }

    public String getNameByID(int id) {
        return find(id).getCategoryName();
    }

    public List<Products> findPaging(int categoryID, int[] range) {
        Category cate = find(categoryID);
        if (cate == null) {
            return null;
        }
        Query q;
        
        if (cate.getCategorySub() == null) {
            q = getEntityManager().createQuery("SELECT a From Products a WHERE a.categoryID.categoryID IN (SELECT b.categoryID FROM Category b WHERE b.categorySub = :categoryID)");
        } else {
            q = getEntityManager().createQuery("SELECT a From Products a WHERE a.categoryID.categoryID = :categoryID");
        }
        q.setParameter("categoryID", categoryID);
        q.setMaxResults(range[1] - range[0]);
        q.setFirstResult(range[0]);
        return q.getResultList();
    }
    
    public List<Products> findPagingSize(int categoryID, int[] range) {
        Category cate = find(categoryID);
        if (cate == null) {
            return null;
        }
        Query q;
        
        if (cate.getCategorySub() == null) {
            q = getEntityManager().createQuery("SELECT a From Products a WHERE a.categoryID.categoryID IN (SELECT b.categoryID FROM Category b WHERE b.categorySub = :categoryID)");
        } else {
            q = getEntityManager().createQuery("SELECT a From Products a WHERE a.categoryID.categoryID = :categoryID");
        }
        q.setParameter("categoryID", categoryID);
        return q.getResultList();
    }

    public Long productCount(int categoryID) {
        Query q = em.createQuery("SELECT COUNT(a) FROM Products a WHERE a.categoryID.categoryID IN (SELECT b.categoryID FROM Category b WHERE b.categorySub IN (SELECT b.categoryID FROM Category b WHERE b.categoryID = :categoryID))");
        q.setParameter("categoryID", categoryID);
        return (Long) q.getSingleResult();
    }

    public List<Category> getSubCategory(int ID) {
        Query q = em.createQuery("SELECT a FROM Category a WHERE a.categorySub = :ID");
        q.setParameter("ID", ID);
        return q.getResultList();
    }

    public boolean removeRoot(int root) {
        Query q = em.createQuery("DELETE FROM Category a WHERE a.categorySub = :root");
        q.setParameter("root", root);
        return q.executeUpdate() > 0;
    }
    
    public List<Products> searchCategory(String sql, int categoryID, String name, int from, int to){
        Query q = em.createQuery(sql);
        q.setParameter("category", categoryID);
        q.setParameter("name", "%" + name + "%");
        if(from > 0 && to > 0) {
            q.setParameter("from", from);
            q.setParameter("to", to);
        }
        return q.getResultList();
    }
    
    public List<Products> filter(String sql, String name, int from, int to, int[] range){
        Query q = em.createQuery(sql);
        q.setParameter("name", "%" + name + "%");
        if(from > 0 && to > 0) {
            q.setParameter("from", from);
            q.setParameter("to", to);
        }
        q.setMaxResults(range[1] - range[0]);
        q.setFirstResult(range[0]);
        return q.getResultList();
    }
    
    public List<Products> filterSize(String sql, String name, int from, int to, int[] range){
        Query q = em.createQuery(sql);
        q.setParameter("name", "%" + name + "%");
        if(from > 0 && to > 0) {
            q.setParameter("from", from);
            q.setParameter("to", to);
        }
        return q.getResultList();
    }
    public Long ReportCategory(int cate,int year) {
        long count = 0;
        Query q = em.createNativeQuery("SELECT sum(b.Quantity*c.ProductPrice)\n" +
"FROM Orders a join OrderDetails b on a.OrderID = b.OrderID join Products c on c.ProductID = b.ProductID join Category d on d.CategoryID = c.CategoryID\n" +
"where d.CategoryID in (select CategoryID from Category where CategorySub = ?) and (a.orderDate >= CONVERT(DATETIME, ?) AND a.orderDate < CONVERT(DATETIME, ?)) and a.OrderStatus = 2");
        q.setParameter(1, cate);
        q.setParameter(2,"01/01/" + year);
        q.setParameter(3,"01/01/" + (year + 1));
        try {
            count = (long) q.getSingleResult();
        } catch (Exception e) {
            System.out.println(e.getMessage());
            count = 0;
        }
        return count;
    }
    
    @PreDestroy
    public void destroy() {
        em.close();
    }
}
