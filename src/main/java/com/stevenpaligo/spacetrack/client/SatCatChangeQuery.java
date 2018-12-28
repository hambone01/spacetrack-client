package com.stevenpaligo.spacetrack.client;

import java.io.IOException;
import java.time.Instant;
import java.time.LocalDate;
import java.util.Collection;
import java.util.List;
import java.util.Optional;
import java.util.Set;
import org.apache.commons.collections4.CollectionUtils;
import org.apache.commons.collections4.ListUtils;
import org.apache.commons.collections4.SetUtils;
import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonInclude.Include;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.core.JsonParseException;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.stevenpaligo.spacetrack.client.credential.CredentialProvider;
import com.stevenpaligo.spacetrack.client.predicate.Predicate;
import com.stevenpaligo.spacetrack.client.query.Limit;
import com.stevenpaligo.spacetrack.client.query.QueryField;
import com.stevenpaligo.spacetrack.client.query.Sort;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.NonNull;
import lombok.Setter;
import lombok.Singular;

@Builder
public class SatCatChangeQuery {

  @NonNull
  private CredentialProvider credentials;

  @Singular
  private Collection<Predicate<SatCatChangeQueryField>> predicates;

  private Limit limit;

  @Singular
  private List<Sort<SatCatChangeQueryField>> sorts;

  @Singular
  private Set<String> favorites;


  public List<SatCatChange> execute() throws JsonParseException, JsonMappingException, IOException {

    // create a query
    Query<SatCatChangeQueryField, SatCatChange> query = new Query<>(SatCatChange.class, credentials, "satcat_change", CollectionUtils.emptyIfNull(predicates), Optional.ofNullable(limit), ListUtils.emptyIfNull(sorts), SetUtils.emptyIfNull(favorites));


    // execute the query and return the results
    return query.execute();
  }


  public static enum SatCatChangeQueryField implements QueryField {

    CATALOG_NUMBER {

      @Override
      public String getQueryFieldName() {
        return "NORAD_CAT_ID";
      }
    },

    OBJECT_NUMBER {

      @Override
      public String getQueryFieldName() {
        return "OBJECT_NUMBER";
      }
    },

    CURRENT_NAME {

      @Override
      public String getQueryFieldName() {
        return "CURRENT_NAME";
      }
    },

    PREVIOUS_NAME {

      @Override
      public String getQueryFieldName() {
        return "PREVIOUS_NAME";
      }
    },

    CURRENT_INTERNATIONAL_DESIGNATOR {

      @Override
      public String getQueryFieldName() {
        return "CURRENT_INTLDES";
      }
    },

    PREVIOUS_INTERNATIONAL_DESIGNATOR {

      @Override
      public String getQueryFieldName() {
        return "PREVIOUS_INTLDES";
      }
    },

    CURRENT_COUNTRY {

      @Override
      public String getQueryFieldName() {
        return "CURRENT_COUNTRY";
      }
    },

    PREVIOUS_COUNTRY {

      @Override
      public String getQueryFieldName() {
        return "PREVIOUS_COUNTRY";
      }
    },

    CURRENT_LAUNCH_DATE {

      @Override
      public String getQueryFieldName() {
        return "CURRENT_LAUNCH";
      }
    },

    PREVIOUS_LAUNCH_DATE {

      @Override
      public String getQueryFieldName() {
        return "PREVIOUS_LAUNCH";
      }
    },

    CURRENT_DECAY_DATE {

      @Override
      public String getQueryFieldName() {
        return "CURRENT_DECAY";
      }
    },

    PREVIOUS_DECAY_DATE {

      @Override
      public String getQueryFieldName() {
        return "PREVIOUS_DECAY";
      }
    },

    UPDATE_TIME {

      @Override
      public String getQueryFieldName() {
        return "CHANGE_MADE";
      }
    }
  }


  @Getter
  @Setter
  @NoArgsConstructor
  @JsonInclude(value = Include.NON_NULL)
  public static class SatCatChange {

    @JsonProperty("NORAD_CAT_ID")
    private Integer catalogNumber;

    @JsonProperty("OBJECT_NUMBER")
    private Integer objectNumber;

    @JsonProperty("CURRENT_NAME")
    private String currentName;

    @JsonProperty("PREVIOUS_NAME")
    private Optional<String> previousName;

    @JsonProperty("CURRENT_INTLDES")
    private String currentInternationalDesignator;

    @JsonProperty("PREVIOUS_INTLDES")
    private Optional<String> previousInternationalDesignator;

    @JsonProperty("CURRENT_COUNTRY")
    private String currentCountry;

    @JsonProperty("PREVIOUS_COUNTRY")
    private Optional<String> previousCountry;

    @JsonProperty("CURRENT_LAUNCH")
    @JsonFormat(pattern = "yyyy-MM-dd", timezone = "UTC")
    private Optional<LocalDate> currentLaunchDate;

    @JsonProperty("PREVIOUS_LAUNCH")
    @JsonFormat(pattern = "yyyy-MM-dd", timezone = "UTC")
    private Optional<LocalDate> previousLaunchDate;

    @JsonProperty("CURRENT_DECAY")
    @JsonFormat(pattern = "yyyy-MM-dd", timezone = "UTC")
    private Optional<LocalDate> currentDecayDate;

    @JsonProperty("PREVIOUS_DECAY")
    @JsonFormat(pattern = "yyyy-MM-dd", timezone = "UTC")
    private Optional<LocalDate> previousDecayDate;

    @JsonProperty("CHANGE_MADE")
    private Optional<Instant> updateTime;

  }
}
