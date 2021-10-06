/**
 * Author: Kevin Abrahams
 * Date: June 10, 2020
 * Class Description: This exception is thrown if polynomial
 * contains coefficients or
 * exponents of an improper type or
 * should the exponents fail to be listed in strictly descending order.
 */
public class InvalidPolynomialSyntax extends RuntimeException {

    /**
     * Serial version uid
     */
    private static final long serialVersionUID = -1768447935010985301L;

    /**
     * Constructor to set the exception message
     *
     * @param message - Exception message
     */
    public InvalidPolynomialSyntax(String message) {
        super(message);
    }

}