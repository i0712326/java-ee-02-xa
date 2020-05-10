package pro.itstikk.jpa.xa01;

import java.io.Serializable;
import javax.persistence.*;


/**
 * The persistent class for the accounttbl database table.
 * 
 */
@Entity
@Table(name="accounttbl")
@NamedQuery(name="Account.findAll", query="SELECT a FROM Account a")
public class Account implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private String accno;

	private float balance;

	public Account() {
	}

	public String getAccno() {
		return this.accno;
	}

	public void setAccno(String accno) {
		this.accno = accno;
	}

	public float getBalance() {
		return this.balance;
	}

	public void setBalance(float balance) {
		this.balance = balance;
	}

}