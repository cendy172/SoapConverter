package com.liqing.domain;

public class ResponseHeader
{
	private String responseCreatedTime;
	private String operationStatus;

	public String getResponseCreatedTime()
	{
		return responseCreatedTime;
	}

	public void setResponseCreatedTime(String responseCreatedTime)
	{
		this.responseCreatedTime = responseCreatedTime;
	}

	public String getOperationStatus()
	{
		return operationStatus;
	}

	public void setOperationStatus(String operationStatus)
	{
		this.operationStatus = operationStatus;
	}
}
