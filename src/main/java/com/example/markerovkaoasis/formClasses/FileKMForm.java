package com.example.markerovkaoasis.formClasses;

import lombok.*;
import org.springframework.stereotype.Component;

import java.util.Date;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
@ToString
@Component
public class FileKMForm {
    public Long id;
    public int numberKM;
    public Date data;

    public FileKMForm(Long id){
        this.id = id;
    }

}
