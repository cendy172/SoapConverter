package com.liqing.domain;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;

@XmlAccessorType(XmlAccessType.FIELD)
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
