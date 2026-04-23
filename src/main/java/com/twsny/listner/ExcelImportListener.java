package com.twsny.listner;

import com.alibaba.excel.context.AnalysisContext;
import com.alibaba.excel.event.AnalysisEventListener;
import com.twsny.service.dto.ExcelExampleDTO;

import java.util.ArrayList;
import java.util.List;

public class ExcelImportListener extends AnalysisEventListener<ExcelExampleDTO> {
    private static final int BATCH_SIZE = 500;
    private List<ExcelExampleDTO> list = new ArrayList<>();
    @Override
    public void invoke(ExcelExampleDTO excelExampleDTO, AnalysisContext analysisContext) {
        list.add(excelExampleDTO);
        if (list.size() >= BATCH_SIZE) {
            batchHandle(list);
            list.clear();
        }
    }

    @Override
    public void doAfterAllAnalysed(AnalysisContext analysisContext) {
        if (!list.isEmpty()) {
            batchHandle(list); // 最后一批
        }
    }

    private void batchHandle(List<ExcelExampleDTO> list) {
        System.out.println("list size: " +  list.size()); // 最后一批
    }
}
