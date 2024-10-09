package com.utility.utility.to.demonstrate.the.file.upload.controller;


import com.utility.utility.to.demonstrate.the.file.upload.dto.Employee;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.time.LocalDate;
import java.util.List;

@CrossOrigin(origins = "http://localhost:3000")
@RestController
public class FileUploadController {

    String UPLOAD_DIR="D:\\workspace\\project";
    @PostMapping("/upload")
    public ResponseEntity<String> handleFileUpload(@RequestParam("file")MultipartFile file) {

        if (file.isEmpty()) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("File cannot be empty");
        }

        try {
            String fileName = file.getOriginalFilename();
            Path path = Paths.get(UPLOAD_DIR);
            if (!Files.exists(path)) {
                Files.createDirectories(path);
            }
            Path resolvePath = path.resolve(fileName);
            file.transferTo(new File(resolvePath.toString()));

        } catch (IOException e) {
            e.printStackTrace();
            ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Try after sometime");
        }

        return ResponseEntity.status(HttpStatus.OK).body("File upload successfully");

    }

    @GetMapping(value = "/detail/{accountId}")
    public ResponseEntity<List<Employee>> getAllEmployee(@PathVariable("accountId") String accountId){
        Employee employee=new Employee();
        employee.setAccessEndDate(LocalDate.now());
        employee.setMachine("MAC");
        employee.setEmpName("Anil");
        return ResponseEntity.status(HttpStatus.OK).body(List.of(employee));
    }

}
