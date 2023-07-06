package by.logonuk.controller.response;

import lombok.Data;
import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;

import java.sql.Timestamp;

@Data
public class DealResponse {

    private Integer id;

    private Integer userId;

    private Integer productId;

    private Integer quantity;

    private Double price;

    private Timestamp creationDate;

    private Timestamp modificationDate;

    private Boolean isDeleted;

    @Override
    public String toString() {
        return ToStringBuilder.reflectionToString(this, ToStringStyle.JSON_STYLE);
    }
}
