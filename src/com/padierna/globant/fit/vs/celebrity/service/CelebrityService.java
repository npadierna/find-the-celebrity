package com.padierna.globant.fit.vs.celebrity.service;

import com.padierna.globant.fit.vs.celebrity.model.Person;
import java.util.List;
import java.util.Optional;

public interface CelebrityService {

  Optional<Person> findCelebrity(List<Person> persons);
}
