package github.com.mgrzeszczak.stringf;

import static github.com.mgrzeszczak.stringf.Stringf.stringf;

final class Person {

    private final String firstName;
    private final String lastName;


    Person(String firstName, String lastName) {
        this.firstName = firstName;
        this.lastName = lastName;
    }

    String getFirstName() {
        return firstName;
    }

    String getLastName() {
        return lastName;
    }

    @Override
    public String toString() {
        return stringf().$(firstName).$(lastName).str();
    }
}
