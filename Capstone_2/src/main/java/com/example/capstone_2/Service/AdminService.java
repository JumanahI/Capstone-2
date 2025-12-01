package com.example.capstone_2.Service;

import com.example.capstone_2.Model.Admin;
import com.example.capstone_2.Model.Investor;
import com.example.capstone_2.Model.Student;
import com.example.capstone_2.Repository.AdminRepository;
import com.example.capstone_2.Repository.InvestorRepository;
import com.example.capstone_2.Repository.StudentRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class AdminService {
    private final AdminRepository adminRepository;
    private final StudentRepository studentRepository;
    private final InvestorRepository investorRepository;

    public List<Admin> getAdmins(){
        return adminRepository.findAll();
    }

    public void addAdmin(Admin admin){
        adminRepository.save(admin);
    }

    public boolean updateAdmin(Integer id,Admin admin){
        Admin oldAdmin = adminRepository.findAdminById(id);
        if(oldAdmin == null){
            return false;
        }
        oldAdmin.setFullName(admin.getFullName());
        oldAdmin.setUsername(admin.getUsername());
        oldAdmin.setPassword(admin.getPassword());
        adminRepository.save(oldAdmin);
        return true;
    }

    public boolean deleteAdmin(Integer id){
        Admin admin = adminRepository.findAdminById(id);
        if(admin == null){
            return false;
        }
        adminRepository.delete(admin);
        return true;
    }


    public int setInvestorStatus(Integer adminId,Integer investorId){
        Investor investor = investorRepository.findInvestorById(investorId);
        Admin admin = adminRepository.findAdminById(adminId);
        if(investor == null){
            return 1;
        }
        if(admin == null){
            return 2;
        }
        if(investor.getStatus().equalsIgnoreCase("active")){
            return 3;
        }
        if(investor.getCommercialRegister().length()==10) {
            Investor oldInvestor = investor;
            oldInvestor.setStatus("Active");
            investorRepository.save(oldInvestor);
            return 4;
        }
        return 5;
    }





}
