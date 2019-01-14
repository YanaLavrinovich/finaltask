package by.etc.finaltask.logic.validation;

public class UserValidatorImpl implements UserValidator {

    private static final String ID_REGEX = "^[\\d]+$";
    private static final String PASSWORD_REGEX = "^[\\w\\d._]{4,12}$";
    private static final String EMAIL_REGEX = "^[\\w._\\d-]+@[A-Za-z]+.[A-Za-z]{2,3}$";
    private static final String NAME_REGEX = "^[\\w[А-я]]+$";
    private static final String SEX_REGEX = "^(female|male)$";
    private static final String ROLE_REGEX = "^(admin|teacher|student)$";

    /**
     * @see UserValidator#isValidId(String)
     */
    public void isValidId(String id) throws ValidationException {
        if (id == null || !id.matches(ID_REGEX)) {
            throw new ValidationException("The user's id is wrong. It is null or has incorrect data");
        }
    }

    /**
     * @see UserValidator#isValidPassword(String)
     */
    public void isValidPassword(String password) throws ValidationException {
        if (password == null || !password.matches(PASSWORD_REGEX)) {
            throw new ValidationException("The user's password is wrong. It is null or has incorrect data");
        }
    }

    /**
     * @see UserValidator#isValidEmail(String)
     */
    public void isValidEmail(String email) throws ValidationException {
        if (email == null || !email.matches(EMAIL_REGEX)) {
            throw new ValidationException("The user's email is wrong. It is null or has incorrect data");
        }
    }

    /**
     * @see UserValidator#isValidUserInfo(String, String, String, String, String)
     */
    public void isValidUserInfo(String email, String firstName, String lastName, String sex, String role) throws ValidationException {
        isValidEmail(email);
        isValidName(firstName);
        isValidName(lastName);
        isValidSex(sex);
        isValidRole(role);
    }

    /**
     * @see UserValidator#isValidEditUser(String, String, String, String, String)
     */
    public void isValidEditUser(String userId, String email, String firstName, String lastName, String sex) throws ValidationException {
        isValidId(userId);
        isValidName(firstName);
        isValidName(lastName);
        isValidEmail(email);
        isValidSex(sex);
    }

    /**
     * @see UserValidator#isValidName(String)
     */
    public void isValidName(String name) throws ValidationException {
        if (name == null || !name.matches(NAME_REGEX)) {
            throw new ValidationException("The user's name is wrong. It is null or has incorrect data");
        }
    }

    /**
     * @see UserValidator#isValidSex(String)
     */
    public void isValidSex(String sex) throws ValidationException {
        if (sex == null || !sex.matches(SEX_REGEX)) {
            throw new ValidationException("The user's sex is wrong. It is null or has incorrect data");
        }
    }

    /**
     * @see UserValidator#isValidRole(String)
     */
    public void isValidRole(String role) throws ValidationException {
        if (role == null || !role.toUpperCase().matches(ROLE_REGEX)) {
            throw new ValidationException("The user's role is wrong. It is null or has incorrect data");
        }
    }


}
