package biz.idsoftware.dao;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import biz.idsoftware.model.DAOUser;

@Repository
public interface UserDao extends CrudRepository<DAOUser, Integer> {
	
	DAOUser findByUsername(String username);
	
}