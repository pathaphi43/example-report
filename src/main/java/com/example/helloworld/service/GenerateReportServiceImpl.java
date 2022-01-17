package com.example.helloworld.service;

import com.example.helloworld.enumeration.ReportTemplate;
import net.sf.jasperreports.engine.JasperPrint;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.stereotype.Service;

import java.text.SimpleDateFormat;
import java.util.Arrays;
import java.util.Collections;
import java.util.Date;
import java.util.List;

@Service
public class GenerateReportServiceImpl extends IReportServiceImpl<GenerateReportService> implements GenerateReportService {
    private final Logger logger = LogManager.getLogger(this.getClass().getName());
    private List<String> srcFiles;
    private static Date queryDate;
    private String formatDate = "yyyyMMdd";

    public String getFormatDate() {
        return formatDate;
    }

    public void setFormatDate(String formatDate) {
        this.formatDate = formatDate;
    }

    @Override
    public void generateReport() {
    }

    public String exportXlsx() {
        StringBuilder outputPath = new StringBuilder();
        outputPath.append("/");
        outputPath.append(configuration.getReportPath());
        outputPath.append("/");
        outputPath.append("teacher-list" + ReportTemplate.TEACHER_XLSX.getType());
        logger.info("setup output file name success");

        JasperPrint reportTemplate = this.generateXlsxReportTemplate();
        this.generateXlsxReport(Collections.singletonList(reportTemplate), outputPath.toString());

        logger.info("jasper print prepare success");
        return outputPath.toString();
    }


    public String exportPdf(Integer id) {
        StringBuilder outputPath = new StringBuilder();
        outputPath.append("/");
        outputPath.append(configuration.getReportPath());
        outputPath.append("/");
        outputPath.append("teacher-list");
        outputPath.append(ReportTemplate.TEACHER_PDF.getType());
        logger.info("setup output file name success");

        JasperPrint reportTemplate = this.generatePdfReportTemplate(id);
        logger.info("PDF template print teacher id " + id + " success");

        generatePdfReport(reportTemplate, outputPath.toString());
        logger.info("PDF file teacher id " + id + " generate success");

        return outputPath.toString();

    }

    public String exportCsv() {
        StringBuilder outputPath = new StringBuilder();
        outputPath.append("/");
        outputPath.append(configuration.getReportPath());
        outputPath.append("/");
        outputPath.append("student-list");
        outputPath.append(ReportTemplate.STUDENT_CSV.getType());
        logger.info("setup output file name success");

        JasperPrint reportTemplate = this.generateCsvReportTemplate();
        logger.info("setup jasper template file csv success");


        this.generateCsvReport(reportTemplate, outputPath.toString());

        return outputPath.toString();

    }

    public String exportTxt() {
        StringBuilder outputPath = new StringBuilder();
        outputPath.append("/");
        outputPath.append(configuration.getReportPath());
        outputPath.append("/");
        outputPath.append("student-list");
        outputPath.append(ReportTemplate.STUDENT_TXT.getType());
        System.out.println(outputPath.toString());
        logger.info("setup output file name success");

        JasperPrint reportTemplate = this.generateTxtReportTemplate();
        logger.info("setup jasper template file txt success");

        this.generateTxtReport(reportTemplate, outputPath.toString());

        return outputPath.toString();

    }
}
