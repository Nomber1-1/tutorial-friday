package ca.mcgill.ecse321.eventregistration.integration;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.time.LocalDate;

import org.junit.jupiter.api.MethodOrderer;
import org.junit.jupiter.api.Order;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestInstance;
import org.junit.jupiter.api.TestInstance.Lifecycle;
import org.junit.jupiter.api.TestMethodOrder;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.context.SpringBootTest.WebEnvironment;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import ca.mcgill.ecse321.eventregistration.dto.ErrorDto;
import ca.mcgill.ecse321.eventregistration.dto.PersonRequestDto;
import ca.mcgill.ecse321.eventregistration.dto.PersonResponseDto;

@SpringBootTest(webEnvironment = WebEnvironment.RANDOM_PORT)
@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
@TestInstance(Lifecycle.PER_CLASS)
public class PersonIntegrationTests {
    @Autowired
    private TestRestTemplate client;

    private final String VALID_NAME = "Alice";
    private final String VALID_EMAIL = "alice@mail.mcgill.ca";
    private final String VALID_PASSWORD = "password123";
    private final String INVALID_PASSWORD = "123";
    private final int INVALID_ID = 0;
    private int validId;

    @Test
    @Order(1)
    public void testCreateValidPerson() {
        // Set up
        PersonRequestDto request = new PersonRequestDto(VALID_NAME, VALID_EMAIL, VALID_PASSWORD);

        // Act
        ResponseEntity<PersonResponseDto> response = client.postForEntity("/people", request, PersonResponseDto.class);

        // Assert
        assertNotNull(response);
        assertEquals(HttpStatus.CREATED, response.getStatusCode());
        PersonResponseDto createdPerson = response.getBody();
        assertNotNull(createdPerson);
        assertEquals(VALID_NAME, createdPerson.getName());
        assertEquals(VALID_EMAIL, createdPerson.getEmail());
        assertNotNull(createdPerson.getId());
        assertTrue(createdPerson.getId() > 0, "Response should have a positive ID.");
        assertEquals(LocalDate.now(), createdPerson.getCreationDate());

        this.validId = createdPerson.getId();
    }

    @Test
    @Order(2)
    public void testReadPersonByValidId() {
        // Set up
        String url = "/people/" + this.validId;

        // Act
        ResponseEntity<PersonResponseDto> response = client.getForEntity(url, PersonResponseDto.class);

        // Assert
        assertNotNull(response);
        assertEquals(HttpStatus.OK, response.getStatusCode());
        PersonResponseDto person = response.getBody();
        assertNotNull(person);
        assertEquals(VALID_NAME, person.getName());
        assertEquals(VALID_EMAIL, person.getEmail());
        assertEquals(this.validId, person.getId());
        assertEquals(LocalDate.now(), person.getCreationDate());
    }

    @Test
    @Order(3)
    public void testCreateInvalidPerson() {
        // Set up
        PersonRequestDto request = new PersonRequestDto(VALID_NAME, VALID_EMAIL, INVALID_PASSWORD);

        // Act
        ResponseEntity<ErrorDto> response = client.postForEntity("/people", request, ErrorDto.class);

        // Assert
        assertNotNull(response);
        assertEquals(HttpStatus.BAD_REQUEST, response.getStatusCode());
        ErrorDto body = response.getBody();
        assertNotNull(body);
        assertEquals(1, body.getErrors().size());
        assertEquals("Password too short.", body.getErrors().get(0));
    }

    @Test
    @Order(4)
    public void testReadPersonByInvalidId() {
        // Set up
        String url = "/people/" + this.INVALID_ID;

        // Act
        ResponseEntity<ErrorDto> response = client.getForEntity(url, ErrorDto.class);

        // Assert
        assertNotNull(response);
        assertEquals(HttpStatus.NOT_FOUND, response.getStatusCode());
        ErrorDto body = response.getBody();
        assertNotNull(body);
        assertEquals(1, body.getErrors().size());
        assertEquals("There is no person with ID " + this.INVALID_ID + ".", body.getErrors().get(0));
    }
}
