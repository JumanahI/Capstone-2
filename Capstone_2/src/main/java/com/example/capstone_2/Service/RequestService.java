package com.example.capstone_2.Service;

import com.example.capstone_2.Model.Investor;
import com.example.capstone_2.Model.Project;
import com.example.capstone_2.Model.Request;
import com.example.capstone_2.Model.Student;
import com.example.capstone_2.Repository.InvestorRepository;
import com.example.capstone_2.Repository.ProjectRepository;
import com.example.capstone_2.Repository.RequestRepository;
import com.example.capstone_2.Repository.StudentRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class RequestService {

    private final RequestRepository requestRepository;
    private final InvestorRepository investorRepository;
    private final ProjectRepository projectRepository;
    private final StudentRepository studentRepository;

    public List<Request> getAllRequests() {
        return requestRepository.findAll();
    }

    public int addRequest(Request request) {
        Investor investor = investorRepository.findInvestorById(request.getInvestorId());
        if(investor == null){
            return 1;
        }
        Project project = projectRepository.findProjectById(request.getProjectId());
        if(project == null){
            return 2;
        }
        if(investor.getStatus().equalsIgnoreCase("Pending")){
            return 3;
        }
        Request oldRequest = request;
        oldRequest.setStatus("Pending");
        requestRepository.save(oldRequest);
        return 4;

    }

    public int updateRequest(Integer id, Request request) {
        Request oldRequest = requestRepository.findRequestById(id);
        if (oldRequest == null) return 1;

        Investor investor = investorRepository.findInvestorById(request.getInvestorId());
        if(investor == null){
            return 2;
        }
        Project project = projectRepository.findProjectById(request.getProjectId());
        if (project == null){
            return 3;
        }
        oldRequest.setInvestorId(request.getInvestorId());
        oldRequest.setProjectId(request.getProjectId());
        oldRequest.setContent(request.getContent());
        oldRequest.setStatus(request.getStatus());

        requestRepository.save(oldRequest);
        return 4;
    }

    public boolean deleteRequest(Integer id) {
        Request request = requestRepository.findRequestById(id);
        if (request == null) return false;

        requestRepository.delete(request);
        return true;
    }

    public List<Request> getRequestsByInvestorId(Integer investorId){
        return requestRepository.getRequestsByInvestorId(investorId);
    }

    public List<Request> getRequestsByProjectId(Integer projectId){
        return requestRepository.findRequestByProjectId(projectId);
    }

    public int approveRequest(Integer studentId,Integer id){
        Request oldRequest = requestRepository.findRequestById(id);
        Project project = projectRepository.findProjectById(oldRequest.getProjectId());
        Student student = studentRepository.findStudentById(studentId);
        if(!student.getId().equals(project.getStudentId())){
            return 1;
        }
        if (oldRequest == null) return 2;

        oldRequest.setStatus("Accepted");
        requestRepository.save(oldRequest);
        return 3;
    }


    public int rejectRequest(Integer studentId,Integer id){
        Request oldRequest = requestRepository.findRequestById(id);
        Project project = projectRepository.findProjectById(oldRequest.getProjectId());
        Student student = studentRepository.findStudentById(studentId);
        if(!student.getId().equals(project.getStudentId())){
            return 1;
        }
        if (oldRequest == null) return 2;

        oldRequest.setStatus("Rejected");
        requestRepository.save(oldRequest);
        return 3;
    }


}
