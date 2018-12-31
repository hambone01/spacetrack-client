package com.stevenpaligo.spacetrack.client;

import static org.junit.jupiter.api.Assertions.assertDoesNotThrow;
import static org.junit.jupiter.api.Assertions.assertThrows;
import java.net.URL;
import java.util.Collections;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import com.stevenpaligo.spacetrack.client.TipMessageQuery.TipMessage;
import com.stevenpaligo.spacetrack.client.TipMessageQuery.TipMessageQueryField;
import com.stevenpaligo.spacetrack.client.credential.CredentialProvider;
import com.stevenpaligo.spacetrack.client.credential.DefaultCredentialProvider;

public class TipMessageQueryTests {

  private static final String SPACETRACK_USER_NAME_PROPERTY = "spacetrack.user.name";
  private static final String SPACETRACK_PASSWORD_PROPERTY = "spacetrack.password";


  private static CredentialProvider credentials;


  @BeforeAll
  protected static void init() throws Exception {

    // verify the SpaceTrack credentials are available as system properties
    if (System.getProperty(SPACETRACK_USER_NAME_PROPERTY) == null) {
      throw new Exception("The SpaceTrack user name is missing from the system properties (" + SPACETRACK_USER_NAME_PROPERTY + ")");
    } else if (System.getProperty(SPACETRACK_PASSWORD_PROPERTY) == null) {
      throw new Exception("The SpaceTrack password is missing from the system properties (" + SPACETRACK_PASSWORD_PROPERTY + ")");
    }


    // save the SpaceTrack credentials
    credentials = new DefaultCredentialProvider(System.getProperty(SPACETRACK_USER_NAME_PROPERTY), System.getProperty(SPACETRACK_PASSWORD_PROPERTY));
  }


  @Test
  @DisplayName("TipMessageQuery: Result type matches the SpaceTrack schema")
  public void test1() {

    assertDoesNotThrow(() -> {
      ResultTypeValidator.validate(TipMessage.class, new URL("https://www.space-track.org/basicspacedata/modeldef/class/tip/format/json"));
    });
  }


  @Test
  @DisplayName("TipMessageQuery: Query field enum matches the result type")
  public void test2() {

    assertDoesNotThrow(() -> {
      QueryFieldEnumValidator.validate(TipMessageQueryField.class, TipMessage.class);
    });
  }


  @Test
  @DisplayName("TipMessageQuery: Builder method parameter validation")
  public void test3() {

    // a call to set the credentials is required
    assertThrows(IllegalArgumentException.class, () -> {
      TipMessageQuery.builder().build();
    });


    // the call to set the credentials will not accept a null
    assertThrows(IllegalArgumentException.class, () -> {
      TipMessageQuery.builder().credentials(null).build();
    });


    // none of the following calls are required: favorite(...), favorites(...), limit(...), predicate(...), predicates(...), sort(...), sorts(...)
    assertDoesNotThrow(() -> {
      TipMessageQuery.builder().credentials(credentials).build();
    });


    // the call to favorite(...) will not accept a null
    assertThrows(IllegalArgumentException.class, () -> {
      TipMessageQuery.builder().credentials(credentials).favorite(null).build();
    });


    // the call to favorites(...) will not accept a null
    assertThrows(NullPointerException.class, () -> { // TODO: change to IllegalArgumentException if/when https://github.com/rzwitserloot/lombok/issues/1999 is worked
      TipMessageQuery.builder().credentials(credentials).favorites(null).build();
    });


    // the call to favorites(...) will accept an empty collection
    assertDoesNotThrow(() -> {
      TipMessageQuery.builder().credentials(credentials).favorites(Collections.emptyList()).build();
    });


    // the call to limit(...) will accept a null
    assertDoesNotThrow(() -> {
      TipMessageQuery.builder().credentials(credentials).limit(null).build();
    });


    // TODO: this won't pass until Lombok is fixed
    // the call to predicate(...) will not accept a null
    // assertThrows(IllegalArgumentException.class, () -> {
    // TipMessageQuery.builder().credentials(credentials).predicate(null).build();
    // });


    // the call to predicates(...) will not accept a null
    assertThrows(NullPointerException.class, () -> { // TODO: change to IllegalArgumentException if/when https://github.com/rzwitserloot/lombok/issues/1999 is worked
      TipMessageQuery.builder().credentials(credentials).predicates(null).build();
    });


    // the call to predicates(...) will accept an empty collection
    assertDoesNotThrow(() -> {
      TipMessageQuery.builder().credentials(credentials).predicates(Collections.emptyList()).build();
    });


    // TODO: this won't pass until Lombok is fixed
    // the call to sort(...) will not accept a null
    // assertThrows(IllegalArgumentException.class, () -> {
    // TipMessageQuery.builder().credentials(credentials).sort(null).build();
    // });


    // the call to sorts(...) will not accept a null
    assertThrows(NullPointerException.class, () -> { // TODO: change to IllegalArgumentException if/when https://github.com/rzwitserloot/lombok/issues/1999 is worked
      TipMessageQuery.builder().credentials(credentials).sorts(null).build();
    });


    // the call to sorts(...) will accept an empty collection
    assertDoesNotThrow(() -> {
      TipMessageQuery.builder().credentials(credentials).sorts(Collections.emptyList()).build();
    });
  }
}
