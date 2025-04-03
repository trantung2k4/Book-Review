package config;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;

public class SecurityConfig {
	
	public static final String ROLE_USER = "USER";
	public static final String ROLE_SELLER = "SELLER";
	public static final String ROLE_ADMIN = "ADMIN";

	private static final Map<String, List<String>> mapConfig = new HashMap<String, List<String>>();

	static {
		init();
	}

	private static void init() {
		
		// Cấu hình cho vai trò "USER".
		List<String> urlPatternsUser = new ArrayList<String>();

//		urlPatterns1.add("/user");
		urlPatternsUser.add("/review");
//		urlPatterns1.add("/checkout");

		mapConfig.put(ROLE_USER, urlPatternsUser);
		
		// Cấu hình cho vai trò "SELLER".
		List<String> urlPatternsSeller = new ArrayList<String>();

		urlPatternsSeller.add("/manager");
		urlPatternsSeller.add("/addproduct");
		urlPatternsSeller.add("/deleteproduct");
		urlPatternsSeller.add("/editproduct");

		mapConfig.put(ROLE_SELLER, urlPatternsUser);
		mapConfig.put(ROLE_SELLER, urlPatternsSeller);

		// Cấu hình cho vai trò "ADMIN".
		List<String> urlPatternsAdmin = new ArrayList<String>();

		urlPatternsAdmin.add("/admin");
		urlPatternsAdmin.add("/removeuser");
		urlPatternsAdmin.add("/edituser");

		mapConfig.put(ROLE_ADMIN, urlPatternsUser);
		mapConfig.put(ROLE_ADMIN, urlPatternsSeller);
		mapConfig.put(ROLE_ADMIN, urlPatternsAdmin);
	}

	public static Set<String> getAllAppRoles() {
		return mapConfig.keySet();
	}

	public static List<String> getUrlPatternsForRole(String role) {
		return mapConfig.get(role);
	}
}
