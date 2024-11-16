package com.example.demo;

import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository // Spring that this is a DAO component.
public interface AdminRepository extends JpaRepository<Admin, String> { // <Entity_name,Primary_key_type>
    // implements DAO Pattern
    Admin findByPassword(String password);// custom query method (Spring will take careo of the respective sql
                                          // implentation of this)
}
