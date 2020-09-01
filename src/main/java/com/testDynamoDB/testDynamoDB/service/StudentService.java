package com.testDynamoDB.testDynamoDB.service;

import com.amazonaws.services.dynamodbv2.datamodeling.DynamoDBMapper;
import com.testDynamoDB.testDynamoDB.dtos.StudentDTO;
import com.testDynamoDB.testDynamoDB.entity.Student;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class StudentService {

    @Autowired
    private DynamoDBMapper mapper;

    public StudentDTO insertIntoDynamoDB (StudentDTO dto) {
        Student student = new Student();
        if (student.getStudentId() != null )
            student.setStudentId(Long.parseLong(dto.getStudentId()));
        student.setFirstName(dto.getFirstName());
        student.setLastName(dto.getLastName());
        mapper.save(student);
        return convertDto(student);
    }

    public StudentDTO convertDto(Student student){
        return new StudentDTO(
                student.getStudentId().toString(),
                student.getFirstName(),
                student.getLastName()
        );
    }
}
