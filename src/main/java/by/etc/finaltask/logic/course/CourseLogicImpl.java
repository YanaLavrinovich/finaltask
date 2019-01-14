package by.etc.finaltask.logic.course;

import by.etc.finaltask.bean.Course;
import by.etc.finaltask.bean.build_bean.CourseBuilder;
import by.etc.finaltask.dao.DaoFactory;
import by.etc.finaltask.dao.course.CourseDao;
import by.etc.finaltask.dao.exception.DaoException;
import by.etc.finaltask.dao.exception.DaoRollbackException;
import by.etc.finaltask.logic.exception.CourseLogicException;
import by.etc.finaltask.logic.exception.InvalidInputException;
import by.etc.finaltask.logic.validation.CourseValidator;
import by.etc.finaltask.logic.validation.CourseValidatorImpl;
import by.etc.finaltask.logic.validation.ValidationException;
import by.etc.finaltask.logic.validation.ValidatorFactory;

import java.util.List;

public class CourseLogicImpl implements CourseLogic {
    @Override
    public void addCourse(String name, String description, String dateStart, String dateFinish, String userId) throws InvalidInputException, CourseLogicException {
        CourseValidator courseValidator = ValidatorFactory.getInstance().getCourseValidator();

        try {
            courseValidator.isValidCourse(name, dateStart, dateFinish, userId);
        } catch (ValidationException e) {
            throw new InvalidInputException("Wrong params in input date", e);
        }

        int id = Integer.valueOf(userId);
        CourseBuilder courseBuilder = new CourseBuilder();
        courseBuilder.addName(name);
        courseBuilder.addDescription(description);
        courseBuilder.addDateStart(dateStart);
        courseBuilder.addDateFinish(dateFinish);
        courseBuilder.addUserId(id);

        CourseDao courseDao = DaoFactory.getInstance().getCourseDao();
        try {
            courseDao.addCourse(courseBuilder.build());
        } catch (DaoException e) {
            throw new CourseLogicException("Can't add new course.", e);
        }

    }

    @Override
    public List<Course> findCourseForTeacher(String userId) throws CourseLogicException, InvalidInputException {
        CourseValidator courseValidator = ValidatorFactory.getInstance().getCourseValidator();

        try {
            courseValidator.isValidUserId(userId);
        } catch (ValidationException e) {
            throw new InvalidInputException("Wrong params in input date", e);
        }

        int id = Integer.valueOf(userId);
        List<Course> courses;
        CourseDao courseDao = DaoFactory.getInstance().getCourseDao();
        try {
            courses = courseDao.findCourseForTeacher(id);
        } catch (DaoException e) {
            throw new CourseLogicException("Can't get courses.");
        }
        return courses;
    }


    @Override
    public Course takeCourse(String courseId) throws CourseLogicException, InvalidInputException {
        CourseValidator courseValidator = ValidatorFactory.getInstance().getCourseValidator();

        try {
            courseValidator.isValidCourseId(courseId);
        } catch (ValidationException e) {
            throw new InvalidInputException("Wrong params in input date", e);
        }

        int id = Integer.valueOf(courseId);
        CourseDao courseDao = DaoFactory.getInstance().getCourseDao();
        Course course = null;
        try {
            course = courseDao.takeCourse(id);
        } catch (DaoException e) {
            throw new CourseLogicException("Can't take course.", e);
        }
        return course;
    }


    @Override
    public void removeCourse(String courseId) throws CourseLogicException, InvalidInputException {
        CourseValidator courseValidator = ValidatorFactory.getInstance().getCourseValidator();
        try {
            courseValidator.isValidCourseId(courseId);
        } catch (ValidationException e) {
            throw new InvalidInputException("Wrong params in input date", e);
        }

        int id = Integer.valueOf(courseId);
        CourseDao courseDao = DaoFactory.getInstance().getCourseDao();
        try {
            courseDao.removeCourse(id);
        } catch (DaoException | DaoRollbackException e) {
            throw new CourseLogicException("Can't remove course.", e);
        }
    }


    @Override
    public void editCourse(String courseId, String nameCourse, String description, String dateStart, String dateFinish)
            throws CourseLogicException, InvalidInputException {
        CourseValidator courseValidator = ValidatorFactory.getInstance().getCourseValidator();
        try {
            courseValidator.isValidCourseId(courseId);
            courseValidator.isValidCourse(nameCourse, dateStart, dateFinish);
            courseValidator.isValidDateRange(dateStart, dateFinish);
        } catch (ValidationException e) {
            throw new InvalidInputException("Wrong params in input date", e);
        }

        int id = Integer.valueOf(courseId);
        CourseDao courseDao = DaoFactory.getInstance().getCourseDao();
        try {
            courseDao.editCourse(id, nameCourse, description, dateStart, dateFinish);
        } catch (DaoException e) {
            throw new CourseLogicException("Can't edit course", e);
        }

    }

    @Override
    public List<Course> findActualCourse() throws CourseLogicException {
        List<Course> courses;
        CourseDao courseDao = DaoFactory.getInstance().getCourseDao();
        try {
            courses = courseDao.findActualCourse();
        } catch (DaoException e) {
            throw new CourseLogicException("Can't get courses.");
        }
        return courses;
    }


    @Override
    public List<Course> findAllCourse() throws CourseLogicException {
        List<Course> courses;
        CourseDao courseDao = DaoFactory.getInstance().getCourseDao();
        try {
            courses = courseDao.findAllCourse();
        } catch (DaoException e) {
            throw new CourseLogicException("Can't get courses.", e);
        }
        return courses;
    }

    @Override
    public void restoreCourse(String courseId) throws InvalidInputException, CourseLogicException {
        CourseValidator courseValidator = ValidatorFactory.getInstance().getCourseValidator();
        try {
            courseValidator.isValidCourseId(courseId);
        } catch (ValidationException e) {
            throw new InvalidInputException("Wrong params in input date", e);
        }

        int id = Integer.valueOf(courseId);
        CourseDao courseDao = DaoFactory.getInstance().getCourseDao();
        try {
            courseDao.restoreCourse(id);
        } catch (DaoException e) {
            throw new CourseLogicException("Can't restore course.", e);
        }
    }
}
