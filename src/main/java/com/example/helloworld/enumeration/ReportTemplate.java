package com.example.helloworld.enumeration;

public enum ReportTemplate {
    STUDENT_CSV("/student-list-csv.jrxml", ".csv"),
    STUDENT_TXT("/student-list-txt.jrxml",".txt"),
    TEACHER_PDF("/teacher-list-pdf.jrxml",".pdf"),
    TEACHER_XLSX("/teacher-list-xlsx.jrxml",".xlsx");
    ;

    private String templateFile;
    private String type;



    ReportTemplate(String template,String type) {
        this.templateFile = template;
        this.type = type;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getTemplateFile() {
        return templateFile;
    }

    public void setTemplateFile(String templateFile) {
        this.templateFile = templateFile;
    }
}
