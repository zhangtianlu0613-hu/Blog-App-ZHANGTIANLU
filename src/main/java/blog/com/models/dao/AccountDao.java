package blog.com.models.dao;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import blog.com.models.entity.Account;

@Repository
public interface AccountDao extends JpaRepository<Account, Long> {

	Account save(Account account);

	Account findByAccountId(Long accountId);

	Account findByAccountName(String accountName);

	Account findByAccountEmail(String accountEmail);

	Account findByAccountEmailAndPassword(String accountEmail, String password);
}
