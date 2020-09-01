package com.testDynamoDB.testDynamoDB.controllers;

import com.testDynamoDB.testDynamoDB.dtos.StudentDTO;
import com.testDynamoDB.testDynamoDB.service.StudentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/dynamoDb")
public class DynamoDBController {

    @Autowired
    private StudentService studentService;

    @PostMapping("/save")
    public StudentDTO insertIntoDynamoDB (@RequestBody StudentDTO dto) {
        return studentService.insertIntoDynamoDB(dto);
    }

}
