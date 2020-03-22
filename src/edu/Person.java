package edu;

public class Person {
    protected String firstName;
    protected String lastName;
    protected String nationalCode;
    private long loanReceived;
    public static final long LOAN_AMOUNT = 5000000;

    public Person(String firstName, String lastName, String nationalCode) {
        this.firstName = firstName;
        this.lastName = lastName;
        this.nationalCode = nationalCode;
        this.loanReceived = 0;
    }

    public void receiveLoan() {
        loanReceived += Person.LOAN_AMOUNT;
    }
}
