package com.putnam.response;

public class Response {
	private String status;
	private Object data;
	
	public Response(String status, Object data) {
		this.status = status;
		this.data = data;
	}

	public String getStatus() {
		return status;
	}

	@Override
	public String toString() {
		return "Response [status=" + status + ", data=" + data + "]";
	}

	public void setStatus(String status) {
		this.status = status;
	}

	public Object getData() {
		return data;
	}

	public void setData(Object data) {
		this.data = data;
	}

	public Response() {

	}
}
