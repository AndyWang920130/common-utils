package com.twsny.service;


import com.alibaba.excel.EasyExcel;
import com.twsny.listner.ExcelImportListener;
import com.twsny.service.dto.ExcelExampleDTO;
import com.twsny.utils.random.RandomUtil;
import jakarta.servlet.http.HttpServletResponse;
import org.apache.xmlbeans.impl.xb.xsdschema.Public;
import org.springframework.stereotype.Service;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.List;

@Service
public class ExcelService {
    private static List<ExcelExampleDTO> excelExampleDTOList;

    static {
        excelExampleDTOList = new ArrayList<>();
        ExcelExampleDTO dto1 = new ExcelExampleDTO();
        dto1.setId(1L);
        dto1.setSerialNumber(RandomUtil.generate(5));
        excelExampleDTOList.add(dto1);
    }


    public void exportExcel(HttpServletResponse response) throws IOException {
        EasyExcel
                .write(response.getOutputStream(), ExcelExampleDTO.class)
                .sheet("模板列表")
                .doWrite(excelExampleDTOList);
    }

    public void importExcel(MultipartFile file) throws IOException {
        InputStream inputStream = file.getInputStream();
        EasyExcel.read(inputStream, ExcelExampleDTO.class, new ExcelImportListener())
                .sheet()
                .doRead();
    }
}
