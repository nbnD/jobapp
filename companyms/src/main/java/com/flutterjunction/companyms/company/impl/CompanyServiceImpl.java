package com.flutterjunction.companyms.company.impl;


import com.flutterjunction.companyms.company.Company;
import com.flutterjunction.companyms.company.CompanyRepository;
import com.flutterjunction.companyms.company.CompanyService;
import jakarta.transaction.Transactional;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@Transactional
public class CompanyServiceImpl implements CompanyService {
    private static final Log log = LogFactory.getLog(CompanyServiceImpl.class);
    CompanyRepository companyRepository;

    public CompanyServiceImpl(CompanyRepository companyRepository) {
        this.companyRepository = companyRepository;
    }

    @Override
    public List<Company> getAllCompanies() {
        return companyRepository.findAll();
    }

    @Override
    public void createCompany(Company company) {
        companyRepository.save(company);
    }

    @Override
    public Company getCompanyById(Long id) {
        return companyRepository.findById(id).orElse(null);
    }


    @Override
    public boolean deleteCompanyById(Long id) {
        if (companyRepository.existsById(id)) {
            companyRepository.deleteById(id);
            return true;
        } else {
            return false;
        }

    }

    @Override
    public boolean updateCompany(Long id, Company updatedCompany) {
        Optional<Company> comOptional = companyRepository.findById(id);

        if (comOptional.isPresent()) {
            Company company = comOptional.get();
            company.setName(updatedCompany.getName());
            company.setDescription(updatedCompany.getDescription());
            return true;
        }
        return false;
    }
}
