package com.bank.model;

import java.time.LocalDate;
import java.time.Period;
import java.util.ArrayList;
import java.util.List;

import jakarta.annotation.Nonnull;
import jakarta.persistence.CascadeType;
import jakarta.persistence.Embedded;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Pattern;

@Entity
@Table(name = "customer")
public class Customer {

	@Id
	@GeneratedValue(strategy = GenerationType.SEQUENCE)
	private int accountId;

	@NotEmpty
	private String name;

	@NotEmpty
	private String userName;

	@NotEmpty
	private String passWord;

	@Embedded
	private Address address;

	@NotEmpty
	private String country;

	@NotEmpty
	private String state;

	@NotEmpty
	@Pattern(regexp = "^\\w+@[a-zA-Z_]+?\\.[a-zA-Z]{2,3}$", message = "Invalid Email format")
	private String email;

	

	@Pattern(regexp = "(^$|[0-9]{10})")
	@NotEmpty
	private String contactNo;

	@Enumerated(EnumType.STRING)
	private AccountType accountType;// salary/saving

	@NotEmpty
	private String branchName;

	@Nonnull
	private Double initDepositAmount;

	@Enumerated(EnumType.STRING)
	private IdProofType idProofType;

	//@NotEmpty
	//@NotNull
	private String IdentificationDocNo;

	@OneToMany(mappedBy = "customer", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
	private List<Loan> loans = new ArrayList<Loan>();

	public int getAccountId() {
		return accountId;
	}

	public void setAccountId(int accountId) {
		this.accountId = accountId;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getUserName() {
		return userName;
	}

	public void setUserName(String userName) {
		this.userName = userName;
	}

	public String getPassWord() {
		return passWord;
	}

	public void setPassWord(String passWord) {
		this.passWord = passWord;
	}

	public Address getAddress() {
		return address;
	}

	public void setAddress(Address address) {
		this.address = address;
	}

	public String getCountry() {
		return country;
	}

	public void setCountry(String country) {
		this.country = country;
	}

	public String getState() {
		return state;
	}

	public void setState(String state) {
		this.state = state;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getContactNo() {
		return contactNo;
	}

	public void setContactNo(String contactNo) {
		this.contactNo = contactNo;
	}

	public AccountType getAccountType() {
		return accountType;
	}

	public void setAccountType(AccountType accountType) {
		this.accountType = accountType;
	}

	public String getBranchName() {
		return branchName;
	}

	public void setBranchName(String branchName) {
		this.branchName = branchName;
	}

	public Double getInitDepositAmount() {
		return initDepositAmount;
	}

	public void setInitDepositAmount(Double initDepositAmount) {
		this.initDepositAmount = initDepositAmount;
	}

	public IdProofType getIdProofType() {
		return idProofType;
	}

	public void setIdProofType(IdProofType idProofType) {
		this.idProofType = idProofType;
	}

	public String getIdentificationDocNo() {
		return IdentificationDocNo;
	}

	public void setIdentificationDocNo(String identificationDocNo) {
		IdentificationDocNo = identificationDocNo;
	}

	public List<Loan> getLoans() {
		return loans;
	}

	public void setLoans(List<Loan> loans) {
		this.loans = loans;
	}

	public void addLoan(Loan loan) {
		loan.setCustomer(this); // this will avoid nested cascade
		this.getLoans().add(loan);
	}

	public String calculateAge(LocalDate dateOfBirth, LocalDate currentDate) {
		// validate inputs ...
		int years = Period.between(dateOfBirth, currentDate).getYears();
		if (years < 18) {
			return "Minor";
		} else if (years >= 18 && years <= 60) {
			return "Normal";
		} else {
			return "Senior";
		}
	}

	// <18 Minor

	// >18 to <=60 Normal

	// >60 Senior

}
