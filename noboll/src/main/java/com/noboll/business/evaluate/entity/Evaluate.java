package com.noboll.business.evaluate.entity;

import com.noboll.core.base.entity.BaseEntity;

/**
 * Position entity. @author MyEclipse Persistence Tools
 * 评价表  Evaluate
 */

public class Evaluate extends BaseEntity  {

	private static final long serialVersionUID = 631591814426724602L;
	
	// Fields
	private String resumeId;// 简历id
	private String customerId;// 客户id
	private String REvaluate;// 简历评价
	private String CEvaluate;// 客户评价
	private String REvaluateStar;// 简历评价星级
	private String CEvaluateStar;// 客户评价星级
	private String deleteFlag;
	
	private String label;// 标签
	
	
	public String getResumeId() {
		return resumeId;
	}
	public void setResumeId(String resumeId) {
		this.resumeId = resumeId;
	}
	public String getCustomerId() {
		return customerId;
	}
	public void setCustomerId(String customerId) {
		this.customerId = customerId;
	}
	public String getREvaluate() {
		return REvaluate;
	}
	public void setREvaluate(String rEvaluate) {
		REvaluate = rEvaluate;
	}
	public String getCEvaluate() {
		return CEvaluate;
	}
	public void setCEvaluate(String cEvaluate) {
		CEvaluate = cEvaluate;
	}
	public String getREvaluateStar() {
		return REvaluateStar;
	}
	public void setREvaluateStar(String rEvaluateStar) {
		REvaluateStar = rEvaluateStar;
	}
	public String getCEvaluateStar() {
		return CEvaluateStar;
	}
	public void setCEvaluateStar(String cEvaluateStar) {
		CEvaluateStar = cEvaluateStar;
	}
	public String getDeleteFlag() {
		return deleteFlag;
	}
	public void setDeleteFlag(String deleteFlag) {
		this.deleteFlag = deleteFlag;
	}
	public String getLabel() {
		return label;
	}
	public void setLabel(String label) {
		this.label = label;
	}
	
	
	
}