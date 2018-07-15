package takeARide.exceptions;

import javax.servlet.http.HttpServletResponse;

public class BlockedUserExeption extends AbstractApplicationException{
	
	
	/**
	 * 
	 */
	private static final long serialVersionUID = -1379983727935749771L;

	public BlockedUserExeption(String s) {
		super(s,HttpServletResponse.SC_NOT_ACCEPTABLE);
	}
}
