package com.example.demo;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class StaffService {

    @Autowired
    private StaffRepository staffRepository;

    public void saveStaff(Staff staff) {
        staffRepository.save(staff);

    }

}
