package com.tradetask.entity;

import java.util.Date;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;

import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

@Entity
@JsonIgnoreProperties(ignoreUnknown = true)
public class Trade {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	int tradeId;
	@NotNull(message = "Currency may not be null")
	String currency;
	@Min(1)
	double amount;
	String comments;
	String location;
	String status;
	String bankname;
	@CreationTimestamp
	Date createdTime;
	@UpdateTimestamp
	Date lastUpdatedTime;

	public Trade() {
	}

	public Trade(String currency, double amount, String comments, String location, String status, String bankname,
			Date createdTime, Date lastUpdatedTime) {
		this.currency = currency;
		this.amount = amount;
		this.comments = comments;
		this.location = location;
		this.status = status;
		this.bankname = bankname;
		this.createdTime = createdTime;
		this.lastUpdatedTime = lastUpdatedTime;
	}

	public int getTradeId() {
		return tradeId;
	}

	/*
	 * public void setTradeId(int tradeId) { this.tradeId = tradeId; }
	 */

	public String getCurrency() {
		return currency;
	}

	public void setCurrency(String currency) {
		this.currency = currency;
	}

	public double getAmount() {
		return amount;
	}

	public void setAmount(double amount) {
		this.amount = amount;
	}

	public String getComments() {
		return comments;
	}

	public void setComments(String comments) {
		this.comments = comments;
	}

	public String getLocation() {
		return location;
	}

	public void setLocation(String location) {
		this.location = location;
	}

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

	public String getBankname() {
		return bankname;
	}

	public void setBankname(String bankname) {
		this.bankname = bankname;
	}

	public Date getCreatedTime() {
		return createdTime;
	}

	public void setCreatedTime(Date createdTime) {
		this.createdTime = createdTime;
	}

	public Date getLastUpdatedTime() {
		return lastUpdatedTime;
	}

	public void setLastUpdatedTime(Date lastUpdatedTime) {
		this.lastUpdatedTime = lastUpdatedTime;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + tradeId;
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Trade other = (Trade) obj;
		if (tradeId != other.tradeId)
			return false;
		return true;
	}

	@Override
	public String toString() {
		return "Trade [tradeId=" + tradeId + ", currency=" + currency + ", amount=" + amount + ", comments=" + comments
				+ ", location=" + location + ", status=" + status + ", bankname=" + bankname + ", createdTime="
				+ createdTime + ", lastUpdatedTime=" + lastUpdatedTime + "]";
	}

}
