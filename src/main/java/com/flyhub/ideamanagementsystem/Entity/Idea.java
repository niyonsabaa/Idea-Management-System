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

@Entity
@Table(name = "idea")
public class Idea {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;

	@ManyToOne(targetEntity = User.class, fetch = FetchType.LAZY)

	@JoinColumn(name = "user_id", nullable = false)

	@OnDelete(action = OnDeleteAction.CASCADE)
	private User user;

	@Column(nullable = true)
	private Long globalUserId;

	@Column(nullable = true, length = 500)
	private String ideaTitle;

	@ManyToOne(targetEntity = Category.class, fetch = FetchType.LAZY)

	@JoinColumn(name = "category_id", nullable = false)

	@OnDelete(action = OnDeleteAction.CASCADE)

	private Category category;
	/*
	 * @Column(nullable = true) private int categoryId;
	 */
	@Column(nullable = true)
	// @Temporal(TemporalType.TIMESTAMP)
	public String dateOfSubmission;
	@Column(nullable = true, length = 1000)
	private String ideaDescription;
	@Column(nullable = true)
	@Temporal(TemporalType.TIMESTAMP)
	private Date createDate;
	@Column(nullable = true)
	private String createdByGlobalUserId;
	@Column(nullable = true)
	@Temporal(TemporalType.TIMESTAMP)
	private Date modifiedDate;
	@Column(nullable = true)
	private Long modifiedByGlobalUserId;
	@Column(nullable = true)
	private Long tenantId;
	@Column(nullable = true)
	private String modifiedReason;

	@ManyToOne(targetEntity = Priority.class, fetch = FetchType.LAZY)

	@JoinColumn(name = "priority_id", nullable = false)

	@OnDelete(action = OnDeleteAction.CASCADE)

	private Priority priority;

	/*
	 * @Column(nullable = true) private int priorityId;
	 */
	@Column(nullable = true)
	private int ideaStatus;
	@Column(nullable = true)
	private int ideaApprovalStatus;
	@Column(nullable = true)
	private int ideaWorkflowStatus;
	@Column(nullable = true)
	private boolean ideaHasAttachment;
	@Column(nullable = true)
	private String ideaExecutiveSummary;
	@Column(nullable = true)
	private String ideaBackgroundDescription;

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public User getUser() {
		return user;
	}

	public void setUser(User user) {
		this.user = user;
	}

	/*
	 * public Long getUserId() { return userId; } public void setUserId(Long userId)
	 * { this.userId = userId; }
	 */

	public String getIdeaTitle() {
		return ideaTitle;
	}

	public void setIdeaTitle(String ideaTitle) {
		this.ideaTitle = ideaTitle;
	}

	/*
	 * public int getCategoryId() { return categoryId; }
	 * 
	 * public void setCategoryId(int categoryId) { this.categoryId = categoryId; }
	 */
	public Category getCategory() {
		return category;
	}

	public void setCategory(Category category) {
		this.category = category;
	}

	public String getDateOfSubmission() {
		return dateOfSubmission;
	}

	public void setDateOfSubmission(String dateOfSubmission) {
		this.dateOfSubmission = dateOfSubmission;
	}

	public String getIdeaDescription() {
		return ideaDescription;
	}

	public void setIdeaDescription(String ideaDescription) {
		this.ideaDescription = ideaDescription;
	}

	public Date getCreateDate() {
		return createDate;
	}

	public void setCreateDate(Date createDate) {
		this.createDate = createDate;
	}

	public String getCreatedByGlobalUserId() {
		return createdByGlobalUserId;
	}

	public void setCreatedByGlobalUserId(String createdByGlobalUserId) {
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

	/*
	 * public int getPriorityId() { return priorityId; }
	 * 
	 * public void setPriorityId(int priorityId) { this.priorityId = priorityId; }
	 */

	public int getIdeaStatus() {
		return ideaStatus;
	}

	public Priority getPriority() {
		return priority;
	}

	public void setPriority(Priority priority) {
		this.priority = priority;
	}

	public void setIdeaStatus(int ideaStatus) {
		this.ideaStatus = ideaStatus;
	}

	public int getIdeaApprovalStatus() {
		return ideaApprovalStatus;
	}

	public void setIdeaApprovalStatus(int ideaApprovalStatus) {
		this.ideaApprovalStatus = ideaApprovalStatus;
	}

	public int getIdeaWorkflowStatus() {
		return ideaWorkflowStatus;
	}

	public void setIdeaWorkflowStatus(int ideaWorkflowStatus) {
		this.ideaWorkflowStatus = ideaWorkflowStatus;
	}

	public boolean isIdeaHasAttachment() {
		return ideaHasAttachment;
	}

	public void setIdeaHasAttachment(boolean ideaHasAttachment) {
		this.ideaHasAttachment = ideaHasAttachment;
	}

	public String getIdeaExecutiveSummary() {
		return ideaExecutiveSummary;
	}

	public void setIdeaExecutiveSummary(String ideaExecutiveSummary) {
		this.ideaExecutiveSummary = ideaExecutiveSummary;
	}

	public String getIdeaBackgroundDescription() {
		return ideaBackgroundDescription;
	}

	public void setIdeaBackgroundDescription(String ideaBackgroundDescription) {
		this.ideaBackgroundDescription = ideaBackgroundDescription;
	}

	public Long getGlobalUserId() {
		return globalUserId;
	}

	public void setGlobalUserId(Long globalUserId) {
		this.globalUserId = globalUserId;
	}

	public Idea(User user, Long globalUserId, String ideaTitle, Category category, String dateOfSubmission,
			String ideaDescription, Priority priority, String ideaExecutiveSummary, String ideaBackgroundDescription) {
		super();
		this.user = user;
		this.globalUserId = globalUserId;
		this.ideaTitle = ideaTitle;
		this.category = category;
		this.dateOfSubmission = dateOfSubmission;
		this.ideaDescription = ideaDescription;
		this.priority = priority;
		this.ideaExecutiveSummary = ideaExecutiveSummary;
		this.ideaBackgroundDescription = ideaBackgroundDescription;
	}

	public Idea() {
		super();
		// TODO Auto-generated constructor stub
	}

}
