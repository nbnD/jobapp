package com.flutterjunction.jobms.job;

import java.util.List;


public interface JobService {

  List<Job> findAll();
  void createJob(Job job);
  Job getJobById(Long job);

  boolean deleteJobById(Long id);
  boolean updateJob(Long id,Job job);
}
