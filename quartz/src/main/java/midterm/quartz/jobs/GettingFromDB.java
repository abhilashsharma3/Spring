package midterm.quartz.jobs;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

import org.springframework.stereotype.Service;

import midterm.quartz.model.Product;

@Service
public class GettingFromDB {
    
    public void GettingFromDBExecute1(){
    	EntityManagerFactory entityManagerFactory = Persistence
                .createEntityManagerFactory("products");
        EntityManager entityManager = entityManagerFactory
                .createEntityManager();
		Product product1=entityManager.find(Product.class,1);
        System.out.println(product1.getName());
        entityManager.close();
        entityManagerFactory.close();
  }

	public void GettingFromDBExecute2() {
		// TODO Auto-generated method stub
		EntityManagerFactory entityManagerFactory1 = Persistence
                .createEntityManagerFactory("products");
        EntityManager entityManager1 = entityManagerFactory1
                .createEntityManager();
		Product pro2=entityManager1.find(Product.class,2);
	     System.out.println(pro2.getName());
	     entityManager1.close();
	     entityManagerFactory1.close();
	     }
}
