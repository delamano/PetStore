// 1 - Pacote
package petstore;

// 2 - Biblioteca


import org.testng.annotations.Test;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

import static io.restassured.RestAssured.given;
import static org.hamcrest.CoreMatchers.is;

// 3 - Classes
public class Pet {

    // 3.1 - Atributos - (qualidade - não retorna nada)
    String uri ="https://petstore.swagger.io/v2/pet"; //endereço da entidade Pet

    // 3.2 - Métodos e Funções (retornam)
    public String lerJson(String caminhoJson) throws IOException {
        return new String(Files.readAllBytes(Paths.get(caminhoJson)));
    }

    //Inlcuir - Create - Post
    @Test //Identifica método ou função como um teste para o TestNG
    public void incluirPet() throws IOException {
        String jsonBody = lerJson("db/pet1.json");

        //Sintaxe Gherkin
        //Dado - Quando Então
        //Given - When - Then

        given() //Dado
                .contentType("application/json") //comum em API REST - antigas era "text/xml"
                .log().all()
                .body(jsonBody)
        .when() //Quando
                .post(uri)
        .then() //Então
                .log().all()
                .statusCode(200)
                .body("name", is ("kira"))
                .body("status", is("available"))
                .body("category.name", is("dog"))
                .body("tag.id", is(2021))
        ;

    }

}

