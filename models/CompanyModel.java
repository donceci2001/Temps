package bg.temps.jobportal.models;

import bg.temps.jobportal.entities.Company;
import lombok.Data;

@Data
public class CompanyModel {
private long id;
private String companyName;
private String description;
public Company dissemble(){
    Company company=new Company();
    company.setId(this.id);
    company.setName(this.companyName);
    company.setDescription(this.description);
    return company;
}
public CompanyModel assemble(Company company){
    CompanyModel companyModel=new CompanyModel();
    companyModel.setId(company.getId());
    companyModel.setCompanyName(company.getName());
    companyModel.setDescription(company.getDescription());
    return companyModel;
}

}
