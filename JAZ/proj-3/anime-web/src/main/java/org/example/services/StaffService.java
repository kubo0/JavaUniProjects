package org.example.services;

import lombok.RequiredArgsConstructor;
import org.example.dto.StaffDto;
import org.example.model.Staff;
import org.example.repositories.IRepositoriesCatalog;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class StaffService implements IStaffService {
    private final IRepositoriesCatalog repositories;

    @Override
    public List<StaffDto> getAllStaff() {
        return repositories
                .getStaff()
                .findAll()
                .stream()
                .map(s -> mapFromStaff(s))
                .toList();
    }

    @Override
    public StaffDto getById(long id) {
        return mapFromStaff(repositories.getStaff().getById(id));
    }

    @Override
    public long save(StaffDto staffDto) {
        Staff staff = getStaffFromDto(staffDto);
        var s = repositories.getStaff().save(staff);
        return s.getId();
    }

    @Override
    public StaffDto delete(long id) {
        var staff = repositories.getStaff().getById(id);
        repositories.getStaff().delete(staff);
        return mapFromStaff(staff);
    }

    @Override
    public StaffDto update(long id, StaffDto staffDto) {
        var staff = repositories.getStaff().getById(id);
        repositories.getStaff().save(getStaffFromDto(staffDto, staff));
        return staffDto;
    }

    @Override
    public List<StaffDto> getByAge(int age) {
        return repositories
                .getStaff()
                .findAllByAge(age)
                .stream()
                .map(s -> mapFromStaff(s))
                .toList();
    }

    @Override
    public List<StaffDto> getByAgeBetween(int ageFrom, int ageTo) {
        return repositories
                .getStaff()
                .findAllByAgeBetween(ageFrom, ageTo)
                .stream()
                .map(s -> mapFromStaff(s))
                .toList();
    }

    @Override
    public StaffDto getByEmail(String email) {
        return mapFromStaff(repositories.getStaff().findByEmail(email));
    }

    private static Staff getStaffFromDto(StaffDto staffDto) {
        return getStaffFromDto(staffDto, new Staff());
    }
    private static Staff getStaffFromDto(StaffDto staffDto, Staff staff) {
        staff.setAge(staffDto.getAge());
        staff.setEmail(staffDto.getEmail());
        staff.setFirstName(staffDto.getFirstName());
        staff.setLastName(staffDto.getLastName());
        return staff;
    }
    private StaffDto mapFromStaff(Staff staff) {
        return new StaffDto()
                .setAge(staff.getAge())
                .setEmail(staff.getEmail())
                .setFirstName(staff.getFirstName())
                .setLastName(staff.getLastName());
    }
}
