package fr.eni.tpgestionlistescourses.dal;

import java.util.List;

import fr.eni.tpgestionlistescourses.models.bo.Courses;

public interface CoursesDAO {

	Courses insert(Courses courses) throws Exception;

	List<Courses> select() throws Exception;

	void supprime(int id) throws Exception;

}
