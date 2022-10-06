package com.example.markerovkaoasis.Utils;

import com.example.markerovkaoasis.entities.CodeMark;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

@Component
@RequiredArgsConstructor
public class FileUtils {

    public List<CodeMark> readToWriteBD(File file) throws FileNotFoundException {
        List<CodeMark> codeMarkList = new ArrayList<>();
        Scanner scannerRead = new Scanner(file);
        String code;
        while (scannerRead.hasNextLine()){
            code = scannerRead.nextLine();
            codeMarkList.add(CodeMark.builder().code(code)
                    .codeProduct(code.substring(0,16))
                    .dataAdded(LocalDate.now())
                    .fileNameAdded(file.getName())
                    .build());
        }
        return codeMarkList;
    }

    public void createPrintFile(List<CodeMark> codeMarkList, String fileNamePrintFile) {
        StringBuilder stringBuilder = new StringBuilder(fileNamePrintFile);
        int countC = 0;
        while (new File(String.valueOf(stringBuilder)).exists()) {
            int i = stringBuilder.indexOf("order");
            if (countC != 0) {
                int countFileNumber = Integer.parseInt(stringBuilder.substring(i - 1, i));
                countFileNumber++;
                stringBuilder.deleteCharAt(i - 1);
                stringBuilder.insert(stringBuilder.indexOf("order"), countFileNumber);
            } else {
                stringBuilder.insert(stringBuilder.indexOf("order"), "1");
            }
            countC++;
        }
        fileNamePrintFile = stringBuilder.toString();
        if (Files.exists(Paths.get(fileNamePrintFile))) {
            new File(fileNamePrintFile);
        }
        try {
            FileWriter writer = new FileWriter(fileNamePrintFile);
            int count = 1;
            for (CodeMark codeMark : codeMarkList) {
                if (count < codeMarkList.size()) {
                    writer.write(codeMark.getCode() + "\n");
                    System.out.println(codeMark.getCode());
                    count++;
                } else {
                    writer.write(codeMark.getCode());
                    System.out.println(codeMark.getCode());
                }
            }
            writer.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}