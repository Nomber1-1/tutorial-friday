package ca.mcgill.ecse321.eventregistration.model;

import java.io.Serializable;
import java.util.Objects;

import jakarta.persistence.Embeddable;
import jakarta.persistence.EmbeddedId;
import jakarta.persistence.Entity;
import jakarta.persistence.ManyToOne;

@Entity
public class Registration {
    @EmbeddedId
    private Key key;

    // Hibernate needs a default constructor, but it doesn't need to be public
    @SuppressWarnings("unused")
    private Registration() {
    }

    public Registration(Key key) {
        this.key = key;
    }

    public Key getKey() {
        return key;
    }

    public void setKey(Key key) {
        this.key = key;
    }

    // See
    // https://docs.jboss.org/hibernate/orm/6.4/userguide/html_single/Hibernate_User_Guide.html#identifiers-composite
    // for the rules that the key class must abide by
    @Embeddable
    public static class Key implements Serializable {
        @ManyToOne
        private Person person;
        @ManyToOne
        private Event event;

        // This constructor needs to be public, according to the docs
        public Key() {
        }

        public Key(Person person, Event event) {
            this.person = person;
            this.event = event;
        }

        public Person getPerson() {
            return person;
        }

        public Event getEvent() {
            return event;
        }

        public void setPerson(Person person) {
            this.person = person;
        }

        public void setEvent(Event event) {
            this.event = event;
        }

        @Override
        public boolean equals(Object obj) {
            if (!(obj instanceof Key)) {
                return false;
            }
            Key other = (Key) obj;
            return this.getPerson().getId() == other.getPerson().getId()
                    && this.getEvent().getId() == other.getEvent().getId();
        }

        @Override
        public int hashCode() {
            return Objects.hash(this.getPerson().getId(), this.getEvent().getId());
        }
    }
}
