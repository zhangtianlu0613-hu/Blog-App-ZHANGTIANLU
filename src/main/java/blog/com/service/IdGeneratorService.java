package blog.com.service;

import java.util.function.LongPredicate;

import org.springframework.stereotype.Service;

@Service
public class IdGeneratorService {

	public Long nextId(LongPredicate existsChecker) {
		long candidate = System.currentTimeMillis();
		while (existsChecker.test(candidate)) {
			candidate++;
		}
		return candidate;
	}
}
