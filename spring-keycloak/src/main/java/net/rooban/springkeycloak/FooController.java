package net.rooban.springkeycloak;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.http.HttpStatus;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

@RestController
@RequestMapping(value = "/api/foos")
public class FooController {

    @Qualifier("fooServiceImpl")
    @Autowired
    private IFooService fooService;

    @CrossOrigin(origins = "http://localhost:8081")
    @GetMapping(value = "/{id}")
    @PreAuthorize("hasAuthority('SCOPE_read')")
    public FooDto findOne(@PathVariable Long id) {
        Foo entity = fooService.findById(id)
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND));
        return convertToDto(entity);
    }

    @GetMapping("/all")
    public Collection<FooDto> findAll() {
        Iterable<Foo> foos = this.fooService.findAll();
        List<FooDto> fooDtos = new ArrayList<>();
        foos.forEach(p -> fooDtos.add(convertToDto(p)));
        return fooDtos;
    }

    @PreAuthorize("hasAuthority('SCOPE_write')")
    @PostMapping("/add")
    public FooDto add(@RequestBody FooDto fooDto){
        return convertToDto(this.fooService.save(returnFoo(fooDto)));
    }

    protected FooDto convertToDto(Foo entity) {
        FooDto dto = new FooDto(entity.getId(), entity.getName());
        return dto;
    }

    protected Foo returnFoo(FooDto fooDto){
        Foo foo = new Foo();
        foo.setName(fooDto.getName());
        return foo;
    }
}
