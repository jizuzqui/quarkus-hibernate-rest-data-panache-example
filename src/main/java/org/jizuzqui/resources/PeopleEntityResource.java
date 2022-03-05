package org.jizuzqui.resources;

import java.util.List;

import org.jizuzqui.entities.Person;

import io.quarkus.hibernate.orm.rest.data.panache.PanacheEntityResource;
import io.quarkus.panache.common.Page;
import io.quarkus.panache.common.Sort;
import io.quarkus.rest.data.panache.MethodProperties;

public interface PeopleEntityResource extends PanacheEntityResource<Person, Long> {
    @MethodProperties(path = "all")
    List<Person> list(Page page, Sort sort);

    @MethodProperties(exposed = false)
    boolean delete(Long id);
}