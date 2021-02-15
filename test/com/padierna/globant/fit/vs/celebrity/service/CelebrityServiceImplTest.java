package com.padierna.globant.fit.vs.celebrity.service;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

import com.padierna.globant.fit.vs.celebrity.model.Person;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.Optional;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

public class CelebrityServiceImplTest {

  private CelebrityService celebrityServiceImpl;

  @BeforeEach
  void setUp() {
    celebrityServiceImpl = new CelebrityServiceImpl();
  }

  @Test
  @DisplayName(value = "'findCelebrity' when persons list is null.")
  void findCelebrityWhenPersonsIsNull() {
    Optional<Person> actualFoundCelebrity = celebrityServiceImpl.findCelebrity(null);

    assertFalse(actualFoundCelebrity.isPresent());
  }

  @Test
  @DisplayName(value = "'findCelebrity' when persons list is empty.")
  void findCelebrityWhenPersonsIsEmpty() {
    Optional<Person> actualFoundCelebrity = celebrityServiceImpl.findCelebrity(Collections.emptyList());

    assertFalse(actualFoundCelebrity.isPresent());
  }

  @Test
  @DisplayName(value = "'findCelebrity' when there are two not Celebrity persons.")
  void findCelebrityWhenThereAreTwoNotCelebrityPersons() {
    Person firstNotCelebrityPerson = buildPerson("Not A Celebrity #1");
    Person secondNotCelebrityPerson = buildPerson("Not A Celebrity #2");

    assignKnowPersonToPerson(firstNotCelebrityPerson, secondNotCelebrityPerson);
    assignKnowPersonToPerson(secondNotCelebrityPerson, firstNotCelebrityPerson);

    List<Person> persons = Arrays.asList(firstNotCelebrityPerson, secondNotCelebrityPerson);

    Optional<Person> actualFoundCelebrity = celebrityServiceImpl.findCelebrity(persons);

    assertFalse(actualFoundCelebrity.isPresent());
  }

  @Test
  @DisplayName(value = "'findCelebrity' when there is a bunch of not Celebrity persons.")
  void findCelebrityWhenThereIsABunchOfNotCelebrityPersons() {
    Person firstNotCelebrityPerson = buildPerson("Not A Celebrity #1");
    Person secondNotCelebrityPerson = buildPerson("Not A Celebrity #2");
    Person thirdNotCelebrityPerson = buildPerson("Not A Celebrity #3");
    Person fourthNotCelebrityPerson = buildPerson("Not A Celebrity #4");

    assignKnowPersonToPerson(firstNotCelebrityPerson, secondNotCelebrityPerson);
    assignKnowPersonToPerson(secondNotCelebrityPerson, thirdNotCelebrityPerson);
    assignKnowPersonToPerson(thirdNotCelebrityPerson, fourthNotCelebrityPerson);
    assignKnowPersonToPerson(fourthNotCelebrityPerson, firstNotCelebrityPerson);

    List<Person> persons = Arrays.asList(firstNotCelebrityPerson, secondNotCelebrityPerson, thirdNotCelebrityPerson,
        fourthNotCelebrityPerson);

    Optional<Person> actualFoundCelebrity = celebrityServiceImpl.findCelebrity(persons);

    assertFalse(actualFoundCelebrity.isPresent());
  }

  @Test
  @DisplayName(value = "'findCelebrity' when there is a Celebrity person.")
  void findCelebrityWhenThereIsACelebrityPerson() {
    Person firstNotCelebrityPerson = buildPerson("Not A Celebrity #1");
    Person secondNotCelebrityPerson = buildPerson("Not A Celebrity #2");

    Person expectedCelebrityPerson = buildPerson("A Celebrity");
    assignCelebrityToPersons(expectedCelebrityPerson, Arrays.asList(firstNotCelebrityPerson, secondNotCelebrityPerson));

    List<Person> persons = Arrays.asList(firstNotCelebrityPerson, expectedCelebrityPerson, secondNotCelebrityPerson);

    Optional<Person> actualFoundCelebrity = celebrityServiceImpl.findCelebrity(persons);

    assertTrue(actualFoundCelebrity.isPresent());
    assertEquals(expectedCelebrityPerson, actualFoundCelebrity.get());
  }

  @Test
  @DisplayName(value = "'findCelebrity' when there is just one person (a Celebrity person).")
  void findCelebrityWhenThereIsJustOneCelebrityPersons() {
    Person expectedCelebrityPerson = buildPerson("A Celebrity #1");

    List<Person> persons = Collections.singletonList(expectedCelebrityPerson);

    Optional<Person> actualFoundCelebrity = celebrityServiceImpl.findCelebrity(persons);

    assertTrue(actualFoundCelebrity.isPresent());
    assertEquals(expectedCelebrityPerson, actualFoundCelebrity.get());
  }

  @Test
  @DisplayName(value = "'findCelebrity' when there are two Celebrity persons.")
  void findCelebrityWhenThereAreTwoCelebrityPersons() {
    Person firstCelebrityPerson = buildPerson("A Celebrity #1");
    Person secondCelebrityPerson = buildPerson("A Celebrity #2");

    List<Person> persons = Arrays.asList(firstCelebrityPerson, secondCelebrityPerson);

    Optional<Person> actualFoundCelebrity = celebrityServiceImpl.findCelebrity(persons);

    assertFalse(actualFoundCelebrity.isPresent());
  }

  private Person buildPerson(String name) {
    return new Person(name);
  }

  private void assignCelebrityToPersons(Person celebrityPerson, List<Person> persons) {
    persons.forEach(person -> assignKnowPersonToPerson(person, celebrityPerson));
  }

  private void assignKnowPersonToPerson(Person person, Person knowPerson) {
    person.getKnownPersons().add(knowPerson);
  }
}
