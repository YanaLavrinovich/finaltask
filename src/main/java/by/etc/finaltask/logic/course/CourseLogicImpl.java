package by.etc.finaltask.logic.course;

import by.etc.finaltask.bean.Course;
import by.etc.finaltask.bean.Training;
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

import java.util.List;
import java.util.Map;

public class CourseLogicImpl implements CourseLogic {
    @Override
    public void addCourse(String name, String description, String dateStart, String dateFinish, String userId) throws InvalidInputException, CourseLogicException {
        CourseValidator courseValidator = ValidatorFactory.getInstance().getCourseValidator();

        if (!courseValidator.isValidCourse(name, dateStart, dateFinish, userId)) {
            throw new InvalidInputException("Wrong params in input date");
        }
        int id = Integer.valueOf(userId);
        CourseBuilder courseBuilder = new CourseBuilder();
        Course course = courseBuilder.build(name, description, dateStart, dateFinish, id);
        CourseDao courseDao = DaoFactory.getInstance().getCourseDao();
        try {
            courseDao.addCourse(course);
        } catch (DaoException e) {
            throw new CourseLogicException("Can't add new course.", e);
        }

    }

    @Override
    public List<Course> findCourseForTeacher(String userId) throws CourseLogicException, InvalidInputException {
        CourseValidator courseValidator = ValidatorFactory.getInstance().getCourseValidator();

        if (!courseValidator.isValidUserId(userId)) {
            throw new InvalidInputException("Wrong params in input date");
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
    public Map<Course, List<User>> findRequest(String userId) throws CourseLogicException, InvalidInputException {
        CourseValidator courseValidator = ValidatorFactory.getInstance().getCourseValidator();

        if (!courseValidator.isValidUserId(userId)) {
            throw new InvalidInputException("Wrong params in input date");
        }
        int id = Integer.valueOf(userId);
        Map<Course, List<User>> requests;
        CourseDao courseDao = DaoFactory.getInstance().getCourseDao();
        try {
            requests = courseDao.findRequest(id);
        } catch (DaoException e) {
            throw new CourseLogicException("Can't get requests.", e);
        }
        return requests;
    }

    @Override
    public void rejectSubscriber(String courseId, String studentId) throws CourseLogicException, InvalidInputException {
        CourseValidator courseValidator = ValidatorFactory.getInstance().getCourseValidator();

        if (!courseValidator.isValidCourseId(courseId) || !courseValidator.isValidUserId(studentId)) {
            throw new InvalidInputException("Wrong params in input date");
        }

        int id = Integer.valueOf(courseId);
        int subscriberId = Integer.valueOf(studentId);
        CourseDao courseDao = DaoFactory.getInstance().getCourseDao();
        try {
            courseDao.rejectSubscriber(id, subscriberId);
        } catch (DaoException e) {
            throw new CourseLogicException("Can't reject subscriber.", e);
        }
    }

    @Override
    public void acceptSubscriber(String courseId, String studentId) throws CourseLogicException, InvalidInputException {
        CourseValidator courseValidator = ValidatorFactory.getInstance().getCourseValidator();

        if (!courseValidator.isValidCourseId(courseId) || !courseValidator.isValidUserId(studentId)) {
            throw new InvalidInputException("Wrong params in input date");
        }

        int id = Integer.valueOf(courseId);
        int subscriberId = Integer.valueOf(studentId);
        CourseDao courseDao = DaoFactory.getInstance().getCourseDao();
        try {
            courseDao.acceptSubscriber(id, subscriberId);
        } catch (DaoException e) {
            throw new CourseLogicException("Can't accept subscriber.", e);
        }
    }

    @Override
    public Course takeCourse(String courseId) throws CourseLogicException, InvalidInputException {
        CourseValidator courseValidator = ValidatorFactory.getInstance().getCourseValidator();

        if (!courseValidator.isValidCourseId(courseId)) {
            throw new InvalidInputException("Wrong params in input date");
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
    public List<User> takeStudent(String courseId) throws CourseLogicException, InvalidInputException {
        CourseValidator courseValidator = ValidatorFactory.getInstance().getCourseValidator();

        if (!courseValidator.isValidCourseId(courseId)) {
            throw new InvalidInputException("Wrong params in input date");
        }

        int id = Integer.valueOf(courseId);

        CourseDao courseDao = DaoFactory.getInstance().getCourseDao();
        List<User> students = null;
        try {
            students = courseDao.takeStudent(id);
        } catch (DaoException e) {
            throw new CourseLogicException("Can't take student list.", e);
        }
        return students;
    }

    @Override
    public void removeCourse(String courseId) throws CourseLogicException, InvalidInputException {
        CourseValidator courseValidator = ValidatorFactory.getInstance().getCourseValidator();

        if (!courseValidator.isValidCourseId(courseId)) {
            throw new InvalidInputException("Wrong params in input date");
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
    public void excludeStudent(String courseId, String studentId) throws CourseLogicException, InvalidInputException {
        CourseValidator courseValidator = ValidatorFactory.getInstance().getCourseValidator();

        if (!courseValidator.isValidCourseId(courseId) || !courseValidator.isValidUserId(studentId)) {
            throw new InvalidInputException("Wrong params in input date");
        }

        int id = Integer.valueOf(courseId);
        int studId = Integer.valueOf(studentId);
        CourseDao courseDao = DaoFactory.getInstance().getCourseDao();
        try {
            courseDao.excludeStudent(id, studId);
        } catch (DaoException e) {
            throw new CourseLogicException("Can't exclude student.", e);
        }
    }

    @Override
    public void setMark(String courseId, String studentId, String mark, String comment) throws CourseLogicException, InvalidInputException {
        CourseValidator courseValidator = ValidatorFactory.getInstance().getCourseValidator();

        if (!courseValidator.isValidCourseId(courseId) || !courseValidator.isValidUserId(studentId) ||
                !courseValidator.isValidMark(mark)) {
            throw new InvalidInputException("Wrong params in input date");
        }

        int id = Integer.valueOf(courseId);
        int studId = Integer.valueOf(studentId);
        int markNumber = Integer.valueOf(mark);
        CourseDao courseDao = DaoFactory.getInstance().getCourseDao();
        try {
            courseDao.setMark(id, studId, markNumber, comment);
        } catch (DaoException e) {
            throw new CourseLogicException("Can't set mark for student.", e);
        }
    }

    @Override
    public void editCourse(String courseId, String nameCourse, String description, String dateStart, String dateFinish)
            throws CourseLogicException, InvalidInputException {
        CourseValidator courseValidator = ValidatorFactory.getInstance().getCourseValidator();
        if (!courseValidator.isValidCourseId(courseId) || !courseValidator.isValidCourse(nameCourse, dateStart, dateFinish)) {
            throw new InvalidInputException("Wrong params in input date");
        }

        if(!courseValidator.isValidDateRange(dateStart, dateFinish)) {
            throw new InvalidInputException("Wrong params in input date");
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
    public void submitCourse(String userId, String courseId) throws InvalidInputException, CourseLogicException {
        CourseValidator courseValidator = ValidatorFactory.getInstance().getCourseValidator();

        if (!courseValidator.isValidCourseId(courseId) || !courseValidator.isValidUserId(userId)) {
            throw new InvalidInputException("Wrong params in input date");
        }

        int idUser = Integer.valueOf(userId);
        int idCourse = Integer.valueOf(courseId);

        CourseDao courseDao = DaoFactory.getInstance().getCourseDao();
        try {
            courseDao.submitCourse(idUser, idCourse);
        } catch (DaoException e) {
            throw new CourseLogicException("Can't sumbit course.");
        }
    }

    @Override
    public List<Training> takeTraining(String userId) throws InvalidInputException, CourseLogicException {
        CourseValidator courseValidator = ValidatorFactory.getInstance().getCourseValidator();

        if (!courseValidator.isValidUserId(userId)) {
            throw new InvalidInputException("Wrong params in input date");
        }

        int id = Integer.valueOf(userId);
        CourseDao courseDao = DaoFactory.getInstance().getCourseDao();
        List<Training> trainingList = null;
        try {
            trainingList = courseDao.takeTraining(id);
        } catch (DaoException e) {
            throw new CourseLogicException("Can't take list of training.", e);
        }
        return trainingList;
    }
}
