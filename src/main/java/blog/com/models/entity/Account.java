package blog.com.models.entity;

import java.time.LocalDateTime;

import org.hibernate.annotations.CreationTimestamp;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;

@Entity
@Table(name = "account")
public class Account {

	@Id
	@Column(name = "account_id", nullable = false)
	private Long accountId;

	@Column(name = "account_name", nullable = false, unique = true, length = 100)
	private String accountName;

	@Column(name = "account_email", nullable = false, unique = true, length = 255)
	private String accountEmail;

	@Column(name = "password", nullable = false, length = 255)
	private String password;

	@CreationTimestamp
	@Column(name = "register_date", updatable = false, columnDefinition = "TIMESTAMP DEFAULT CURRENT_TIMESTAMP")
	private LocalDateTime registerDate;

	public Account() {
	}

	public Account(Long accountId, String accountName, String accountEmail, String password) {
		this.accountId = accountId;
		this.accountName = accountName;
		this.accountEmail = accountEmail;
		this.password = password;
	}

	public Long getAccountId() {
		return accountId;
	}

	public void setAccountId(Long accountId) {
		this.accountId = accountId;
	}

	public String getAccountName() {
		return accountName;
	}

	public void setAccountName(String accountName) {
		this.accountName = accountName;
	}

	public String getAccountEmail() {
		return accountEmail;
	}

	public void setAccountEmail(String accountEmail) {
		this.accountEmail = accountEmail;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public LocalDateTime getRegisterDate() {
		return registerDate;
	}

	public void setRegisterDate(LocalDateTime registerDate) {
		this.registerDate = registerDate;
	}
}
