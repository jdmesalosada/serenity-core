package net.serenitybdd.screenplay.rest.abiities;

import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;
import net.serenitybdd.rest.SerenityRest;
import net.serenitybdd.screenplay.Ability;
import net.serenitybdd.screenplay.Actor;

import java.util.function.Function;

/**
 * A Screenplay ability that allows an actor to perform REST queries against a specified API.
 * For example:
 * ```
 * Actor sam = Actor.named("Sam the supervisor").whoCan(CallAnApi.at("https://reqres.in"));
 * ```
 */
public class CallAnApi implements Ability {

    private final String baseURL;

    private CallAnApi(String baseURL) {
        this.baseURL = baseURL;
    }

    /**
     * Ability to Call and api at a specified baseUrl
     */
    public static CallAnApi at(String baseURL) {
        return new CallAnApi(baseURL);
    }

    /**
     * Used to access the Actor's ability to CallAnApi from within the Interaction classes, such as GET or PUT
     */
    public static CallAnApi as(Actor actor) {
        return actor.abilityTo(CallAnApi.class);
    }

    public String resolve(String resource) {
        return baseURL + resource;
    }

    public Response getLastResponse() {
        return SerenityRest.lastResponse();
    }
}
