package takeARide.service;

import takeARide.model.SocialAccount;

public interface SocialService {
	String getAuthorizeUrl();

	SocialAccount getSocialAccount(String authToken);
}