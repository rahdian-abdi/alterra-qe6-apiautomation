package Reqres.StepDefinitions;

import Reqres.API.PostCreateUserApi;
import io.cucumber.java.en.And;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.When;
import io.restassured.module.jsv.JsonSchemaValidator;
import net.serenitybdd.rest.SerenityRest;
import net.thucydides.core.annotations.Steps;
import java.io.File;

public class LoginUserStepDefinition {

    @Steps
    PostCreateUserApi post;

    // LOGIN
    @Given("Login user with valid body request")
    public void loginUserWithValidBodyRequest() {
        File json = new File(PostCreateUserApi.JSON_FILE+"/BodyRequest/PostLoginUser.json");
        post.postCreateNewUser(json);
    }

    @When("Send request login")
    public void sendRequestLogin() {
        SerenityRest.when().post(PostCreateUserApi.POST_LOGIN);
    }
    @And("Login user successful json schema")
    public void loginUserSuccessfulJsonSchema() {
        File json = new File(PostCreateUserApi.JSON_FILE+"/SchemaValidator/PostLoginUserSuccessfulJsonSchema.json");
        SerenityRest.then().assertThat().body(JsonSchemaValidator.matchesJsonSchema(json));
    }

    // LOGIN UNSUCCESSFUL

    @Given("Login user with invalid body request")
    public void loginUserWithInvalidBodyRequest() {
        File json = new File(PostCreateUserApi.JSON_FILE+"/BodyRequest/PostLoginUnsuccessful.json");
        post.postCreateNewUser(json);
    }
    @And("Login user unsuccessful json schema")
    public void loginUserUnsuccessfulJsonSchema() {
        File json = new File(PostCreateUserApi.JSON_FILE+"/SchemaValidator/PostLoginUserUnsuccessfulJsonSchema.json");
        SerenityRest.then().assertThat().body(JsonSchemaValidator.matchesJsonSchema(json));
    }
}
