package takeARide.entity;

import takeARide.model.CurrentAccount;

public class Account extends AbstractEntity<Integer> implements CurrentAccount {
	private static final long serialVersionUID = -3196229925974576545L;
	private String name;
	private String email;
	private boolean isBlocked = false;

	public boolean isBlocked() {
		return isBlocked;
	}

	public void setBlocked(boolean isBlocked) {
		this.isBlocked = isBlocked;
	}

	public Account() {
		super();
	}

	public Account(String name, String email) {
		super();
		this.email = email;
		this.name = name;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}
    
	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	@Override
	public String toString() {
		return String.format("Account [id=%s, name=%s, email=%s]", getId(), name, email);
	}

	@Override
	public String getDescription() {
		return name + "("+email+")";
	}
}