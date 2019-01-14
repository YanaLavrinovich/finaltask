package by.etc.finaltask.logic.validation;

/**
 * Validates user form input. Throws {@link ValidationException} with the error message if validation fails.
 */
public interface UserValidator {

    /**
     * Validates input user's id for validity.
     *
     * @param id user's id
     * @throws ValidationException if user's id is not positive
     */
    void isValidId(String id) throws ValidationException;

    /**
     * Validates input user's password for validity.
     *
     * @param password user's password
     * @throws ValidationException if user's password is  incorrect
     */
    void isValidPassword(String password) throws ValidationException;

    /**
     * Validates input user's email for validity.
     *
     * @param email user's email
     * @throws ValidationException if user's email is incorrect
     */
    void isValidEmail(String email) throws ValidationException;

    /**
     * Validates input user's info for validity.
     *
     * @param email     user's email
     * @param firstName user's first name
     * @param lastName  user's last name
     * @param sex       user's sex
     * @param role      user's role
     * @throws ValidationException if user's info is incorrect
     */
    void isValidUserInfo(String email, String firstName, String lastName, String sex, String role) throws ValidationException;

    /**
     * Validates input edit user's info for validity.
     *
     * @param userId    user's id
     * @param email     user's email
     * @param firstName user's first name
     * @param lastName  user's last name
     * @param sex       user's sex
     * @throws ValidationException if user's info is incorrect
     */
    void isValidEditUser(String userId, String email, String firstName, String lastName, String sex) throws ValidationException;

    /**
     * Validates input user's name for validity.
     *
     * @param name user's name
     * @throws ValidationException if user's name is incorrect
     */
    void isValidName(String name) throws ValidationException;

    /**
     * Validates input user's sex for validity.
     *
     * @param sex user's sex
     * @throws ValidationException if user's sex is incorrect
     */
    void isValidSex(String sex) throws ValidationException;

    /**
     * Validates input user's role for validity.
     *
     * @param role user's role
     * @throws ValidationException if user's role is incorrect
     */
    void isValidRole(String role) throws ValidationException;
}
