package pro.itstikk.jpa.xa01;

import javax.ejb.LocalBean;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;
import javax.transaction.Transactional;


/**
 * Session Bean implementation class Account01Ejb
 */
@Stateless
@LocalBean
public class Account01Ejb {

	@PersistenceContext(unitName="java-ee-02-xa-jpa-01")
	private EntityManager em;
    /**
     * Default constructor. 
     */
    public Account01Ejb() {
        
    }
    
    public Account getAccount(Account account) {
    	String sql = "Select a from Account a where a.accno = :accno";
    	Query query = em.createQuery(sql);
    	query.setParameter("accno", account.getAccno());
    	return (Account) query.getSingleResult();
    }
    @Transactional
    public Account updateAccount(Account account) {
    	return em.merge(account);
    }

}
