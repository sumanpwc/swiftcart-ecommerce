package com.swiftcart.commom.dto;

public class ApiResponse<T> {
	private String status;
	private int code;
	private String message;
	private String timestamp;
	private String requestId;
	private Data<T> data;
	
	public static class Data<T>{
		private T payload;
		private int count;
		
		public Data() {
			super();
		}

		public Data(T payload, int count) {
			super();
			this.payload = payload;
			this.count = count;
		}

		public T getPayload() {
			return payload;
		}

		public void setPayload(T payload) {
			this.payload = payload;
		}

		public int getCount() {
			return count;
		}

		public void setCount(int count) {
			this.count = count;
		}
	}
	
	public ApiResponse() {
		super();
	}

	public ApiResponse(String status, int code, String message, String timestamp, String requestId, Data<T> data) {
		super();
		this.status = status;
		this.code = code;
		this.message = message;
		this.timestamp = timestamp;
		this.requestId = requestId;
		this.data = data;
	}

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

	public int getCode() {
		return code;
	}

	public void setCode(int code) {
		this.code = code;
	}

	public String getMessage() {
		return message;
	}

	public void setMessage(String message) {
		this.message = message;
	}

	public String getTimestamp() {
		return timestamp;
	}

	public void setTimestamp(String timestamp) {
		this.timestamp = timestamp;
	}

	public String getRequestId() {
		return requestId;
	}

	public void setRequestId(String requestId) {
		this.requestId = requestId;
	}

	public Data<T> getData() {
		return data;
	}

	public void setData(Data<T> data) {
		this.data = data;
	}
	
}