package com.example.helloworld.service;

import com.example.helloworld.configurations.Configuration;
import com.example.helloworld.dto.ReportDTO;
import com.example.helloworld.entity.Student;
import com.example.helloworld.entity.Teacher;
import com.example.helloworld.enumeration.ReportTemplate;
import net.sf.jasperreports.engine.*;
import net.sf.jasperreports.engine.data.JRBeanCollectionDataSource;
import net.sf.jasperreports.engine.export.JRCsvExporter;
import net.sf.jasperreports.engine.export.JRTextExporter;
import net.sf.jasperreports.engine.export.ooxml.JRXlsxExporter;
import net.sf.jasperreports.export.*;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;

import java.io.File;
import java.nio.file.Paths;
import java.util.*;

public class IReportServiceImpl <T extends IReportService> implements IReportService<T> {
    @Autowired
    Configuration configuration;

    @Autowired
    StudentRepository studentRepository;

    @Autowired
    TeacherRepository teacherRepository;

    List<Student> student;

    private final Logger logger = LogManager.getLogger(this.getClass().getName());

    @Override
    public void generateReport() {

    }

    void generatePdfReport(JasperPrint template, String outputPath) {
        try {
            File file = new File(Paths.get("").toAbsolutePath() + outputPath);
            JasperExportManager.exportReportToPdfFile(template, file.getPath());
            logger.info("generate file " + outputPath + " success");
        } catch (JRException e) {
            e.printStackTrace();
        }
    }

    void generateCsvReport(JasperPrint template, String outputPath){
        try {
            File file = new File(Paths.get("").toAbsolutePath() + outputPath);
            JRCsvExporter csvExporter = new JRCsvExporter();
            csvExporter.setExporterInput(new SimpleExporterInput(template));
            csvExporter.setExporterOutput(new SimpleWriterExporterOutput(file));
            SimpleCsvReportConfiguration reportConfiguration = new SimpleCsvReportConfiguration();
            csvExporter.setConfiguration(reportConfiguration);
            csvExporter.exportReport();
            logger.info("Generate csv report success");
        } catch (JRException e) {
            e.printStackTrace();
            logger.info("Generate csv report failed");
        }
    }

    void generateTxtReport(JasperPrint template, String outputPath) {
        try {
            File file = new File(Paths.get("").toAbsolutePath() + outputPath);
            JRTextExporter textExporter = new JRTextExporter();
            textExporter.setExporterInput(new SimpleExporterInput(template));
            textExporter.setExporterOutput(new SimpleWriterExporterOutput(file));
            SimpleTextExporterConfiguration exportConfig = new SimpleTextExporterConfiguration();
            textExporter.setConfiguration(exportConfig);
            SimpleTextReportConfiguration reportConfig = new SimpleTextReportConfiguration();
            reportConfig.setPageWidthInChars(200);
            reportConfig.setPageHeightInChars(10);
            textExporter.setConfiguration(reportConfig);
            textExporter.exportReport();
            logger.info("Generate text report success");
        } catch (JRException e) {
            e.printStackTrace();
            logger.info("Generate text report failed");
        }
    }

    void generateXlsxReport(List<JasperPrint> sheets, String outputPath) {
        try {
            File file = new File(Paths.get("").toAbsolutePath() + outputPath);
            JRXlsxExporter xlsxExporter = new JRXlsxExporter();
            xlsxExporter.setExporterInput(SimpleExporterInput.getInstance(sheets));
            xlsxExporter.setExporterOutput(new SimpleOutputStreamExporterOutput(file));

            SimpleXlsxReportConfiguration configuration = new SimpleXlsxReportConfiguration();
//            configuration.setSheetNames(); //sheets names is an array of the different names.
            configuration.setWhitePageBackground(false);
            configuration.setOnePagePerSheet(false);
            configuration.setIgnoreGraphics(false);
            configuration.setIgnorePageMargins(true);
            configuration.setAutoFitPageHeight(true);
            xlsxExporter.setConfiguration(configuration);
            xlsxExporter.exportReport();
        } catch (JRException e) {
            e.printStackTrace();
        }
    }

    JasperPrint generateXlsxReportTemplate(){
        try {
            String sourceFile = configuration.getTemplatePath() + ReportTemplate.TEACHER_XLSX.getTemplateFile();
            JasperReport jasperReport = JasperCompileManager.compileReport(sourceFile);
            JRBeanCollectionDataSource dataSource = new JRBeanCollectionDataSource(this.getXlsxDatasource());
            Map<String, Object> param = new HashMap<>();
            param.put("createdBy", "DIGIO");
            return JasperFillManager.fillReport(jasperReport, param, dataSource);
        } catch (JRException e) {
            e.printStackTrace();
            return null;
        }
    }

    JasperPrint generatePdfReportTemplate(Integer id) {
        try {
            String sourceFile = configuration.getTemplatePath() + ReportTemplate.TEACHER_PDF.getTemplateFile();
            JasperReport jasperReport = JasperCompileManager.compileReport(sourceFile);
            JRBeanCollectionDataSource dataSource = new JRBeanCollectionDataSource(getPdfDatasource(id));
            Map<String, Object> param = new HashMap<>();
            param.put("createdBy", "DIGIO");
            return JasperFillManager.fillReport(jasperReport, param, dataSource);
        } catch (JRException e) {
            e.printStackTrace();
            return null;
        }
    }

    JasperPrint generateCsvReportTemplate(){
        try {
            student = studentRepository.findAll();
            if (!student.isEmpty()){
                String sourceFile = configuration.getTemplatePath() + ReportTemplate.STUDENT_CSV.getTemplateFile();
                JasperReport jasperReport = JasperCompileManager.compileReport(sourceFile);
                JRBeanCollectionDataSource dataSource = new JRBeanCollectionDataSource(student);
                Map<String, Object> param = new HashMap<>();
                param.put("createdBy", "DIGIO");
                return JasperFillManager.fillReport(jasperReport, param, dataSource);
            }else return null;

        } catch (JRException e) {
            e.printStackTrace();
            return null;
        }
    }

    JasperPrint generateTxtReportTemplate(){
        try {
            List<ReportDTO> reportDTOS = getTxtDataSource();
                String sourceFile = configuration.getTemplatePath() + ReportTemplate.STUDENT_TXT.getTemplateFile();
                JasperReport jasperReport = JasperCompileManager.compileReport(sourceFile);
                reportDTOS.sort(Comparator.comparing(ReportDTO::getStudentId));
                JRBeanCollectionDataSource dataSource = new JRBeanCollectionDataSource(reportDTOS);
                Map<String, Object> param = new HashMap<>();
                param.put("createdBy", "DGIO");
                return JasperFillManager.fillReport(jasperReport, param, dataSource);
        } catch (JRException e) {
            e.printStackTrace();
            return null;
        }
    }

    List<ReportDTO> getPdfDatasource(Integer TeacherId){
        List<ReportDTO> reportDTOList = new ArrayList<ReportDTO>();
        Optional<Teacher> teachers = teacherRepository.findById(TeacherId);
        List<Student> students = studentRepository.findByTeacherId(TeacherId);
        if(teachers.isPresent()){
            reportDTOList.add(new ReportDTO(teachers.get().getId(),teachers.get().getTeacherName(),teachers.get().getTeacherLastname(),teachers.get().getSubject().getSubjectName(),students));
            return reportDTOList;
        }else return null;

    }

    List<ReportDTO> getXlsxDatasource(){
        List<ReportDTO> reportDTOList = new ArrayList<ReportDTO>();
        List<Teacher> teachers = teacherRepository.findAll();
        if(!teachers.isEmpty()){
        for (Teacher t:teachers){
            reportDTOList.add(new ReportDTO(t.getId(),t.getTeacherName(),t.getTeacherLastname(),t.getSubject().getSubjectName(),studentRepository.findByTeacherId(t.getId())));
        }
        logger.info("Teacher Size = "+reportDTOList.size());
            return reportDTOList;
        }else return null;

    }

    List<ReportDTO> getTxtDataSource() {
       List<Student> student = studentRepository.findStudentsByTeacherIdEqualsOrderById();
        List<ReportDTO> reportDTOList = new ArrayList<ReportDTO>();

        for (Student s:student){
            reportDTOList.add(new ReportDTO(
                    s.getId(),
                    s.getTeacher().getId(),
                    s.getStudentName(),
                    s.getStudentLastname(),
                    s.getStudentPhone(),
                    s.getStudentProgram(),
                    s.getTeacher().getTeacherName(),
                    s.getTeacher().getTeacherLastname()
            ));
        }
        return reportDTOList;
    }
}
