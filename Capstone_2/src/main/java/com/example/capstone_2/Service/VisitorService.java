package com.example.capstone_2.Service;

import com.example.capstone_2.Model.Visitor;
import com.example.capstone_2.Repository.VisitorRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class VisitorService {

    private final VisitorRepository visitorRepository;

    public List<Visitor> getVisitors(){
        return visitorRepository.findAll();
    }

    public void addVisitor(Visitor visitor){
        visitorRepository.save(visitor);
    }

    public boolean updateVisitor(Integer id,Visitor visitor){
        Visitor oldVisitor = visitorRepository.findVisitorById(id);
        if(oldVisitor == null){
            return false;
        }
        oldVisitor.setFullName(visitor.getFullName());
        oldVisitor.setUsername(visitor.getUsername());
        oldVisitor.setPassword(visitor.getPassword());
        visitorRepository.save(oldVisitor);
        return true;
    }

    public boolean deleteVisitor(Integer id){
        Visitor visitor = visitorRepository.findVisitorById(id);
        if(visitor == null){
            return false;
        }
        visitorRepository.delete(visitor);
        return true;
    }


}
