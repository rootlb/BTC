package com.btcc.enums;


public enum OrderStatusCode {
	OPEN(1,"open"),CLOSED(2,"closed"),CANCELLED(3,"cancelled"),PENDING(4,"pensing"),ERROR(5,"error");
	private Integer status;
	private String desc;

	private OrderStatusCode(Integer status, String desc) {
		this.status = status;
		this.desc = desc;
	}

	public static String queryStatus(Integer status) {
		OrderStatusCode[] values = OrderStatusCode.values();
		for (OrderStatusCode value : values) {
			if (value.getStatus().equals(status)) {
				return value.getDesc();
			}
		}
		return null;
	}
	
	public Integer getStatus() {
		return status;
	}

	public void setStatus(Integer status) {
		this.status = status;
	}

	public String getDesc() {
		return desc;
	}

	public void setDesc(String desc) {
		this.desc = desc;
	}
}
