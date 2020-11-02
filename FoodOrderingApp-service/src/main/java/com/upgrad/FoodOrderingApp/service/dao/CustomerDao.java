package com.upgrad.FoodOrderingApp.service.dao;

import com.upgrad.FoodOrderingApp.service.entity.CustomerAuthEntity;
import com.upgrad.FoodOrderingApp.service.entity.CustomerEntity;
import org.springframework.stereotype.Repository;

import javax.persistence.Entity;
import javax.persistence.EntityManager;
import javax.persistence.NoResultException;
import javax.persistence.PersistenceContext;

//This Class is created to access DB with respect to CustomerEntity

@Repository
public class CustomerDao {


    @PersistenceContext
    private EntityManager entityManager;


    //To get Customer By ContactNumber if no results return null
    public CustomerEntity getCustomerByContactNumber(final String contact_number) {
        try {
            CustomerEntity customer = entityManager.createNamedQuery("customerByContactNumber", CustomerEntity.class).setParameter("contact_number", contact_number).getSingleResult();
            return customer;
        } catch (NoResultException nre) {
            return null;
        }
    }


    //To save the new customer entity
    public CustomerEntity createCustomer(CustomerEntity customerEntity) {
        entityManager.persist(customerEntity);
        return customerEntity;
    }

    //To update customer
    public CustomerEntity updateCustomer(CustomerEntity customerToBeUpdated) {
        entityManager.merge(customerToBeUpdated);
        return customerToBeUpdated;
    }


    //To get Customer By Uuid if no results return null
    public CustomerEntity getCustomerByUuid(final String uuid) {
        try {
            CustomerEntity customer = entityManager.createNamedQuery("customerByUuid", CustomerEntity.class).setParameter("uuid", uuid).getSingleResult();
            return customer;
        } catch (NoResultException nre) {
            return null;
        }
    }

    public CustomerAuthEntity getCustomerAuthToken(final String accessToken) {
        try {
            return entityManager.createNamedQuery("getCustomerAuthByAccessToken", CustomerAuthEntity.class)
                    .setParameter("access_Token", accessToken).getSingleResult();
        } catch (NoResultException nre) {
            return null;
        }
    }
}