package com.padierna.globant.fit.vs.celebrity.model;

import java.util.HashSet;
import java.util.Objects;
import java.util.Set;

public class Person {

  private final String name;

  private final Set<Person> knownPersons;

  public Person(String name) {
    this.name = name;
    this.knownPersons = new HashSet<>();
  }

  public Set<Person> getKnownPersons() {
    return knownPersons;
  }

  @Override
  public boolean equals(Object object) {
    if (this == object) {
      return true;
    }

    if (object == null || getClass() != object.getClass()) {
      return false;
    }

    Person person = (Person) object;

    return name.equals(person.name) && knownPersons.equals(person.knownPersons);
  }

  @Override
  public int hashCode() {
    return Objects.hash(name);
  }
}
