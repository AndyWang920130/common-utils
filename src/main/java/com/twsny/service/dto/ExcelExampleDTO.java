package com.twsny.service.dto;

import com.alibaba.excel.annotation.ExcelProperty;
import com.twsny.utils.random.RandomUtil;

public class ExcelExampleDTO {
    @ExcelProperty("ID")
    private Long id;

    @ExcelProperty("序列号")
    private String serialNumber;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getSerialNumber() {
        return serialNumber;
    }

    public void setSerialNumber(String serialNumber) {
        this.serialNumber = serialNumber;
    }
}
