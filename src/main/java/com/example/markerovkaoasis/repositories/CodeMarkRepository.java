package com.example.markerovkaoasis.repositories;

import com.example.markerovkaoasis.entities.CodeMark;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.time.LocalDate;
import java.util.List;

@Repository
public interface CodeMarkRepository extends JpaRepository<CodeMark, Long> {
    CodeMark findByCode(String code);
    List<CodeMark> findAllByCodeProductAndIsPrintFalse(String codeProduct);
    List<CodeMark> findAllByCodeProductAndIsPrintTrueAndDataPrint(String codeProduct, LocalDate localDate);
    CodeMark findFirstByCodeProduct(String codeProduct);
}
