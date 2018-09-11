package cn.kgc.exception;

public class DaoException extends Exception {

	/**
	 * 
	 */
	private static final long serialVersionUID = -1657024705495313455L;
	
	
	public DaoException() {}
	
	public DaoException(String msg) {
		super(msg);
	}
	
	public DaoException(Throwable e) {
		super(e);
	}
}
