package com.vicutu.persistence.dao;

import java.sql.Connection;
import java.util.List;
import java.util.Map;

import org.springframework.dao.DataAccessException;

/**
 * DAO can execute store procedure
 * 
 * @author Peng Fei Di
 * 
 */
public interface BaseExecutableDao extends BaseDao {

	/**
	 * 
	 * @param spName 
	 * @throws DataAccessException
	 */
	void execute(String spName) throws DataAccessException;

	/**
	 * 
	 * @param spName
	 * @param parameters
	 */
	void execute(String spName, Map<String, Object> parameters) throws DataAccessException;

	/**
	 * 
	 * @param spName
	 * @param parameters
	 * @param outParams
	 * @param cusorName
	 * @return
	 * @throws DataAccessException
	 */
	Map<String, Object> executeWithResult(String spName, Map<String, Object> parameters,
			Map<String, Integer> outParams, String cursorName) throws DataAccessException;

	/**
	 * 
	 * @param spName
	 * @param parameters
	 * @param outParams
	 * @param cusorName
	 * @return
	 * @throws DataAccessException
	 */
	Map<String, Object> executeWithResult(Connection conn, String spName, Map<String, Object> parameters,
			Map<String, Integer> outParams, String cursorName) throws DataAccessException;

	/**
	 * 
	 * @param spName
	 * @return
	 * @throws DataAccessException
	 */
	List<?> executeWithResultset(String spName) throws DataAccessException;

	/**
	 * 
	 * @param spName
	 * @param parameters
	 * @return
	 * @throws DataAccessException
	 */
	List<?> executeWithResultset(String spName, Map<String, Object> parameters) throws DataAccessException;

}
