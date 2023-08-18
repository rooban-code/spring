package net.rooban.springkeycloak;

import lombok.Data;

@Data
public class FooDto {
    private long id;
    private String name;

    public FooDto() {
    }

    public FooDto(long id, String name) {
        this.id = id;
        this.name = name;
    }

}
