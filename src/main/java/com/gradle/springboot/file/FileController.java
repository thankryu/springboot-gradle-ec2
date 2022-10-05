package com.gradle.springboot.file;

import com.gradle.springboot.util.Move;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.io.File;

@RestController
public class FileController {

    File folder1 = new File("C:\\test1");
    File folder2 = new File("C:\\test2");

    @GetMapping("/move")
    public void moveFile(){
        System.out.println("폴더이동");
        Move.copy(folder1, folder2);
        Move.delete(folder1.toString());
    }
}
