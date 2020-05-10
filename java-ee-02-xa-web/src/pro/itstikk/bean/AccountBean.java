package pro.itstikk.bean;

import javax.annotation.PostConstruct;
import javax.ejb.EJB;
import javax.enterprise.context.RequestScoped;
import javax.inject.Named;

import org.jboss.logging.Logger;

import pro.itstikk.ejb.xa.AccountEjb;

@Named(value="accountBean")
@RequestScoped
public class AccountBean {
	private static Logger logger = Logger.getLogger(AccountBean.class);
	@EJB
	private AccountEjb accountEjb;
	private pro.itstikk.jpa.xa00.Account source;
	private pro.itstikk.jpa.xa01.Account destination;
	private float amount;
	@PostConstruct
	public void init() {
		source = new pro.itstikk.jpa.xa00.Account();
		destination = new pro.itstikk.jpa.xa01.Account();
	}
	public String transfer() throws Exception {
		logger.info("source : "+source.getAccno());
		logger.info("destination : "+destination.getAccno());
		logger.info("amount "+amount);
		accountEjb.transfer(source, destination, amount);
		return "response.xhtml";
	}

	public pro.itstikk.jpa.xa00.Account getSource() {
		return source;
	}

	public void setSource(pro.itstikk.jpa.xa00.Account source) {
		this.source = source;
	}

	public pro.itstikk.jpa.xa01.Account getDestination() {
		return destination;
	}

	public void setDestination(pro.itstikk.jpa.xa01.Account destination) {
		this.destination = destination;
	}

	public float getAmount() {
		return amount;
	}

	public void setAmount(float amount) {
		this.amount = amount;
	}
	
	
	
}
