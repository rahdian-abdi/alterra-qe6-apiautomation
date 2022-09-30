package Reqres.API;

import net.serenitybdd.rest.SerenityRest;
import net.thucydides.core.annotations.Step;

public class DeleteApi {
    public static String URL = "https://reqres.in";
    public static String DELETE_USER = URL+"/api/users/{id}";
    public static String DELETE_USER_INVALID_PATH = URL+"/api/{path}/{id}";

    @Step("Delete user")
    public void deleteUser(int id){
        SerenityRest.given().pathParam("id", id);
    }

    @Step("Delete user with invalid path")
    public void deleteUserInvalidPath(int id, String path){
        SerenityRest.given().pathParam("path", path);
        SerenityRest.given().pathParam("id", id);
    }
}
