package com.example.demo;

import java.util.List;

public class StaReport {

	private String clientName;
	private String mdmId;
	private String status;
	private String brokarageAccount;
	private String principleApproval;
	private String reasonForApproval;
	private String nsdApproval;
	private String firstComments;
	private String secondComments;
	private List<TradeDetails> tradeDetails;
	private List<AuditTrail> auditTrail;

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

	public String getClientName() {
		return clientName;
	}

	public void setClientName(String clientName) {
		this.clientName = clientName;
	}

	public String getMdmId() {
		return mdmId;
	}

	public void setMdmId(String mdmId) {
		this.mdmId = mdmId;
	}

	public String getBrokarageAccount() {
		return brokarageAccount;
	}

	public void setBrokarageAccount(String brokarageAccount) {
		this.brokarageAccount = brokarageAccount;
	}

	public String getPrincipleApproval() {
		return principleApproval;
	}

	public void setPrincipleApproval(String principleApproval) {
		this.principleApproval = principleApproval;
	}

	public String getReasonForApproval() {
		return reasonForApproval;
	}

	public void setReasonForApproval(String reasonForApproval) {
		this.reasonForApproval = reasonForApproval;
	}

	public String getNsdApproval() {
		return nsdApproval;
	}

	public void setNsdApproval(String nsdApproval) {
		this.nsdApproval = nsdApproval;
	}

	public String getFirstComments() {
		return firstComments;
	}

	public void setFirstComments(String firstComments) {
		this.firstComments = firstComments;
	}

	public String getSecondComments() {
		return secondComments;
	}

	public void setSecondComments(String secondComments) {
		this.secondComments = secondComments;
	}

	public List<TradeDetails> getTradeDetails() {
		return tradeDetails;
	}

	public void setTradeDetails(List<TradeDetails> tradeDetails) {
		this.tradeDetails = tradeDetails;
	}

	public List<AuditTrail> getAuditTrail() {
		return auditTrail;
	}

	public void setAuditTrail(List<AuditTrail> auditTrail) {
		this.auditTrail = auditTrail;
	}

}

class TradeDetails {
	private String typeofTrade;
	private String typeofPraposal;
	private String secuirtyName;
	private String tradeamound;
	private String tradefunds;

	public String getTypeofTrade() {
		return typeofTrade;
	}

	public void setTypeofTrade(String typeofTrade) {
		this.typeofTrade = typeofTrade;
	}

	public String getTypeofPraposal() {
		return typeofPraposal;
	}

	public void setTypeofPraposal(String typeofPraposal) {
		this.typeofPraposal = typeofPraposal;
	}

	public String getSecuirtyName() {
		return secuirtyName;
	}

	public void setSecuirtyName(String secuirtyName) {
		this.secuirtyName = secuirtyName;
	}

	public String getTradeamound() {
		return tradeamound;
	}

	public void setTradeamound(String tradeamound) {
		this.tradeamound = tradeamound;
	}

	public String getTradefunds() {
		return tradefunds;
	}

	public void setTradefunds(String tradefunds) {
		this.tradefunds = tradefunds;
	}

	public TradeDetails(String typeofTrade, String typeofPraposal, String secuirtyName, String tradeamound,
			String tradefunds) {
		super();
		this.typeofTrade = typeofTrade;
		this.typeofPraposal = typeofPraposal;
		this.secuirtyName = secuirtyName;
		this.tradeamound = tradeamound;
		this.tradefunds = tradefunds;
	}

}

class AuditTrail {
	private String updatedBy;
	private String status;
	private String description;
	private String comment;
	private String updatedTime;

	public String getUpdatedBy() {
		return updatedBy;
	}

	public void setUpdatedBy(String updatedBy) {
		this.updatedBy = updatedBy;
	}

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public String getComment() {
		return comment;
	}

	public void setComment(String comment) {
		this.comment = comment;
	}

	public String getUpdatedTime() {
		return updatedTime;
	}

	public void setUpdatedTime(String updatedTime) {
		this.updatedTime = updatedTime;
	}

	public AuditTrail(String updatedBy, String status, String description, String comment, String updatedTime) {
		super();
		this.updatedBy = updatedBy;
		this.status = status;
		this.description = description;
		this.comment = comment;
		this.updatedTime = updatedTime;
	}

}