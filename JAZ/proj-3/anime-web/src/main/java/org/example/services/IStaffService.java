package org.example.services;

import org.example.dto.StaffDto;

import java.util.List;

public interface IStaffService {
    List<StaffDto> getAllStaff();
    StaffDto getById(long id);
    long save(StaffDto staffDto);
    StaffDto delete(long id);
    StaffDto update(long id, StaffDto staffDto);
    List<StaffDto> getByAge(int age);
    List<StaffDto> getByAgeBetween(int ageFrom, int ageTo);
    StaffDto getByEmail(String email);
}
