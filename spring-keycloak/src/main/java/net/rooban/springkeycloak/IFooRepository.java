package net.rooban.springkeycloak;

import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface IFooRepository extends PagingAndSortingRepository<Foo, Long> {
    Iterable<Foo> findAll();
    Foo save(Foo foo);
    Optional<Foo> findById(Long id);
}
