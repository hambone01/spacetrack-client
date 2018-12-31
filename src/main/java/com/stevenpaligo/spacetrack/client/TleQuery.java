/*
 * The author licenses this file to You under the Apache License, Version 2.0
 * (the "License"); you may not use this file except in compliance with
 * the License. You may obtain a copy of the License at
 *
 * http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package com.stevenpaligo.spacetrack.client;

import java.time.Instant;
import java.time.temporal.ChronoUnit;
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
import com.stevenpaligo.spacetrack.client.TleQuery.Tle;
import com.stevenpaligo.spacetrack.client.TleQuery.TleQueryField;
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

public class TleQuery extends Query<TleQueryField, Tle> {

  @Builder
  public TleQuery(@NonNull CredentialProvider credentials, @NonNull @Singular Collection<@NonNull Predicate<@NonNull TleQueryField>> predicates, Limit limit, @NonNull @Singular List<@NonNull Sort<TleQueryField>> sorts,
      @NonNull @Singular Set<@NonNull String> favorites) {

    super(Tle.class, credentials, "tle", CollectionUtils.emptyIfNull(predicates), Optional.ofNullable(limit), ListUtils.emptyIfNull(sorts), SetUtils.emptyIfNull(favorites));
  }


  public static enum TleQueryField implements QueryField {

    COMMENT {

      @Override
      public String getQueryFieldName() {
        return "COMMENT";
      }
    },

    ORIGINATOR {

      @Override
      public String getQueryFieldName() {
        return "ORIGINATOR";
      }
    },

    CATALOG_NUMBER {

      @Override
      public String getQueryFieldName() {
        return "NORAD_CAT_ID";
      }
    },

    OBJECT_NAME {

      @Override
      public String getQueryFieldName() {
        return "OBJECT_NAME";
      }
    },

    OBJECT_TYPE {

      @Override
      public String getQueryFieldName() {
        return "OBJECT_TYPE";
      }
    },

    CLASSIFICATION {

      @Override
      public String getQueryFieldName() {
        return "CLASSIFICATION_TYPE";
      }
    },

    INTERNATIONAL_DESIGNATOR {

      @Override
      public String getQueryFieldName() {
        return "INTLDES";
      }
    },

    EPOCH_YMD_HMS {

      @Override
      public String getQueryFieldName() {
        return "EPOCH";
      }
    },

    EPOCH_MICROSECONDS {

      @Override
      public String getQueryFieldName() {
        return "EPOCH_MICROSECONDS";
      }
    },

    MEAN_MOTION_REVS_PER_DAY {

      @Override
      public String getQueryFieldName() {
        return "MEAN_MOTION";
      }
    },

    ECCENTRICITY {

      @Override
      public String getQueryFieldName() {
        return "ECCENTRICITY";
      }
    },

    INCLINATION_DEGREES {

      @Override
      public String getQueryFieldName() {
        return "INCLINATION";
      }
    },

    RIGHT_ASC_OF_NODE_DEGREES {

      @Override
      public String getQueryFieldName() {
        return "RA_OF_ASC_NODE";
      }
    },

    ARG_OF_PERIGEE_DEGREES {

      @Override
      public String getQueryFieldName() {
        return "ARG_OF_PERICENTER";
      }
    },

    MEAN_ANOMALY_DEGREES {

      @Override
      public String getQueryFieldName() {
        return "MEAN_ANOMALY";
      }
    },

    EPHEMERIS_TYPE {

      @Override
      public String getQueryFieldName() {
        return "EPHEMERIS_TYPE";
      }
    },

    ELEMENT_SET_NUMBER {

      @Override
      public String getQueryFieldName() {
        return "ELEMENT_SET_NO";
      }
    },

    REV_NUMBER {

      @Override
      public String getQueryFieldName() {
        return "REV_AT_EPOCH";
      }
    },

    BSTAR {

      @Override
      public String getQueryFieldName() {
        return "BSTAR";
      }
    },

    MEAN_MOTION_DOT {

      @Override
      public String getQueryFieldName() {
        return "MEAN_MOTION_DOT";
      }
    },

    MEAN_MOTION_DOUBLE_DOT {

      @Override
      public String getQueryFieldName() {
        return "MEAN_MOTION_DDOT";
      }
    },

    FILE_NUMBER {

      @Override
      public String getQueryFieldName() {
        return "FILE";
      }
    },

    TLE_LINE0 {

      @Override
      public String getQueryFieldName() {
        return "TLE_LINE0";
      }
    },

    TLE_LINE1 {

      @Override
      public String getQueryFieldName() {
        return "TLE_LINE1";
      }
    },

    TLE_LINE2 {

      @Override
      public String getQueryFieldName() {
        return "TLE_LINE2";
      }
    },

    OBJECT_ID {

      @Override
      public String getQueryFieldName() {
        return "OBJECT_ID";
      }
    },

    OBJECT_NUMBER {

      @Override
      public String getQueryFieldName() {
        return "OBJECT_NUMBER";
      }
    },

    SEMI_MAJOR_AXIS_KILOMETERS {

      @Override
      public String getQueryFieldName() {
        return "SEMIMAJOR_AXIS";
      }
    },

    PERIOD_MINUTES {

      @Override
      public String getQueryFieldName() {
        return "PERIOD";
      }
    },

    APOGEE_HEIGHT_KILOMETERS {

      @Override
      public String getQueryFieldName() {
        return "APOGEE";
      }
    },

    PERIGEE_HEIGHT_KILOMETERS {

      @Override
      public String getQueryFieldName() {
        return "PERIGEE";
      }
    }
  }


  @Getter
  @Setter
  @NoArgsConstructor
  @JsonInclude(value = Include.NON_NULL)
  public static class Tle {

    @JsonProperty("COMMENT")
    private String comment;

    @JsonProperty("ORIGINATOR")
    private String originator;

    @JsonProperty("NORAD_CAT_ID")
    private Integer catalogNumber;

    @JsonProperty("OBJECT_NAME")
    private String objectName;

    @JsonProperty("OBJECT_TYPE")
    private Optional<String> objectType;

    @JsonProperty("CLASSIFICATION_TYPE")
    private String classification;

    @JsonProperty("INTLDES")
    private Optional<String> internationalDesignator;

    @JsonProperty("EPOCH")
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss", timezone = "UTC")
    private Instant epochYmdHms;

    @JsonProperty("EPOCH_MICROSECONDS")
    private Integer epochMicroseconds;

    @JsonProperty("MEAN_MOTION")
    private Double meanMotionRevsPerDay;

    @JsonProperty("ECCENTRICITY")
    private Double eccentricity;

    @JsonProperty("INCLINATION")
    private Double inclinationDegrees;

    @JsonProperty("RA_OF_ASC_NODE")
    private Double rightAscOfNodeDegrees;

    @JsonProperty("ARG_OF_PERICENTER")
    private Double argOfPerigeeDegrees;

    @JsonProperty("MEAN_ANOMALY")
    private Double meanAnomalyDegrees;

    @JsonProperty("EPHEMERIS_TYPE")
    private Integer ephemerisType;

    @JsonProperty("ELEMENT_SET_NO")
    private Integer elementSetNumber;

    @JsonProperty("REV_AT_EPOCH")
    private Float revNumber;

    @JsonProperty("BSTAR")
    private Double bstar;

    @JsonProperty("MEAN_MOTION_DOT")
    private Double meanMotionDot;

    @JsonProperty("MEAN_MOTION_DDOT")
    private Double meanMotionDoubleDot;

    @JsonProperty("FILE")
    private Integer fileNumber;

    @JsonProperty("TLE_LINE0")
    private String tleLine0;

    @JsonProperty("TLE_LINE1")
    private String tleLine1;

    @JsonProperty("TLE_LINE2")
    private String tleLine2;

    @JsonProperty("OBJECT_ID")
    private Optional<String> objectId;

    @JsonProperty("OBJECT_NUMBER")
    private Integer objectNumber;

    @JsonProperty("SEMIMAJOR_AXIS")
    private Double semiMajorAxisKilometers;

    @JsonProperty("PERIOD")
    private Optional<Double> periodMinutes;

    @JsonProperty("APOGEE")
    private Double apogeeHeightKilometers;

    @JsonProperty("PERIGEE")
    private Double perigeeHeightKilometers;


    public Instant getEpoch() {
      return getEpochYmdHms().plus(getEpochMicroseconds(), ChronoUnit.MICROS);
    }
  }
}
