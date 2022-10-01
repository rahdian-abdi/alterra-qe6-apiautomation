package Reqres.StepDefinitions;

import Reqres.API.PutUpdateUserApi;
import Reqres.ReqresResponses;
import io.cucumber.java.en.And;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.When;
import io.restassured.module.jsv.JsonSchemaValidator;
import net.serenitybdd.rest.SerenityRest;
import net.thucydides.core.annotations.Steps;
import static org.hamcrest.Matchers.equalTo;

import java.io.File;

public class PutUserStepDefinition {
    @Steps
    PutUpdateUserApi put;
    @Given("Put update user with id {int}")
    public void put_update_user_with_id(int id) {
        File json = new File(PutUpdateUserApi.JSON_FILE+"/BodyRequest/PutUser.json");
        put.putUpdateUser(id,json);
    }
    @When("Send request with put update user")
    public void send_request_with_put_update_user() {
        SerenityRest.when().put(PutUpdateUserApi.PUT_UPDATE_USER);
    }

    @And("Should return name {string} and job {string}")
    public void shouldReturnNameAndJob(String name, String job) {
        SerenityRest.then()
                .body(ReqresResponses.NAME, equalTo(name))
                .body(ReqresResponses.JOB, equalTo(job));
    }
    @And("Put update user json schema validator")
    public void putUpdateUserJsonSchemaValidator() {
        File json = new File(PutUpdateUserApi.JSON_FILE+"/SchemaValidator/PutUserJsonSchemaValidator.json");
        SerenityRest.then().assertThat()
                .body(JsonSchemaValidator.matchesJsonSchema(json));
    }

    //14. UPDATE USER WITH UNREGISTERED USER
    @And("Put update user with unregistered id json schema")
    public void putUpdateUserWithUnregisteredIdJsonSchema() {
        File json = new File(PutUpdateUserApi.JSON_FILE+"/SchemaValidator/PutUpdateUserUnregisteredUserJsonSchema.json");
        SerenityRest.then().assertThat().body(JsonSchemaValidator.matchesJsonSchema(json));
    }

    //15. UPDATE USER WITH EMPTY BODY REQUEST
    @Given("Put update user with id {int} and empty json")
    public void putUpdateUserWithIdIdAndEmptyJson(int id) {
        File json = new File(PutUpdateUserApi.JSON_FILE+"/BodyRequest/PutEmptyUser.json");
        put.putUpdateUser(id, json);
    }

    @And("Put update user with empty body json schema")
    public void putUpdateUserWithEmptyBodyJsonSchema() {
        File json = new File(PutUpdateUserApi.JSON_FILE+"/SchemaValidator/PutUpdateUserEmptyBodyRequestJsonSchema.json");
        SerenityRest.then().assertThat().body(JsonSchemaValidator.matchesJsonSchema(json));
    }

    //16. UPDATE USER WITH INVALID PARAM
    @Given("Put update user with id {string} and valid body request")
    public void putUpdateUserWithIdAndValidBodyRequest(String id) {
        File json = new File(PutUpdateUserApi.JSON_FILE+"/BodyRequest/PutUser.json");
        put.putUpdateUserInvalidParam(id, json);
    }

    //17. UPDATE USER WITH INVALID BODY REQUEST
    @Given("Put update user with id {int} and invalid body request")
    public void putUpdateUserWithIdIdAndInvalidBodyRequest(int id) {
        File json = new File(PutUpdateUserApi.JSON_FILE+"/BodyRequest/PutInvalidBodyReq.json");
        put.putUpdateUser(id, json);
    }
    @And("Put update user with invalid body request json schema")
    public void putUpdateUserWithInvalidBodyRequestJsonSchema() {
        File json = new File(PutUpdateUserApi.JSON_FILE+"/SchemaValidator/PutUpdateUserInvalidBodyRequestJsonSchema.json");
        SerenityRest.then().assertThat().body(JsonSchemaValidator.matchesJsonSchema(json));
    }
}
