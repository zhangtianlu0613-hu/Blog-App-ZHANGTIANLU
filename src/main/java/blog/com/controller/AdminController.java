package blog.com.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import blog.com.models.entity.Account;
import blog.com.service.AccountService;
import jakarta.servlet.http.HttpSession;

@Controller
@RequestMapping("/admin")
public class AdminController {

	private final AccountService accountService;

	public AdminController(AccountService accountService) {
		this.accountService = accountService;
	}

	@GetMapping("/login")
	public String loginPage(HttpSession session) {
		return session.getAttribute("accountId") == null ? "admin_login" : "redirect:/blog/list";
	}

	@GetMapping("/register")
	public String registerPage(Model model, HttpSession session) {
		if (session.getAttribute("accountId") != null) {
			return "redirect:/blog/list";
		}
		if (!model.containsAttribute("account")) {
			model.addAttribute("account", new Account());
		}
		return "admin_register";
	}

	@PostMapping("/register/process")
	public String registerProcess(@RequestParam("adminName") String accountName,
			@RequestParam("adminEmail") String accountEmail, @RequestParam("password") String password,
			RedirectAttributes redirectAttributes) {
		try {
			accountService.registerAccount(accountName, accountEmail, password);
			redirectAttributes.addFlashAttribute("message", "登録完了しました。");
			return "redirect:/admin/login";
		} catch (IllegalArgumentException exception) {
			Account account = new Account();
			account.setAccountName(accountName);
			account.setAccountEmail(accountEmail);
			redirectAttributes.addFlashAttribute("account", account);
			redirectAttributes.addFlashAttribute("errorMessage", exception.getMessage());
			return "redirect:/admin/register";
		}
	}

	@PostMapping("/login/process")
	public String loginProcess(@RequestParam("adminEmail") String accountEmail,
			@RequestParam("password") String password, HttpSession session, RedirectAttributes redirectAttributes) {
		Account account = accountService.login(accountEmail, password);
		if (account == null) {
			redirectAttributes.addFlashAttribute("errorMessage", "メールアドレスまたはパスワードが無効です。");
			return "redirect:/admin/login";
		}

		session.setAttribute("accountId", account.getAccountId());
		session.setAttribute("accountName", account.getAccountName());
		return "redirect:/blog/list";
	}

	@GetMapping("/logout")
	public String logout(HttpSession session) {
		session.invalidate();
		return "redirect:/admin/login";
	}
}
