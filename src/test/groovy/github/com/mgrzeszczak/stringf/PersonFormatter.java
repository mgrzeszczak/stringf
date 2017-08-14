package github.com.mgrzeszczak.stringf;

class PersonFormatter implements Formatter<Person> {

    @Override
    public String format(Person person) {
        return person.getLastName().toUpperCase() + " " + person.getFirstName().toLowerCase();
    }

    @Override
    public Class<? extends Person> target() {
        return Person.class;
    }
}
