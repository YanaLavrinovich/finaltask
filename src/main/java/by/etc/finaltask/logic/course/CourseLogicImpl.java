package by.etc.finaltask.logic.course;

import by.etc.finaltask.bean.Course;
import by.etc.finaltask.bean.User;
import by.etc.finaltask.bean.build_bean.CourseBuilder;
import by.etc.finaltask.dao.DaoFactory;
import by.etc.finaltask.dao.course.CourseDao;
import by.etc.finaltask.dao.exception.DaoException;
import by.etc.finaltask.dao.exception.DaoRollbackException;
import by.etc.finaltask.logic.exception.CourseLogicException;
import by.etc.finaltask.logic.exception.InvalidInputException;
import by.etc.finaltask.logic.validator.CourseValidator;
import by.etc.finaltask.logic.validator.ValidatorFactory;

import java.sql.Date;
import java.util.List;
import java.util.Map;

public class CourseLogicImpl implements CourseLogic {
    @Override
    public void addCourse(String name, String description, String dateStart, String dateFinish, int userId) throws InvalidInputException, CourseLogicException {
        CourseValidator courseValidator = ValidatorFactory.getInstance().getCourseValidator();

        if (courseValidator.isValidCourse(name, dateStart, dateFinish, userId)) {
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

    @Override
    public void rejectSubscriber(int courseId, int studentId) throws CourseLogicException {
        CourseDao courseDao = DaoFactory.getInstance().getCourseDao();
        try {
            courseDao.rejectSubscriber(courseId, studentId);
        } catch (DaoException e) {
            throw new CourseLogicException("Can't reject subscriber.", e);
        }
    }

    @Override
    public void acceptSubscriber(int courseId, int studentId) throws CourseLogicException {
        CourseDao courseDao = DaoFactory.getInstance().getCourseDao();
        try {
            courseDao.acceptSubscriber(courseId, studentId);
        } catch (DaoException e) {
            throw new CourseLogicException("Can't accept subscriber.", e);
        }
    }

    @Override
    public Course takeCourse(int courseId) throws CourseLogicException {
        CourseDao courseDao = DaoFactory.getInstance().getCourseDao();
        Course course = null;
        try {
            course = courseDao.takeCourse(courseId);
        } catch (DaoException e) {
            throw new CourseLogicException("Can't take course.", e);
        }
        return course;
    }

    @Override
    public List<User> takeStudent(int courseId) throws CourseLogicException {
        CourseDao courseDao = DaoFactory.getInstance().getCourseDao();
        List<User> students = null;
        try {
            students = courseDao.takeStudent(courseId);
        } catch (DaoException e) {
            throw new CourseLogicException("Can't take student list.", e);
        }
        return students;
    }

    @Override
    public void removeCourse(int courseId) throws CourseLogicException {
        CourseDao courseDao = DaoFactory.getInstance().getCourseDao();
        try {
            courseDao.removeCourse(courseId);
        } catch (DaoException | DaoRollbackException e) {
            throw new CourseLogicException("Can't remove course.", e);
        }
    }

    @Override
    public void excludeStudent(int courseId, int studentId) throws CourseLogicException {
        CourseDao courseDao = DaoFactory.getInstance().getCourseDao();
        try {
            courseDao.excludeStudent(courseId, studentId);
        } catch (DaoException e) {
            throw new CourseLogicException("Can't exclude student.", e);
        }
    }

    @Override
    public void setMark(int courseId, int studentId, int mark, String comment) throws CourseLogicException {
        CourseDao courseDao = DaoFactory.getInstance().getCourseDao();
        try {
            courseDao.setMark(courseId, studentId, mark, comment);
        } catch (DaoException e) {
            throw new CourseLogicException("Can't set mark for student.", e);
        }
    }

    @Override
    public void editCourse(int courseId, String nameCourse, String description, String dateStart, String dateFinish) throws CourseLogicException {
        CourseDao courseDao = DaoFactory.getInstance().getCourseDao();
        Date start = Date.valueOf(dateStart);
        Date finish = Date.valueOf(dateFinish);
        try {
            courseDao.editCourse(courseId, nameCourse, description, start, finish);
        } catch (DaoException e) {
            throw new CourseLogicException("Can't edit course", e);
        }

    }
}
