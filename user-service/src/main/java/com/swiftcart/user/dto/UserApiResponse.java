package com.swiftcart.user.dto;

public class UserApiResponse {
	
	private String status;
    private String message;
    private String timestamp;
    private String requestId;
    private Data data;

    public static class Data {
    	private UserResponse user;
    	private int count;
    	
    	public Data() {
			super();
		}

		public Data(UserResponse user, int count) {
			super();
			this.user = user;
			this.count = count;
		}

		public UserResponse getUser() {
			return user;
		}

		public void setUser(UserResponse user) {
			this.user = user;
		}

		public int getCount() {
			return count;
		}

		public void setCount(int count) {
			this.count = count;
		}
    }
    
    public UserApiResponse() {
		super();
	}

	public UserApiResponse(String status, String message, String timestamp, String requestId, Data data) {
		super();
		this.status = status;
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

	public Data getData() {
		return data;
	}

	public void setData(Data data) {
		this.data = data;
	}
}