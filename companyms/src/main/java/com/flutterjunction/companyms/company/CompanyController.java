package com.flutterjunction.companyms.company;


import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;


@RestController
@RequestMapping("/companies")
public class CompanyController {
    private final CompanyService companyService;

    public CompanyController(CompanyService companyService) {
        this.companyService = companyService;
    }


    @GetMapping
    ResponseEntity<List<Company>> findAll() {
        return ResponseEntity.ok(companyService.getAllCompanies());

    }

    @PostMapping
    public ResponseEntity<String> createCompany(@RequestBody Company company) {
        companyService.createCompany(company);
        return new ResponseEntity<String>("Company added Successfully", HttpStatus.CREATED);
    }

    @GetMapping("/{id}")
    public ResponseEntity<Company> getCompanyById(@PathVariable Long id) {
        Company company = companyService.getCompanyById(id);
        if (company != null)
            return new ResponseEntity<>(company, HttpStatus.OK);
        else
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<String> deleteCompanyById(@PathVariable Long id) {
        boolean isDeleted = companyService.deleteCompanyById(id);
        return isDeleted
                ? new ResponseEntity<>("Company Successfully deleted", HttpStatus.OK)
                : new ResponseEntity<>("Company Not Found", HttpStatus.NOT_FOUND);
    }

    @PutMapping("/{id}")
    public ResponseEntity<String> updateCompany(@PathVariable Long id, @RequestBody Company updatedCompany) {
        boolean isUpdated = companyService.updateCompany(id, updatedCompany);
        if (isUpdated)
            return new ResponseEntity<>("Companies updated  SuccessFully", HttpStatus.OK);
        else
            return new ResponseEntity<String>(HttpStatus.NOT_FOUND);
    }
}


