package com.sparta.springproject.service;

import com.sparta.springproject.dto.StudentRequestDto;
import com.sparta.springproject.dto.StudentResponseDto;
import com.sparta.springproject.repository.Repository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class StudentService {

    private final Repository repository;

    public List<StudentResponseDto> inquiryMemo(){

        return repository.inquiryMemo();
    }

    public int saveMemo(StudentRequestDto requestDto) {

        return repository.saveMemo(requestDto);
    }

    public StudentResponseDto inquiryIdMemo(StudentRequestDto requestDto) {

        return repository.inquiryIdMemo(requestDto);
    }

    public List<StudentResponseDto> modified_day(StudentRequestDto requestDto) {

        return  repository.modified_day(requestDto);
    }

    public List<StudentResponseDto> chargename(StudentRequestDto requestDto) {

        return  repository.chargename(requestDto);
    }

    public List<StudentResponseDto> modicharge(StudentRequestDto requestDto) {

        return  repository.modicharge(requestDto);
    }

    public int tocharge(StudentRequestDto requestDto) {

        return  repository.tocharge(requestDto);
    }

    public int deletePart(StudentRequestDto requestDto) {

        return  repository.deletePart(requestDto);
    }
}
