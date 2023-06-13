package com.spring.testautomation.repository;

import com.spring.testautomation.entity.Customer;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.sql.Date;
import java.util.List;

/**
 * @author cristian_iosef
 */
@Repository
public interface CustomerRepository extends JpaRepository<Customer, Integer> {
    List<Customer> findByFirstNameStartingWith(String firstName);
    List<Customer> findByDobBetween(Date from, Date to);
}
