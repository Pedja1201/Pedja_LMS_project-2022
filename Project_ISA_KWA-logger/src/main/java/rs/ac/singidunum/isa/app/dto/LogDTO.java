package rs.ac.singidunum.isa.app.dto;

import java.io.Serial;
import java.io.Serializable;
import java.time.LocalDateTime;

public class LogDTO implements Serializable {
    @Serial
    private static final long serialVersionUID = 3540248038100916891L;
    private String type;
    private String content;
    private LocalDateTime datetime;

    public LogDTO() {super();
    }

    public LogDTO(String type, String content, LocalDateTime datetime) {
        this.type = type;
        this.content = content;
        this.datetime = datetime;
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
