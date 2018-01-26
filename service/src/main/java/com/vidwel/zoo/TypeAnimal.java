package com.vidwel.zoo;

/**
builder of animal type
        TypeAnimal newType = TypeAnimal.makeBuilder().
                name("Cat").
                maxAge(40).
                location("Africa").
                photo("").
                comment("").
                build();
*/

public class TypeAnimal {
    private String name;
    private int maxAge;
    private String location;
    private String photo;
    private String comment;

    private TypeAnimal() {
    }

    public String getName() {
        return name;
    }

    public int getMaxAge() {
        return maxAge;
    }

    public String getLocation() {
        return location;
    }

    public String getPhoto() {
        return photo;
    }

    public String getComment() {
        return comment;
    }

    public static Builder makeBuilder() {
        TypeAnimal animal = new TypeAnimal();
        Builder builder = animal.new Builder();
        return builder;
        //or: return new TypeAnimal().new Builder();
    }

    public class Builder {
        private Builder() {
        }

        public Builder name(String name) {
            TypeAnimal.this.name = name;
            return this;
        }

        public Builder maxAge(int maxAge) {
            TypeAnimal.this.maxAge = maxAge;
            return this;
        }

        public Builder location(String location) {
            TypeAnimal.this.location = location;
            return this;
        }

        public Builder photo(String photo) {
            TypeAnimal.this.photo = photo;
            return this;
        }

        public Builder comment(String comment) {
            TypeAnimal.this.comment = comment;
            return this;
        }

        public TypeAnimal build() {
            return TypeAnimal.this;
        }
    }
}
