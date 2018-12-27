package by.etc.finaltask.logic.course;

import by.etc.finaltask.bean.Course;
import by.etc.finaltask.bean.User;
import by.etc.finaltask.bean.build_bean.CourseBuilder;
import by.etc.finaltask.dao.DaoFactory;
import by.etc.finaltask.dao.course.CourseDao;
import by.etc.finaltask.dao.exception.DaoException;
import by.etc.finaltask.logic.exception.CourseLogicException;
import by.etc.finaltask.logic.exception.InvalidInputException;
import by.etc.finaltask.logic.validator.CourseValidator;
import by.etc.finaltask.logic.validator.ValidatorFactory;

import java.util.List;
import java.util.Map;

public class CourseLogicImpl implements CourseLogic{
    @Override
    public void addCourse(String name, String description, String dateStart, String dateFinish, int userId) throws InvalidInputException, CourseLogicException {
        CourseValidator courseValidator = ValidatorFactory.getInstance().getCourseValidator();

        if(courseValidator.isValidCourse(name, dateStart, dateFinish, userId)) {
            CourseBuilder courseBuilder = new CourseBuilder();
            Course course = courseBuilder.build(name, description, dateStart, dateFinish, userId);
            CourseDao courseDao = DaoFactory.getInstance().getCourseDao();
            try {
                courseDao.addCourse(course);
            } catch (DaoException e) {
                throw new CourseLogicException("Can't add new course.", e);
            }
        } else {
            throw new InvalidInputException("Wrong params in input date");
        }
    }

    @Override
    public List<Course> findCourseForTeacher(int userId) throws CourseLogicException {
        List<Course> courses;
        CourseDao courseDao = DaoFactory.getInstance().getCourseDao();
        try {
            courses = courseDao.findCourseForTeacher(userId);
        } catch (DaoException e) {
            throw new CourseLogicException("Can't get courses.");
        }
        return courses;
    }

    @Override
    public Map<Course, List<User>> findRequest(int userId) throws CourseLogicException {
        Map<Course, List<User>> requests;
        CourseDao courseDao = DaoFactory.getInstance().getCourseDao();
        try {
            requests = courseDao.findRequest(userId);
        } catch (DaoException e) {
            throw new CourseLogicException("Can't get requests.", e);
        }
        return requests;
    }
}
