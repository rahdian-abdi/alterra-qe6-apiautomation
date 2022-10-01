package Reqres.StepDefinitions;

import Reqres.API.DeleteUserApi;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import net.serenitybdd.rest.SerenityRest;
import net.thucydides.core.annotations.Steps;

public class DeleteUserStepDefinition {
    @Steps
    DeleteUserApi delete;
    @Given("Delete user with valid {int}")
    public void delete_user_with_valid(Integer id) {
       delete.deleteUser(id);
    }
    @When("Send request delete user")
    public void send_request_delete_user() {
        SerenityRest.when().delete(DeleteUserApi.DELETE_USER);
    }
    @Then("Should return {int} No Content")
    public void should_return_no_content(Integer status) {
       SerenityRest.then().statusCode(status);
    }

    // INVALID PATH
    @Given("Delete user with valid {int} and set path {string}")
    public void deleteUserWithValidIdAndSetPath(int id, String path) {
        delete.deleteUserInvalidPath(id, path);
    }
}
