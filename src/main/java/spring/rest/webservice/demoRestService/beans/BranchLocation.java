package spring.rest.webservice.demoRestService.beans;

import javax.validation.constraints.Size;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

@ApiModel(description="Includes data such as the branch id, branch name, and branch address")
public class BranchLocation {

	private Integer branchId;
	
	@Size(min=3, message="Branch Name should have at least 3 characters")
	@ApiModelProperty(notes="Branch name should be at least 2 characters long")
	private String branchName;

	private String branchAddress;
	

public BranchLocation() {
	
}
	
public BranchLocation(Integer branchId, String branchName, String branchAddress) {
		super();
		this.branchId = branchId;
		this.branchName = branchName;
		this.branchAddress = branchAddress;
	}

// start getters and setters 
	
	public Integer getBranchId() {
		return branchId;
	}

	public void setBranchId(Integer branchId) {
		this.branchId = branchId;
	}

	public String getBranchName() {
		return branchName;
	}

	public void setBranchName(String branchName) {
		this.branchName = branchName;
	}

	public String getBranchAddress() {
		return branchAddress;
	}

	public void setBranchAddress(String branchAddress) {
		this.branchAddress = branchAddress;
	}

	@Override
	public String toString() {
		return "BranchLocation [branchId=" + branchId + ", branchName=" + branchName + ", branchAddress="
				+ branchAddress + "]";
	}
	
// toString method	
	

	
}
