package com.bhago.geo;

public abstract class GeoException extends Exception {

	
	    private int errCode_;

	    protected GeoException() {}

	    public GeoException(String msg) {
	        super(msg);
	    }

	    public GeoException(String msg, Throwable th) {
	        super(msg, th);
	    }

	    public GeoException(String msg, int error) {
	        super(msg);
	        this.errCode_ = error;
	    }

	    public GeoException(String msg, Throwable th, int error) {
	        super(msg, th);
	        this.errCode_ = error;
	    }

	    public void setErrorCode(int errCode) {
	        errCode_ = errCode;
	    }

	    public int getErrorCode() {
	        return errCode_;
	    }

}
