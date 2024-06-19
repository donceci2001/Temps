package bg.temps.jobportal.services;

import bg.temps.jobportal.models.CompanyModel;
import bg.temps.jobportal.repositories.CompanyRepository;
import bg.temps.jobportal.repositories.RoleRepository;
import bg.temps.jobportal.repositories.UserRepository;
import bg.temps.jobportal.entities.Company;
import bg.temps.jobportal.entities.Role;
import bg.temps.jobportal.entities.User;
import bg.temps.jobportal.models.AdminModel;
import bg.temps.jobportal.models.CompanyAdminModel;
import bg.temps.jobportal.models.ParseObjectModel;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;


@Service
@Transactional
public class AdminService {
    @Autowired
    public CompanyRepository companyRepository;

    @Autowired
    public RoleRepository roleRepository;
    @Autowired
    public UserRepository userRepository;
    public String addCompany(CompanyModel companyModel){
        String incomingCompanyName=companyModel.getCompanyName();
        Company isCompanyExist= companyRepository.findCompanyByNameIgnoreCase(incomingCompanyName);
        if(isCompanyExist!=null){
            return "Company Already Exist";
        }else {
            companyRepository.save(companyModel.dissemble());
            return "Company Added Successfully";

        }
    }
    public String addCompanyAdmin(CompanyAdminModel companyAdminModel){
        String incomingCompanyName= companyAdminModel.getCompanyName();
        Company isCompanyExist=companyRepository.findCompanyByNameIgnoreCase(incomingCompanyName);
        String incomingUserEmail= companyAdminModel.getEmail();
        String role="Company";
        Role checkRole=roleRepository.findByRoleNameIgnoreCase(role);
        User isUserAlreadyExist=userRepository.findUserByEmail(incomingUserEmail);
        if(isCompanyExist!=null ){
            if (isUserAlreadyExist!=null){
                return "user already exist";
            }
            else{
                User user= companyAdminModel.dissemble();
                user.setCompany(isCompanyExist);
                user.setRole(checkRole);
                userRepository.save(user);
                return "User Added";

            }
        }else{
            return "Company not found";
        }
    }
    public List<CompanyModel> showAllCompany(){
        List<CompanyModel> listOfCompany=new ArrayList<>();
        for (Company company : companyRepository.findAll()) {
            listOfCompany.add(new CompanyModel().assemble(company));
        }
        return listOfCompany;
    }
    public List<AdminModel> viewCompanyUser(){
        List<User> allUsers = userRepository.findAll();
        List<AdminModel> filteredUsers = new ArrayList<>();
        for (User user : allUsers) {
            if (user.getCompany() != null) {
                filteredUsers.add(new AdminModel().assemble(user));
            }
        }

        return filteredUsers;
    }
    public String removeCompany(long id) {
    Optional<Company> optionalCompany = companyRepository.findById(id);
    if (optionalCompany.isEmpty()) {
        return "No company found";
    } else {
        Company company = optionalCompany.get();
        companyRepository.delete(company);

        return "Company and associated users deleted successfully.";
    }
}
      public String updateCompanyDetails(CompanyModel companyModel){
        Company company=companyRepository.findCompanyById(companyModel.getId());
        company.setName(companyModel.getCompanyName());
        company.setDescription(companyModel.getDescription());
        companyRepository.save(company);
        return "Update Successfully";
    }
    public String removeUser(ParseObjectModel parseObjectModel) {
        Optional<User> optionalUser = userRepository.findById(parseObjectModel.getId());
        if (optionalUser.isEmpty()) {
            return "No User found";
        } else {
            User user = optionalUser.get();
            userRepository.delete(user);

            return "User deleted successfully.";
        }
    }
    public String updateUserDetails(AdminModel adminModel){
        User user=userRepository.findUserById(adminModel.getId());
        user.setEmail(adminModel.getEmail());
        user.setFirstName(adminModel.getFirstName());
        user.setLastName(adminModel.getLastName());
        userRepository.save(user);
        return "Update Successfully";
    }
}
