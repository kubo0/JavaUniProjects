package org.example.controllers;

import lombok.RequiredArgsConstructor;
import org.example.dto.StaffDto;
import org.example.services.IStaffService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("api/staff")
@RequiredArgsConstructor
public class StaffController {
    private final IStaffService staffService;

    @GetMapping
    public ResponseEntity<List<StaffDto>> getAllStaff() {
        return ResponseEntity.ok(staffService.getAllStaff());
    }
    @PostMapping
    public void saveStaff(@RequestBody StaffDto staffDto) {
        staffService.save(staffDto);
    }

    @GetMapping("{id}")
    public ResponseEntity<StaffDto> getById(@PathVariable long id) {
        var staff = staffService.getById(id);
        if (staff == null)
            return ResponseEntity.notFound().build();
        return ResponseEntity.ok(staff);
    }

    @DeleteMapping("{id}")
    public ResponseEntity delete(@PathVariable long id) {
        var result = staffService.delete(id);
        if (result == null)
            return ResponseEntity.notFound().build();
        return ResponseEntity.ok(result);
    }

    @PutMapping("{id}")
    public ResponseEntity update(@PathVariable long id, @RequestBody StaffDto staffDto) {
        var result = staffService.update(id, staffDto);
        if (result == null)
            return ResponseEntity.notFound().build();
        return ResponseEntity.ok(result);
    }

    @GetMapping("/age/{age}")
    public ResponseEntity<List<StaffDto>> getByAge(@PathVariable int age) {
        return ResponseEntity.ok(staffService.getByAge(age));
    }
    @GetMapping("/age-between/{age1},{age2}")
    public ResponseEntity<List<StaffDto>> getByAgeBetween(@PathVariable int age1, @PathVariable int age2) {
        return ResponseEntity.ok(staffService.getByAgeBetween(age1, age2));
    }
    @GetMapping("/email/{email}")
    public ResponseEntity<StaffDto> getByEmail(@PathVariable String email) {
        return ResponseEntity.ok(staffService.getByEmail(email));
    }
}
