package by.etc.finaltask.logic.training;

import by.etc.finaltask.domain.Course;
import by.etc.finaltask.domain.Training;
import by.etc.finaltask.domain.User;
import by.etc.finaltask.dao.DaoFactory;
import by.etc.finaltask.dao.exception.DaoException;
import by.etc.finaltask.dao.exception.DaoRollbackException;
import by.etc.finaltask.dao.training.TrainingDao;
import by.etc.finaltask.logic.exception.CourseLogicException;
import by.etc.finaltask.logic.exception.InvalidInputException;
import by.etc.finaltask.logic.validation.CourseValidator;
import by.etc.finaltask.logic.validation.TrainingValidator;
import by.etc.finaltask.logic.validation.ValidationException;
import by.etc.finaltask.logic.validation.ValidatorFactory;

import java.util.List;
import java.util.Map;

public class TrainingLogicImpl implements TrainingLogic {

    @Override
    public Map<Course, List<User>> findRequest(String userId) throws CourseLogicException, InvalidInputException {
        TrainingValidator trainingValidator = ValidatorFactory.getInstance().getTrainingValidator();

        try {
            trainingValidator.isValidUserId(userId);
        } catch (ValidationException e) {
            throw new InvalidInputException("Wrong params in input date", e);
        }

        int id = Integer.valueOf(userId);
        Map<Course, List<User>> requests;
        TrainingDao trainingDao = DaoFactory.getInstance().getTrainingDao();
        try {
            requests = trainingDao.findRequest(id);
        } catch (DaoException e) {
            throw new CourseLogicException("Can't get requests.", e);
        }
        return requests;
    }

    @Override
    public void rejectSubscriber(String courseId, String studentId) throws CourseLogicException, InvalidInputException {
        CourseValidator courseValidator = ValidatorFactory.getInstance().getCourseValidator();

        try {
            courseValidator.isValidCourseId(courseId);
            courseValidator.isValidUserId(studentId);
        } catch (ValidationException e) {
            throw new InvalidInputException("Wrong params in input date", e);
        }

        int id = Integer.valueOf(courseId);
        int subscriberId = Integer.valueOf(studentId);
        TrainingDao trainingDao = DaoFactory.getInstance().getTrainingDao();
        try {
            trainingDao.rejectSubscriber(id, subscriberId);
        } catch (DaoException e) {
            throw new CourseLogicException("Can't reject subscriber.", e);
        }
    }

    @Override
    public void acceptSubscriber(String courseId, String studentId) throws CourseLogicException, InvalidInputException {
        CourseValidator courseValidator = ValidatorFactory.getInstance().getCourseValidator();

        try {
            courseValidator.isValidCourseId(courseId);
            courseValidator.isValidUserId(studentId);
        } catch (ValidationException e) {
            throw new InvalidInputException("Wrong params in input date", e);
        }

        int id = Integer.valueOf(courseId);
        int subscriberId = Integer.valueOf(studentId);
        TrainingDao trainingDao = DaoFactory.getInstance().getTrainingDao();
        try {
            trainingDao.acceptSubscriber(id, subscriberId);
        } catch (DaoException e) {
            throw new CourseLogicException("Can't accept subscriber.", e);
        }
    }

    @Override
    public List<User> takeStudent(String courseId) throws CourseLogicException, InvalidInputException {
        CourseValidator courseValidator = ValidatorFactory.getInstance().getCourseValidator();

        try {
            courseValidator.isValidCourseId(courseId);
        } catch (ValidationException e) {
            throw new InvalidInputException("Wrong params in input date", e);
        }

        int id = Integer.valueOf(courseId);

        TrainingDao trainingDao = DaoFactory.getInstance().getTrainingDao();
        List<User> students = null;
        try {
            students = trainingDao.takeStudent(id);
        } catch (DaoException e) {
            throw new CourseLogicException("Can't take student list.", e);
        }
        return students;
    }

    @Override
    public void excludeStudent(String courseId, String studentId) throws CourseLogicException, InvalidInputException {
        CourseValidator courseValidator = ValidatorFactory.getInstance().getCourseValidator();

        try {
            courseValidator.isValidCourseId(courseId);
            courseValidator.isValidUserId(studentId);
        } catch (ValidationException e) {
            throw new InvalidInputException("Wrong params in input date", e);
        }

        int id = Integer.valueOf(courseId);
        int studId = Integer.valueOf(studentId);
        TrainingDao trainingDao = DaoFactory.getInstance().getTrainingDao();
        try {
            trainingDao.excludeStudent(id, studId);
        } catch (DaoException e) {
            throw new CourseLogicException("Can't exclude student.", e);
        }
    }

    @Override
    public void setMark(String courseId, String studentId, String mark, String comment) throws CourseLogicException, InvalidInputException {
        CourseValidator courseValidator = ValidatorFactory.getInstance().getCourseValidator();
        TrainingValidator trainingValidator = ValidatorFactory.getInstance().getTrainingValidator();

        try {
            courseValidator.isValidCourseId(courseId);
            courseValidator.isValidUserId(studentId);
            trainingValidator.isValidMark(mark);
            trainingValidator.isValidComment(comment);
        } catch (ValidationException e) {
            throw new InvalidInputException("Wrong params in input date", e);
        }

        int id = Integer.valueOf(courseId);
        int studId = Integer.valueOf(studentId);
        int markNumber = Integer.valueOf(mark);
        TrainingDao trainingDao = DaoFactory.getInstance().getTrainingDao();
        try {
            trainingDao.setMark(id, studId, markNumber, comment);
        } catch (DaoException e) {
            throw new CourseLogicException("Can't set mark for student.", e);
        }
    }

    @Override
    public void submitCourse(String userId, String courseId) throws InvalidInputException, CourseLogicException {
        CourseValidator courseValidator = ValidatorFactory.getInstance().getCourseValidator();

        try {
            courseValidator.isValidCourseId(courseId);
            courseValidator.isValidUserId(userId);
        } catch (ValidationException e) {
            throw new InvalidInputException("Wrong params in input date", e);
        }

        int idUser = Integer.valueOf(userId);
        int idCourse = Integer.valueOf(courseId);

        TrainingDao trainingDao = DaoFactory.getInstance().getTrainingDao();
        try {
            trainingDao.submitCourse(idUser, idCourse);
        } catch (DaoException e) {
            throw new CourseLogicException("Can't sumbit course.", e);
        }
    }

    @Override
    public List<Training> takeTraining(String userId) throws InvalidInputException, CourseLogicException {
        TrainingValidator trainingValidator = ValidatorFactory.getInstance().getTrainingValidator();

        try {
            trainingValidator.isValidUserId(userId);
        } catch (ValidationException e) {
            throw new InvalidInputException("Wrong params in input date", e);
        }

        int id = Integer.valueOf(userId);
        TrainingDao trainingDao = DaoFactory.getInstance().getTrainingDao();
        List<Training> trainingList = null;
        try {
            trainingList = trainingDao.takeTraining(id);
        } catch (DaoException e) {
            throw new CourseLogicException("Can't take list of training.", e);
        }
        return trainingList;
    }

    @Override
    public void startTraining(String courseId) throws InvalidInputException, CourseLogicException {
        CourseValidator courseValidator = ValidatorFactory.getInstance().getCourseValidator();
        try {
            courseValidator.isValidCourseId(courseId);
        } catch (ValidationException e) {
            throw new InvalidInputException("Wrong id of course", e);
        }
        int id = Integer.valueOf(courseId);
        TrainingDao trainingDao = DaoFactory.getInstance().getTrainingDao();
        try {
            trainingDao.startTraining(id);
        } catch (DaoException | DaoRollbackException e) {
            throw new CourseLogicException("Can't start course", e);
        }
    }

    @Override
    public void stopTraining(String courseId) throws CourseLogicException, InvalidInputException {
        CourseValidator courseValidator = ValidatorFactory.getInstance().getCourseValidator();
        try {
            courseValidator.isValidCourseId(courseId);
        } catch (ValidationException e) {
            throw new InvalidInputException("Wrong id of course", e);
        }
        int id = Integer.valueOf(courseId);
        TrainingDao trainingDao = DaoFactory.getInstance().getTrainingDao();
        try {
            trainingDao.stopTraining(id);
        } catch (DaoException | DaoRollbackException e) {
            throw new CourseLogicException("Can't start course", e);
        }
    }

    @Override
    public List<Training> takeStudentForCourse(String courseId) throws InvalidInputException, CourseLogicException {
        CourseValidator courseValidator = ValidatorFactory.getInstance().getCourseValidator();

        try {
            courseValidator.isValidCourseId(courseId);
        } catch (ValidationException e) {
            throw new InvalidInputException("Wrong params in input date", e);
        }

        int id = Integer.valueOf(courseId);
        TrainingDao trainingDao = DaoFactory.getInstance().getTrainingDao();
        List<Training> trainingList = null;
        try {
            trainingList = trainingDao.takeStudentForCourse(id);
        } catch (DaoException e) {
            throw new CourseLogicException("Can't take list of students for training.", e);
        }
        return trainingList;
    }

    @Override
    public String takeCourseStatus(String userId, String courseId) throws InvalidInputException, CourseLogicException {
        CourseValidator courseValidator = ValidatorFactory.getInstance().getCourseValidator();

        try {
            courseValidator.isValidCourseId(courseId);
            courseValidator.isValidUserId(userId);
        } catch (ValidationException e) {
            throw new InvalidInputException("Wrong params in input date", e);
        }

        int cId = Integer.valueOf(courseId);
        int uId = Integer.valueOf(userId);
        TrainingDao trainingDao = DaoFactory.getInstance().getTrainingDao();
        String courseStatus = null;
        try {
            courseStatus = trainingDao.takeCourseStatus(uId, cId);
        } catch (DaoException e) {
            throw new CourseLogicException("Can't take course status.", e);
        }
        if (courseStatus!= null) {
            courseStatus = courseStatus.toUpperCase();
        }
        return courseStatus;
    }


}
