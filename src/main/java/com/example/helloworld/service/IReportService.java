package com.example.helloworld.service;

import org.springframework.stereotype.Service;

@Service
public interface IReportService  <T extends IReportService>{
    void generateReport();
}
