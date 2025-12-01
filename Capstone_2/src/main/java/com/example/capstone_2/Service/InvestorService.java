package com.example.capstone_2.Service;

import com.example.capstone_2.Model.Investor;
import com.example.capstone_2.Repository.InvestorRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class InvestorService {

    private final InvestorRepository investorRepository;

    public List<Investor> getInvestors(){
        return investorRepository.findAll();
    }

    public void addInvestor(Investor investor){
        Investor oldInvestor = investor;
        oldInvestor.setStatus("Pending");
        investorRepository.save(oldInvestor);
    }


    public boolean updateInvestor(Integer id,Investor investor){
        Investor oldInvestor = investorRepository.findInvestorById(id);
        if(oldInvestor == null){
            return false;
        }
        oldInvestor.setFullName(investor.getFullName());
        oldInvestor.setUsername(investor.getUsername());
        oldInvestor.setPassword(investor.getPassword());
        oldInvestor.setEmail(investor.getEmail());
        oldInvestor.setCommercialRegister(investor.getCommercialRegister());
        investorRepository.save(oldInvestor);
        return true;
    }

    public boolean deleteInvestor(Integer id){
        Investor investor = investorRepository.findInvestorById(id);
        if(investor == null){
            return false;
        }
        investorRepository.delete(investor);
        return true;
    }
}
