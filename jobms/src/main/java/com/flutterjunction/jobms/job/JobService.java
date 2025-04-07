package com.flutterjunction.jobms.job;

import com.flutterjunction.jobms.job.dto.JobWithCompanyDTO;

import java.util.List;


public interface JobService {

  List<JobWithCompanyDTO> findAll();
  void createJob(Job job);
  JobWithCompanyDTO getJobById(Long job);

  boolean deleteJobById(Long id);
  boolean updateJob(Long id,Job job);
}
