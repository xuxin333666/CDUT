package cn.kgc.exception;

public class ServiceException extends Exception {

	/**
	 * 
	 */
	private static final long serialVersionUID = -1657024705495313455L;
	
	
	public ServiceException() {}
	
	public ServiceException(String msg) {
		super(msg);
	}
	
	public ServiceException(Throwable e) {
		super(e);
	}
}
