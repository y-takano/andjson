package jp.gr.java_conf.ke.io;

public class IOStreamingException extends RuntimeException {

	public IOStreamingException(Exception e) {
		super(e);
	}

	public IOStreamingException(String msg) {
		super(msg);
	}
}
