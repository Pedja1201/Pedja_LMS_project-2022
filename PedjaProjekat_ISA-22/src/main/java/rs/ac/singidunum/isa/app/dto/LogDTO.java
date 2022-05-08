package rs.ac.singidunum.isa.app.dto;

import java.time.LocalDateTime;

public class LogDTO {
    private String type;
    private String content;
    private LocalDateTime dateTime;

    public LogDTO() {super();
    }

    public LogDTO(String type, String content, LocalDateTime dateTime) {
        this.type = type;
        this.content = content;
        this.dateTime = dateTime;
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

    public LocalDateTime getDateTime() {
        return dateTime;
    }

    public void setDateTime(LocalDateTime dateTime) {
        this.dateTime = dateTime;
    }
}
