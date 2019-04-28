package com.me.dao;

import org.hibernate.HibernateException;

//import com.neu.edu.exception.CategoryException;
import com.me.pojo.*;

public class ApplicantDAO extends DAO{

	public Applicant create(Applicant applicant) {
		try {
			begin();
			getSession().save(applicant);
			commit();
		
		} catch (HibernateException e) {
			rollback();
			// throw new AdException("Could not create the category", e);
			//throw Exception("Exception while creating category: " + e.getMessage());
		}
		return applicant;
	}
}
