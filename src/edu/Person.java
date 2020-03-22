package edu;

public abstract class Person {
    protected String firstName;
    protected String lastName;
    protected String nationalCode;
    protected long loanReceived;
    public static final long LOAN_AMOUNT_STUDENT = 5000000;
    public static final long LOAN_AMOUNT_PROFESSOR = 10000000;

    public Person(String firstName, String lastName, String nationalCode) {
        this.firstName = firstName;
        this.lastName = lastName;
        this.nationalCode = nationalCode;
        this.loanReceived = 0;
    }

    public abstract String getType();

    public String getNationalCode() {
        return nationalCode;
    }

    public String getFirstName() {
        return firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public abstract void receiveLoan();
}
