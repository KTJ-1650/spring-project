package com.sparta.springproject.controller;


import com.sparta.springproject.dto.StudentRequestDto;
import com.sparta.springproject.dto.StudentResponseDto;
import com.sparta.springproject.service.StudentService;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api")
@RequiredArgsConstructor
public class StudentController {
   @Autowired

    private final StudentService studentService;



    //1단계 할일, 담당자명, 비밀번호, 작성/수정일을 저장할 수 있습니다.
    @PostMapping("/memos")
    public  int saveMemo(@RequestBody StudentRequestDto requestDto){

        return studentService.saveMemo(requestDto);

    }

    //2단계 1)선택한 일정 단건의 정보를 조회할 수 있습니다.
    // 2) 일정의 고유 식별자(ID)를 사용하여 조회합니다.
    @GetMapping("/inquiry")
    public StudentResponseDto inquiryIdMemo(@RequestBody StudentRequestDto requestDto){

        return studentService.inquiryIdMemo(requestDto);
    }

    // 3단계 1)등록된일정 전부 조회
    @GetMapping("/memos")
    public List<StudentResponseDto> inquiryMemo(){

        System.out.println("조회되었습니다.");

        return studentService.inquiryMemo();
    }

    //3단계 2)수정일 조회
    @GetMapping("/modified")
    public List<StudentResponseDto> modified_day(StudentRequestDto requestDto){

        return studentService.modified_day(requestDto);
    }

    //3단계. 3)담당자 조회
    @GetMapping("/chargename")
    public List<StudentResponseDto> chargename(StudentRequestDto requestDto){

        return  studentService.chargename(requestDto);
    }

    //3단계 수정일,담당일의 조회
    @GetMapping("/modicharge")
    public  List<StudentResponseDto> modicharge(StudentRequestDto requestDto){

        return  studentService.modicharge(requestDto);
    }

    //4단계
    //1) 할일,담당자만 수정 가능
    //a) 비밀번호함께 전달 ( 특정 id에 password 입력)
    //b) 작성일 (write_day) 변경 x, 수정일(modified_day) 수정시점 변경
    //2)수정된 일정의 정보를 반환 받아 확인할 수 있습니다
    @PutMapping
    public int tocharge(StudentRequestDto requestDto){

        return  studentService.tocharge(requestDto);
    }

    //5단계 선택할 일정 삭제
    //a)서버에 일정 수정을 요청할 때 비밀번호를 함께 전달합니다.
    @DeleteMapping
    public int deletePart(StudentRequestDto requestDto){
        return  studentService.deletePart(requestDto);
    }


}