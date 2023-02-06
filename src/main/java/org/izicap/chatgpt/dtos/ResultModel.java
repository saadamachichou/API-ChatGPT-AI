package org.izicap.chatgpt.dtos;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class ResultModel<T> {
    private Integer code;
    private String message;
    private T data;

    public ResultModel() {
    }

    public static <T> ResultModel<T> success(T data){
        return new ResultModel<>(200,"success",data);
    }

    public static <T> ResultModel<T> fail(T data){
        return new ResultModel<>(0,"fail",data);
    }

}
