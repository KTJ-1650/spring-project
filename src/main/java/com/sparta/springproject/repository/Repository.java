package com.sparta.springproject.repository;


import com.sparta.springproject.dto.StudentRequestDto;
import com.sparta.springproject.dto.StudentResponseDto;
import lombok.RequiredArgsConstructor;
import org.springframework.jdbc.core.JdbcTemplate;

import java.util.List;

@org.springframework.stereotype.Repository
@RequiredArgsConstructor
public class Repository {

    private final JdbcTemplate jdbcTemplate;


    // 3단계 1)등록된 정보 목록 전부 조회
    public List<StudentResponseDto> inquiryMemo() {

        String sql = "SELECT * FROM major";

        return jdbcTemplate.query(sql, (rs, rowNum) -> {
            StudentResponseDto projectName = new StudentResponseDto();

            projectName.setStudent_id(rs.getInt("student_id"));
            projectName.setTo_do(rs.getString("to_do"));
            projectName.setCharge_name(rs.getString("charge_name"));
            projectName.setPassword(rs.getString("password"));
            projectName.setWrite_day(rs.getString("write_day"));
            projectName.setModified_day(rs.getString("modified_day"));

            return projectName;

        });
    }

    //1 단계 정보 저장 (할일,당담자명,비밀번호,작성/수정일 저장)
    public int saveMemo(StudentRequestDto requestDto) {

        String sql = "INSERT INTO major (to_do, charge_name,password,write_day,modified_day) VALUES (?, ?, ?, ?, ?);";

        return jdbcTemplate.update(sql,
                requestDto.getTo_do(),
                requestDto.getCharge_name(),
                requestDto.getPassword(),
                requestDto.getWrite_day(),
                requestDto.getModified_day()
        );
    }

    //2단계 1)선택한 일정 단건의 정보를 조회할 수 있습니다.
    // 2) 일정의 고유 식별자(ID)를 사용하여 조회합니다.
    public StudentResponseDto inquiryIdMemo(StudentRequestDto requestDto) {

        String sql = "SELECT * FROM major where student_id=?";

        return  jdbcTemplate.queryForObject
                (sql, new Object[]{requestDto.getStudent_id()}, (rs, rowNum) ->
        {
            StudentResponseDto projectName = new StudentResponseDto();

            projectName.setStudent_id(rs.getInt("student_id"));
            projectName.setTo_do(rs.getString("to_do"));
            projectName.setCharge_name(rs.getString("charge_name"));
            projectName.setWrite_day(rs.getString("write_day"));
            projectName.setModified_day(rs.getString("modified_day"));

            return projectName;
        });
    }


    // 1)등록된 정보 목록 전부 조회
    // 2) 수정일 조회
    // 3) 담당자 조회
    // 4) 수정일과 담당자의 조회


    //2)수정일 조회
    public List<StudentResponseDto> modified_day(StudentRequestDto requestDto) {

        String sql = "SELECT * FROM major where modified_day=?";

        return jdbcTemplate.query(sql,new Object[]{requestDto.getModified_day(),}, (rs, rowNum) -> {
            StudentResponseDto modify = new StudentResponseDto();

            modify.setStudent_id(rs.getInt("student_id"));
            modify.setTo_do(rs.getString("to_do"));
            modify.setCharge_name(rs.getString("charge_name"));
            modify.setPassword(rs.getString("password"));
            modify.setWrite_day(rs.getString("write_day"));
            modify.setModified_day(rs.getString("modified_day"));

            return modify;

        });
    }

    //3) 담당자 조회
    public List<StudentResponseDto> chargename(StudentRequestDto requestDto) {

        String sql = "SELECT * FROM major where modified_day=?";

        return jdbcTemplate.query(sql,new Object[]{requestDto.getModified_day(),}, (rs, rowNum) -> {
            StudentResponseDto modify = new StudentResponseDto();

            modify.setStudent_id(rs.getInt("student_id"));
            modify.setTo_do(rs.getString("to_do"));
            modify.setCharge_name(rs.getString("charge_name"));
            modify.setPassword(rs.getString("password"));
            modify.setWrite_day(rs.getString("write_day"));
            modify.setModified_day(rs.getString("modified_day"));

            return modify;
        });
    }

    // 4)수정일과 담당일의 조회
    public List<StudentResponseDto> modicharge(StudentRequestDto requestDto) {

        String sql = "SELECT * FROM major where modified_day=? and charge_name = ?";

        return jdbcTemplate.query(sql,new Object[]{requestDto.getModified_day(),requestDto.getCharge_name()}, (rs, rowNum) -> {
                    StudentResponseDto modicharge = new StudentResponseDto();

                    modicharge.setStudent_id(rs.getInt("student_id"));
                    modicharge.setTo_do(rs.getString("to_do"));
                    modicharge.setCharge_name(rs.getString("charge_name"));
                    modicharge.setPassword(rs.getString("password"));
                    modicharge.setWrite_day(rs.getString("write_day"));
                    modicharge.setModified_day(rs.getString("modified_day"));

                    return  modicharge;
        });
    }

    //4단계
    //1) 할일,담당자만 수정 가능
    //a) 비밀번호함께 전달 ( 특정 id에 password 입력)
    //b) 작성일 (write_day) 변경 x, 수정일(modified_day) 수정시점 변경
    //2)수정된 일정의 정보를 반환 받아 확인할 수 있습니다


    public int tocharge(StudentRequestDto requestDto) {

        String sql = "UPDATE major SET to_do = ?, charge_name = ?, modified_day = NOW() WHERE id = ? AND password = ?";

        return jdbcTemplate.update(sql,
                requestDto.getTo_do(),
                requestDto.getCharge_name(),
                requestDto.getStudent_id(),
                requestDto.getPassword()
        );
    }


    public int deletePart(StudentRequestDto requestDto) {

        String sql = "DELETE FROM major WHERE id = ? and password = ?";

        return jdbcTemplate.update(sql,
                requestDto.getStudent_id(),
                requestDto.getPassword());
    }
}



