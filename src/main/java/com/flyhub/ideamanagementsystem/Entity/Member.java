package com.flyhub.ideamanagementsystem.Entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.UniqueConstraint;

@Entity
@Table(name = "user_table")
public class Member {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private long id;
	@Column(name = "global_user_id")
	private long globalUserId;
	@Column(name = "first_name ")
	private String firstName;
	@Column(name = "middle_name")
	private String middleName;
	@Column(name = "last_name")
	private String lastName;
	@Column(name = "other_name")
	private String otherName;
	@Column(name = "full_name ")
	private String fullName;
	@Column(name = "date_of_birth")
	private String dateOfBirth;
	@Column(name = "nin")
	private String nin;
	@Column(name = "primary_phone")
	private String primaryPhone;
	@Column(name = "secondary_phone")
	private String secondaryPhone;
	@Column(name = "primary_email")
	private String primaryEmail;
	@Column(name = "secondary_email")
	private String secondaryEmail;
	@Column(name = "preferred_whatsapp_number")
	private String preferredWhatsappNumber;
	@Column(name = "card_number")
	private String cardNumber;
	@Column(name = "password")
	private String password;
	@Column(name = "create_date")
	private String createDate;
	@Column(name = "created_by_global_user_id")
	private String createdByGlobalUserId;
	@Column(name = "modified_date")
	private String modifiedDate;
	@Column(name = "modified_by_global_user_id")
	private String modifiedByGlobalUserId;
	@Column(name = "tenant_id")
	private int tenantId;
	@Column(name = "postfix_id")
	private int postfixId;
	@Column(name = "gender_id")
	private int genderId;
	@Column(name = "status_id")
	private int statusId;
	@Column(name = "country_id")
	private int countryId;
	@Column(name = "modified_reason")
	private String modifiedReason;
	@Column(name = "is_admin")
	private boolean isAdmin;
	@Column(name = "is_super_admin")
	private boolean isSuperAdmin;
	@Column(name = "is_end_user")
	private boolean isEndUser;
	@Column(name = "is_system_account")
	private boolean isSystemAccount;
	@Column(name = "is_employee")
	private boolean isEmployee;

	public Member(String firstName, String middleName, String lastName, String otherName, String fullName,
			String dateOfBirth, String nin, String primaryPhone, String secondaryPhone, String primaryEmail,
			String secondaryEmail, String preferredWhatsappNumber, String cardNumber, String password,
			String createDate, String createdByGlobalUserId, String modifiedDate, String modifiedByGlobalUserId,
			int tenantId, int postfixId, int genderId, int statusId, int countryId, String modifiedReason,
			boolean isAdmin, boolean isSuperAdmin, boolean isEndUser, boolean isSystemAccount, boolean isEmployee) {
		// this.globalUserId = grobalUserId;
		// this.id = id;
		this.firstName = firstName;
		this.middleName = middleName;

		this.lastName = lastName;
		this.otherName = otherName;
		this.fullName = fullName;
		this.dateOfBirth = dateOfBirth;
		this.nin = nin;
		this.primaryPhone = primaryPhone;
		this.secondaryPhone = secondaryPhone;
		this.primaryEmail = primaryEmail;
		this.secondaryEmail = secondaryEmail;
		this.preferredWhatsappNumber = preferredWhatsappNumber;
		this.cardNumber = cardNumber;
		this.password = password;
		this.createDate = createDate;
		this.createdByGlobalUserId = createdByGlobalUserId;
		this.modifiedDate = modifiedDate;
		this.modifiedByGlobalUserId = modifiedByGlobalUserId;
		this.tenantId = tenantId;
		this.postfixId = postfixId;
		this.genderId = genderId;
		this.statusId = statusId;
		this.countryId = countryId;
		this.modifiedReason = modifiedReason;
		this.isAdmin = isAdmin;
		this.isSuperAdmin = isSuperAdmin;
		this.isEndUser = isEndUser;
		this.isSystemAccount = isSystemAccount;
		this.isEmployee = isEmployee;

	}

	public Member() {
		super();
	}

	public long getGlobalUserId() {
		return globalUserId;
	}

	public void setGrobalUserId(int grobalUserId) {
		this.globalUserId = grobalUserId;
	}

	public long getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getFirstName() {
		return firstName;
	}

	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}

	public String getMiddleName() {
		return middleName;
	}

	public void setMiddleName(String middleName) {
		this.middleName = middleName;
	}

	public String getLastName() {
		return lastName;
	}

	public void setLastName(String lastName) {
		this.lastName = lastName;
	}

	public String getOtherName() {
		return otherName;
	}

	public void setOtherName(String otherName) {
		this.otherName = otherName;
	}

	public String getFullName() {
		return fullName;
	}

	public void setFullName(String fullName) {
		this.fullName = fullName;
	}

	public String getDateOfBirth() {
		return dateOfBirth;
	}

	public void setDateOfBirth(String dateOfBirth) {
		this.dateOfBirth = dateOfBirth;
	}

	public String getNin() {
		return nin;
	}

	public void setNin(String nin) {
		this.nin = nin;
	}

	public String getPrimaryPhone() {
		return primaryPhone;
	}

	public void setPrimaryPhone(String primaryPhone) {
		this.primaryPhone = primaryPhone;
	}

	public String getSecondaryPhone() {
		return secondaryPhone;
	}

	public void setSecondaryPhone(String secondaryPhone) {
		this.secondaryPhone = secondaryPhone;
	}

	public String getPrimaryEmail() {
		return primaryEmail;
	}

	public void setPrimaryEmail(String primaryEmail) {
		this.primaryEmail = primaryEmail;
	}

	public String getSecondaryEmail() {
		return secondaryEmail;
	}

	public void setSecondaryEmail(String secondaryEmail) {
		this.secondaryEmail = secondaryEmail;
	}

	public String getPreferredWhatsappNumber() {
		return preferredWhatsappNumber;
	}

	public void setPreferredWhatsappNumber(String preferredWhatsappNumber) {
		this.preferredWhatsappNumber = preferredWhatsappNumber;
	}

	public String getCardNumber() {
		return cardNumber;
	}

	public void setCardNumber(String cardNumber) {
		this.cardNumber = cardNumber;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public String getCreateDate() {
		return createDate;
	}

	public void setCreateDate(String createDate) {
		this.createDate = createDate;
	}

	public String getCreatedByGlobalUserId() {
		return createdByGlobalUserId;
	}

	public void setCreatedByGlobalUserId(String createdByGlobalUserId) {
		this.createdByGlobalUserId = createdByGlobalUserId;
	}

	public String getModifiedDate() {
		return modifiedDate;
	}

	public void setModifiedDate(String modifiedDate) {
		this.modifiedDate = modifiedDate;
	}

	public String getModifiedByGlobalUserId() {
		return modifiedByGlobalUserId;
	}

	public void setModifiedByGlobalUserId(String modifiedByGlobalUserId) {
		this.modifiedByGlobalUserId = modifiedByGlobalUserId;
	}

	public int getTenantId() {
		return tenantId;
	}

	public void setTenantId(int tenantId) {
		this.tenantId = tenantId;
	}

	public int getPostfixId() {
		return postfixId;
	}

	public void setPostfixId(int postfixId) {
		this.postfixId = postfixId;
	}

	public int getGenderId() {
		return genderId;
	}

	public void setGenderId(int genderId) {
		this.genderId = genderId;
	}

	public int getStatusId() {
		return statusId;
	}

	public void setStatusId(int statusId) {
		this.statusId = statusId;
	}

	public int getCountryId() {
		return countryId;
	}

	public void setCountryId(int countryId) {
		this.countryId = countryId;
	}

	public String getModifiedReason() {
		return modifiedReason;
	}

	public void setModifiedReason(String modifiedReason) {
		this.modifiedReason = modifiedReason;
	}

	public boolean isAdmin() {
		return isAdmin;
	}

	public void setAdmin(boolean isAdmin) {
		this.isAdmin = isAdmin;
	}

	public boolean isSuperAdmin() {
		return isSuperAdmin;
	}

	public void setSuperAdmin(boolean isSuperAdmin) {
		this.isSuperAdmin = isSuperAdmin;
	}

	public boolean isEndUser() {
		return isEndUser;
	}

	public void setEndUser(boolean isEndUser) {
		this.isEndUser = isEndUser;
	}

	public boolean isSystemAccount() {
		return isSystemAccount;
	}

	public void setSystemAccount(boolean isSystemAccount) {
		this.isSystemAccount = isSystemAccount;
	}

	public boolean isEmployee() {
		return isEmployee;
	}

	public void setEmployee(boolean isEmployee) {
		this.isEmployee = isEmployee;
	}

}
