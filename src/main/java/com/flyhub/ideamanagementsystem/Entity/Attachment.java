package com.flyhub.ideamanagementsystem.Entity;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

import org.hibernate.annotations.OnDelete;
import org.hibernate.annotations.OnDeleteAction;

@Entity(name="Attachment")
@Table(name="attachment")
public class Attachment {
	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	@Column(name="attachment_id")
	private Long id;
	
	@ManyToOne(targetEntity = Idea.class,fetch = FetchType.LAZY)	  
	  @JoinColumn(name = "idea_id", nullable = false)	  
	  @OnDelete(action = OnDeleteAction.CASCADE)	  
	  private Idea idea;
	@Column(name="attachment_name",nullable=false)
	private String attachmentName;
	

	@Column(name = "create_date", nullable = true)

	@Temporal(TemporalType.TIMESTAMP)
	private Date createDate; // @ManyToOne

	@Column(name = "created_by_global_use_id", nullable = true)
	private Long createdByGlobalUserId; // private User user;

	@Column(name = "modified_date", nullable = true)

	@Temporal(TemporalType.TIMESTAMP)
	private Date modifiedDate;	

	@Column(name = "modified_by_global_user_id", nullable = true)
	private Long modifiedByGlobalUserId;

	@Column(name = "tenant_id", nullable = true)
	private Long tenantId;

	@Column(name = "modified_reason", nullable = true)
	private String modifiedReason;

	public Attachment(Idea idea, String attachmentName) {
		super();
		this.idea = idea;
		this.attachmentName = attachmentName;
	}
	

	public Attachment() {
		super();
		// TODO Auto-generated constructor stub
	}


	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public Idea getIdea() {
		return idea;
	}

	public void setIdea(Idea idea) {
		this.idea = idea;
	}

	public String getAttachmentName() {
		return attachmentName;
	}

	public void setAttachmentName(String attachmentName) {
		this.attachmentName = attachmentName;
	}

	public Date getCreateDate() {
		return createDate;
	}

	public void setCreateDate(Date createDate) {
		this.createDate = createDate;
	}

	public Long getCreatedByGlobalUserId() {
		return createdByGlobalUserId;
	}

	public void setCreatedByGlobalUserId(Long createdByGlobalUserId) {
		this.createdByGlobalUserId = createdByGlobalUserId;
	}

	public Date getModifiedDate() {
		return modifiedDate;
	}

	public void setModifiedDate(Date modifiedDate) {
		this.modifiedDate = modifiedDate;
	}

	public Long getModifiedByGlobalUserId() {
		return modifiedByGlobalUserId;
	}

	public void setModifiedByGlobalUserId(Long modifiedByGlobalUserId) {
		this.modifiedByGlobalUserId = modifiedByGlobalUserId;
	}

	public Long getTenantId() {
		return tenantId;
	}

	public void setTenantId(Long tenantId) {
		this.tenantId = tenantId;
	}

	public String getModifiedReason() {
		return modifiedReason;
	}

	public void setModifiedReason(String modifiedReason) {
		this.modifiedReason = modifiedReason;
	}	

}
