package com.padierna.globant.fit.vs.celebrity.service;

import static java.util.Objects.isNull;

import com.padierna.globant.fit.vs.celebrity.model.Person;
import java.util.List;
import java.util.Optional;

public class CelebrityServiceImpl implements CelebrityService {

  @Override
  public Optional<Person> findCelebrity(List<Person> persons) {
    return isInvalidPersons(persons) ? Optional.empty() : processFindingCelebrities(persons);
  }

  private boolean isInvalidPersons(List<Person> persons) {
    return isNull(persons) || persons.isEmpty();
  }

  private Optional<Person> processFindingCelebrities(List<Person> persons) {
    return persons.stream().filter(person -> isACelebrityPerson(person, persons)).findAny();
  }

  private boolean isACelebrityPerson(Person possibleCelebrityPerson, List<Person> persons) {
    return isPersonKnownByOthersPersons(possibleCelebrityPerson, persons)
        && isPersonKnowingAnybody(possibleCelebrityPerson);
  }

  private boolean isPersonKnownByOthersPersons(Person person, List<Person> othersPersons) {
    return othersPersons.stream().filter(anotherPerson -> !person.equals(anotherPerson))
        .map(Person::getKnownPersons)
        .allMatch(knownPersons -> knownPersons.contains(person));
  }

  private boolean isPersonKnowingAnybody(Person person) {
    return person.getKnownPersons().isEmpty();
  }
}
