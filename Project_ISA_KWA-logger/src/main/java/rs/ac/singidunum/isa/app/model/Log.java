package rs.ac.singidunum.isa.app.model;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.time.LocalDateTime;

@Document
public class Log {
    @Id
    private String id;
    private String type;
    private String content;
    private LocalDateTime datetime;

    public Log() {super();
    }

    public Log(String id, String type, String content, LocalDateTime datetime) {
        this.id = id;
        this.type = type;
        this.content = content;
        this.datetime = datetime;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public LocalDateTime getDatetime() {
        return datetime;
    }

    public void setDatetime(LocalDateTime datetime) {
        this.datetime = datetime;
    }
}
