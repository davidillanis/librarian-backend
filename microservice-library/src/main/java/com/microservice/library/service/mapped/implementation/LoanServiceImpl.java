package com.microservice.library.service.mapped.implementation;

import com.microservice.library.model.entity.LoanEntity;
import com.microservice.library.model.repository.LoanRepository;
import com.microservice.library.service.mapped.LoanService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class LoanServiceImpl implements LoanService {
    @Autowired
    private LoanRepository loanRepository;


    @Override
    public boolean createEntity(LoanEntity obj) {
        if(obj!=null){
            loanRepository.save(obj);
            return true;
        }
        return false;
    }

    @Override
    public List<LoanEntity> getListEntity() {
        return loanRepository.findAll();
    }

    @Override
    public LoanEntity getEntity(Integer id) {
        return loanRepository.findById(id).orElse(null);
    }

    @Override
    public boolean updateEntity(LoanEntity obj) {
        LoanEntity loanEntity=loanRepository.findById(obj.getIdPrest()).orElse(null);
        if(loanEntity!=null){
            loanEntity.setFechPres(obj.getFechPres());
            loanEntity.setFechDevoPres(obj.getFechDevoPres());
            loanEntity.setObsePres(obj.getObsePres());
            loanEntity.setEstaPres(obj.getEstaPres());
            loanEntity.setCopyBookEntity(obj.getCopyBookEntity());
            loanEntity.setLibrarianEntity(obj.getLibrarianEntity());
            loanEntity.setStudentEntity(obj.getStudentEntity());
            loanRepository.save(loanEntity);
            return true;
        }
        return false;
    }

    @Override
    public boolean deleteEntity(Integer id) {
        LoanEntity loanEntity=loanRepository.findById(id).orElse(null);
        if(loanEntity!=null){
            loanRepository.delete(loanEntity);
            return true;
        }
        return false;
    }
}
