package blog.com.service;

import java.util.Locale;

import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

import blog.com.models.dao.AccountDao;
import blog.com.models.entity.Account;

@Service
public class AccountService {

	private final AccountDao accountDao;
	private final IdGeneratorService idGeneratorService;

	public AccountService(AccountDao accountDao, IdGeneratorService idGeneratorService) {
		this.accountDao = accountDao;
		this.idGeneratorService = idGeneratorService;
	}

	public Account registerAccount(String accountName, String accountEmail, String password) {
		String normalizedName = normalizeText(accountName);
		String normalizedEmail = normalizeEmail(accountEmail);

		if (!StringUtils.hasText(normalizedName)) {
			throw new IllegalArgumentException("アカウント名は必須です。");
		}
		if (!StringUtils.hasText(normalizedEmail)) {
			throw new IllegalArgumentException("アカウントメールは必須です。");
		}
		if (!StringUtils.hasText(password)) {
			throw new IllegalArgumentException("パスワードは必須です。");
		}
		if (accountDao.findByAccountName(normalizedName) != null) {
			throw new IllegalArgumentException("アカウント名は既に登録されています。");
		}
		if (accountDao.findByAccountEmail(normalizedEmail) != null) {
			throw new IllegalArgumentException("アカウントメールは既に登録されています。");
		}

		Account account = new Account();
		account.setAccountId(idGeneratorService.nextId(value -> accountDao.existsById(value)));
		account.setAccountName(normalizedName);
		account.setAccountEmail(normalizedEmail);
		account.setPassword(password);
		return accountDao.save(account);
	}

	public Account login(String accountEmail, String password) {
		String normalizedEmail = normalizeEmail(accountEmail);
		if (!StringUtils.hasText(normalizedEmail) || !StringUtils.hasText(password)) {
			return null;
		}
		return accountDao.findByAccountEmailAndPassword(normalizedEmail, password);
	}

	public Account findByAccountId(Long accountId) {
		if (accountId == null) {
			return null;
		}
		return accountDao.findByAccountId(accountId);
	}

	private String normalizeText(String value) {
		return value == null ? null : value.trim();
	}

	private String normalizeEmail(String value) {
		if (value == null) {
			return null;
		}
		String trimmedValue = value.trim();
		return trimmedValue.toLowerCase(Locale.ROOT);
	}
}
