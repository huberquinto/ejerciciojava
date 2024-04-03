package com.hfqv.adapters.persistence.repository;


import com.hfqv.adapters.persistence.repository.model.Phone;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface PhoneRepository extends JpaRepository<Phone,Long> {

    public List<Phone> findByNumber(String number);
}
