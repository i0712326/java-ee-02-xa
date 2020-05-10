package pro.itstikk.ejb.xa;

import javax.ejb.EJB;
import javax.ejb.Stateless;
import javax.transaction.Transactional;

import org.jboss.logging.Logger;

import pro.istikk.ejb.xa00.Account00Ejb;
import pro.itstikk.jpa.xa01.Account01Ejb;

/**
 * Session Bean implementation class AccountEjb
 */
@Stateless(mappedName = "accountEjb")
public class AccountEjb {
	private static Logger logger = Logger.getLogger(AccountEjb.class);
	@EJB
	private Account00Ejb account00Ejb;
	@EJB
	private Account01Ejb account01Ejb;
    /**
     * Default constructor. 
     */
    public AccountEjb() {
        
    }
    @Transactional
    public void transfer(pro.itstikk.jpa.xa00.Account source, pro.itstikk.jpa.xa01.Account destination, float amount) throws Exception {
    	
    	// validate source and destination
    	if(source.getAccno().equals(destination.getAccno())) throw new Exception("Source and Destination is the same.");
    	
    	// validate source
    	pro.itstikk.jpa.xa00.Account sourceAccount = account00Ejb.getAccount(source);
    	if(sourceAccount==null) throw new Exception("No Source Account found !");
    	
    	// validate destination
    	
    	pro.itstikk.jpa.xa01.Account destinationAccount = account01Ejb.getAccount(destination);
    	if(destinationAccount==null) throw new Exception("No destination account found !");
    	
    	// validate amount
    	if(amount<=0) throw new Exception("Transfer amount is 0");
    	float sourceBal = sourceAccount.getBalance() - amount;
    	logger.info("source account : "+sourceAccount.getAccno());
    	logger.info("source balance : "+sourceBal); 
    	
    	if(sourceBal < 0) throw new Exception("Insufficient fund");
    	float destinationBal = destinationAccount.getBalance() + amount;
    	logger.info("destination account : "+destinationAccount.getAccno());
    	logger.info("destination balance : "+destinationBal);
    	
    	// update account
    	source.setBalance(sourceBal);
    	if(account00Ejb.updateAccount(source)==null) throw new Exception("update source account failed.");
    	
    	// update destination
    	destination.setBalance(destinationBal);
    	if(account01Ejb.updateAccount(destination)==null) throw new Exception("update destination account failed.");
    	
    }
}
