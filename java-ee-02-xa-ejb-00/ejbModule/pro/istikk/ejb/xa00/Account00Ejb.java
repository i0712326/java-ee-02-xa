package pro.istikk.ejb.xa00;

import javax.ejb.LocalBean;
import javax.ejb.Stateful;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;
import javax.transaction.Transactional;

import org.jboss.logging.Logger;

import pro.itstikk.jpa.xa00.Account;


/**
 * Session Bean implementation class Account00Ejb
 */
@Stateless
@LocalBean
public class Account00Ejb {
	
	private static Logger logger = Logger.getLogger(Account00Ejb.class);
	@PersistenceContext(unitName="java-ee-02-xa-jpa-00")
	private EntityManager em;
    /**
     * Default constructor. 
     */
    public Account00Ejb() {
        
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
