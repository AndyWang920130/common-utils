package com.twsny.rest;

import com.twsny.service.ExcelService;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;

@RestController
@RequestMapping("/api/v1")
public class ExcelResource {
    private final ExcelService excelService;

    public ExcelResource(ExcelService excelService) {
        this.excelService = excelService;
    }

    @GetMapping("/export")
    public void export(HttpServletResponse response) throws IOException {
        response.setContentType("application/vnd.ms-excel");
        response.setCharacterEncoding("utf-8");
        response.setHeader("Content-disposition", "attachment;filename=excelExample.xlsx");
        excelService.exportExcel(response);
    }

    @PostMapping("/import")
    public void export(@RequestParam("file") MultipartFile file) throws IOException {
        excelService.importExcel(file);
    }
}
