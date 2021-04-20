import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import io.restassured.RestAssured;
import io.restassured.http.ContentType;
import org.hamcrest.CoreMatchers;
import org.junit.BeforeClass;
import org.junit.Test;

public class ApiTest {

    @BeforeClass
    public static void setup(){
        RestAssured.baseURI = "http://localhost:8001/tasks-backend";
    }

    @Test
    public void deveTrazerTodasAsTarefas(){

        RestAssured
                .given()
                    .log().all()
                .when()
                    .get("/todo")
                .then()
                    .log().all()
                    .statusCode(200);
    }

    @Test
    public void deveAdicionarTarefaComFalhaTarefaNula() throws JsonProcessingException {

        Task task = new Task();
        task.setTask(null);
        task.setDueDate("2021-04-25");

        RestAssured
                .given()
                    .body(new ObjectMapper().writeValueAsString(task))
                    .contentType(ContentType.JSON)
                .when()
                    .post("/todo")
                .then()
                    .log().all()
                    .statusCode(400)
                    .body("message", CoreMatchers.is("Fill the task description"));
    }

    @Test
    public void deveAdicionarTarefaComSucesso() throws JsonProcessingException {

        Task task = new Task();
        task.setTask("Tarefa 3");
        task.setDueDate("2021-04-25");

        RestAssured
                .given()
                    .body(new ObjectMapper().writeValueAsString(task))
                    .contentType(ContentType.JSON)
                .when()
                    .post("/todo")
                .then()
                    .log().all()
                .statusCode(201);
    }

    @Test
    public void deveAdicionarTarefaComFalhaDataNula() throws JsonProcessingException {

        Task task = new Task();
        task.setTask("Tarefa 3");

        RestAssured
                .given()
                    .body(new ObjectMapper().writeValueAsString(task))
                    .contentType(ContentType.JSON)
                .when()
                    .post("/todo")
                .then()
                    .log().all()
                    .statusCode(400)
                    .body("message", CoreMatchers.is("Fill the due date"));
    }

    @Test
    public void deveAdicionarTarefaComFalhaDataPassada() throws JsonProcessingException {

        Task task = new Task();
        task.setTask("Tarefa 3");
        task.setDueDate("2020-04-25");


        RestAssured
                .given()
                    .body(new ObjectMapper().writeValueAsString(task))
                    .contentType(ContentType.JSON)
                .when()
                    .post("/todo")
                .then()
                    .log().all()
                    .statusCode(400)
                    .body("message", CoreMatchers.is("Due date must not be in past"));
    }
}

