package com.jdbc.template.controller;

import com.jdbc.template.template_batch.NameParameterJdbcTemplateBatch;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class JdbcTemplateBatchController {

    private final NameParameterJdbcTemplateBatch nameParameterJdbcTemplateBatch;

    public JdbcTemplateBatchController(NameParameterJdbcTemplateBatch nameParameterJdbcTemplateBatch) {
        this.nameParameterJdbcTemplateBatch = nameParameterJdbcTemplateBatch;
    }

    @GetMapping("/batch")
    public String updateBatch() {
        this.nameParameterJdbcTemplateBatch.patchUpdate();
        return "Update batch ok";
    }
}
