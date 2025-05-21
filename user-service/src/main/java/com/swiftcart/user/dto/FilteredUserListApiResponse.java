package com.swiftcart.user.dto;

import java.util.List;

public class FilteredUserListApiResponse {

	private String status;
	private int code;
    private String message;
    private String timestamp;
    private String requestId;
    private FilterParams filters;
    private Pagination pagination;
    private Data data;

    public static class FilterParams {
        private String role;
        private Boolean isEmailVerified;
        private Boolean isPhoneVerified;
        private Boolean isActive;
        
        public FilterParams() {
			super();
		}

		public FilterParams(String role, Boolean isEmailVerified, Boolean isPhoneVerified, Boolean isActive) {
			super();
			this.role = role;
			this.isEmailVerified = isEmailVerified;
			this.isPhoneVerified = isPhoneVerified;
			this.isActive = isActive;
		}

		public String getRole() {
			return role;
		}

		public void setRole(String role) {
			this.role = role;
		}

		public Boolean getIsEmailVerified() {
			return isEmailVerified;
		}

		public void setIsEmailVerified(Boolean isEmailVerified) {
			this.isEmailVerified = isEmailVerified;
		}

		public Boolean getIsPhoneVerified() {
			return isPhoneVerified;
		}

		public void setIsPhoneVerified(Boolean isPhoneVerified) {
			this.isPhoneVerified = isPhoneVerified;
		}

		public Boolean getIsActive() {
			return isActive;
		}

		public void setIsActive(Boolean isActive) {
			this.isActive = isActive;
		}
        
    }

    public static class Pagination {
        private int page;
        private int limit;
        private boolean hasMore;
        
        // Constructors, Getters, Setters
         public Pagination() {
			super();
		}

		public Pagination(int page, int limit, boolean hasMore) {
			super();
			this.page = page;
			this.limit = limit;
			this.hasMore = hasMore;
		}

		public int getPage() {
			return page;
		}

		public void setPage(int page) {
			this.page = page;
		}

		public int getLimit() {
			return limit;
		}

		public void setLimit(int limit) {
			this.limit = limit;
		}

		public boolean isHasMore() {
			return hasMore;
		}

		public void setHasMore(boolean hasMore) {
			this.hasMore = hasMore;
		}
        
    }

    public static class Data {
        private List<UserResponse> payload;
        private int count;
        // Constructors, Getters, Setters
        
        public Data() {
        	super();
		}
		public Data(List<UserResponse> payload, int count) {
			super();
			this.payload = payload;
			this.count = count;
		}
		public List<UserResponse> getPayload() {
			return payload;
		}
		public void setPayload(List<UserResponse> payload) {
			this.payload = payload;
		}
		public int getCount() {
			return count;
		}
		public void setCount(int count) {
			this.count = count;
		}
    }

    // Constructors, Getters, Setters
    
    public FilteredUserListApiResponse() {
		super();
	}

	public FilteredUserListApiResponse(String status, int code, String message, String timestamp, String requestId,
			FilterParams filters, Pagination pagination, Data data) {
		super();
		this.status = status;
		this.code = code;
		this.message = message;
		this.timestamp = timestamp;
		this.requestId = requestId;
		this.filters = filters;
		this.pagination = pagination;
		this.data = data;
	}

	public int getCode() {
		return code;
	}

	public void setCode(int code) {
		this.code = code;
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

	public FilterParams getFilters() {
		return filters;
	}

	public void setFilters(FilterParams filters) {
		this.filters = filters;
	}

	public Pagination getPagination() {
		return pagination;
	}

	public void setPagination(Pagination pagination) {
		this.pagination = pagination;
	}

	public Data getData() {
		return data;
	}

	public void setData(Data data) {
		this.data = data;
	}
}
