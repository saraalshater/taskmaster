package com.amplifyframework.datastore.generated.model;

import com.amplifyframework.core.model.temporal.Temporal;

import java.util.List;
import java.util.UUID;
import java.util.Objects;

import androidx.core.util.ObjectsCompat;

import com.amplifyframework.core.model.Model;
import com.amplifyframework.core.model.annotations.Index;
import com.amplifyframework.core.model.annotations.ModelConfig;
import com.amplifyframework.core.model.annotations.ModelField;
import com.amplifyframework.core.model.query.predicate.QueryField;

import static com.amplifyframework.core.model.query.predicate.QueryField.field;

/** This is an auto generated class representing the TaskClass type in your schema. */
@SuppressWarnings("all")
@ModelConfig(pluralName = "TaskClasses")
@Index(name = "byTeam", fields = {"teamID","title"})
public final class TaskClass implements Model {
  public static final QueryField ID = field("TaskClass", "id");
  public static final QueryField TEAM_ID = field("TaskClass", "teamID");
  public static final QueryField TITLE = field("TaskClass", "title");
  public static final QueryField BODY = field("TaskClass", "body");
  public static final QueryField STATUS = field("TaskClass", "status");
  private final @ModelField(targetType="ID", isRequired = true) String id;
  private final @ModelField(targetType="ID", isRequired = true) String teamID;
  private final @ModelField(targetType="String", isRequired = true) String title;
  private final @ModelField(targetType="String") String body;
  private final @ModelField(targetType="String") String status;
  private @ModelField(targetType="AWSDateTime", isReadOnly = true) Temporal.DateTime createdAt;
  private @ModelField(targetType="AWSDateTime", isReadOnly = true) Temporal.DateTime updatedAt;
  public String getId() {
      return id;
  }
  
  public String getTeamId() {
      return teamID;
  }
  
  public String getTitle() {
      return title;
  }
  
  public String getBody() {
      return body;
  }
  
  public String getStatus() {
      return status;
  }
  
  public Temporal.DateTime getCreatedAt() {
      return createdAt;
  }
  
  public Temporal.DateTime getUpdatedAt() {
      return updatedAt;
  }
  
  private TaskClass(String id, String teamID, String title, String body, String status) {
    this.id = id;
    this.teamID = teamID;
    this.title = title;
    this.body = body;
    this.status = status;
  }
  
  @Override
   public boolean equals(Object obj) {
      if (this == obj) {
        return true;
      } else if(obj == null || getClass() != obj.getClass()) {
        return false;
      } else {
      TaskClass taskClass = (TaskClass) obj;
      return ObjectsCompat.equals(getId(), taskClass.getId()) &&
              ObjectsCompat.equals(getTeamId(), taskClass.getTeamId()) &&
              ObjectsCompat.equals(getTitle(), taskClass.getTitle()) &&
              ObjectsCompat.equals(getBody(), taskClass.getBody()) &&
              ObjectsCompat.equals(getStatus(), taskClass.getStatus()) &&
              ObjectsCompat.equals(getCreatedAt(), taskClass.getCreatedAt()) &&
              ObjectsCompat.equals(getUpdatedAt(), taskClass.getUpdatedAt());
      }
  }
  
  @Override
   public int hashCode() {
    return new StringBuilder()
      .append(getId())
      .append(getTeamId())
      .append(getTitle())
      .append(getBody())
      .append(getStatus())
      .append(getCreatedAt())
      .append(getUpdatedAt())
      .toString()
      .hashCode();
  }
  
  @Override
   public String toString() {
    return new StringBuilder()
      .append("TaskClass {")
      .append("id=" + String.valueOf(getId()) + ", ")
      .append("teamID=" + String.valueOf(getTeamId()) + ", ")
      .append("title=" + String.valueOf(getTitle()) + ", ")
      .append("body=" + String.valueOf(getBody()) + ", ")
      .append("status=" + String.valueOf(getStatus()) + ", ")
      .append("createdAt=" + String.valueOf(getCreatedAt()) + ", ")
      .append("updatedAt=" + String.valueOf(getUpdatedAt()))
      .append("}")
      .toString();
  }
  
  public static TeamIdStep builder() {
      return new Builder();
  }
  
  /** 
   * WARNING: This method should not be used to build an instance of this object for a CREATE mutation.
   * This is a convenience method to return an instance of the object with only its ID populated
   * to be used in the context of a parameter in a delete mutation or referencing a foreign key
   * in a relationship.
   * @param id the id of the existing item this instance will represent
   * @return an instance of this model with only ID populated
   */
  public static TaskClass justId(String id) {
    return new TaskClass(
      id,
      null,
      null,
      null,
      null
    );
  }
  
  public CopyOfBuilder copyOfBuilder() {
    return new CopyOfBuilder(id,
      teamID,
      title,
      body,
      status);
  }
  public interface TeamIdStep {
    TitleStep teamId(String teamId);
  }
  

  public interface TitleStep {
    BuildStep title(String title);
  }
  

  public interface BuildStep {
    TaskClass build();
    BuildStep id(String id);
    BuildStep body(String body);
    BuildStep status(String status);
  }
  

  public static class Builder implements TeamIdStep, TitleStep, BuildStep {
    private String id;
    private String teamID;
    private String title;
    private String body;
    private String status;
    @Override
     public TaskClass build() {
        String id = this.id != null ? this.id : UUID.randomUUID().toString();
        
        return new TaskClass(
          id,
          teamID,
          title,
          body,
          status);
    }
    
    @Override
     public TitleStep teamId(String teamId) {
        Objects.requireNonNull(teamId);
        this.teamID = teamId;
        return this;
    }
    
    @Override
     public BuildStep title(String title) {
        Objects.requireNonNull(title);
        this.title = title;
        return this;
    }
    
    @Override
     public BuildStep body(String body) {
        this.body = body;
        return this;
    }
    
    @Override
     public BuildStep status(String status) {
        this.status = status;
        return this;
    }
    
    /** 
     * @param id id
     * @return Current Builder instance, for fluent method chaining
     */
    public BuildStep id(String id) {
        this.id = id;
        return this;
    }
  }
  

  public final class CopyOfBuilder extends Builder {
    private CopyOfBuilder(String id, String teamId, String title, String body, String status) {
      super.id(id);
      super.teamId(teamId)
        .title(title)
        .body(body)
        .status(status);
    }
    
    @Override
     public CopyOfBuilder teamId(String teamId) {
      return (CopyOfBuilder) super.teamId(teamId);
    }
    
    @Override
     public CopyOfBuilder title(String title) {
      return (CopyOfBuilder) super.title(title);
    }
    
    @Override
     public CopyOfBuilder body(String body) {
      return (CopyOfBuilder) super.body(body);
    }
    
    @Override
     public CopyOfBuilder status(String status) {
      return (CopyOfBuilder) super.status(status);
    }
  }
  
}
