package com.rothur.UserDemo.exception;

import lombok.*;

import java.time.Instant;

@AllArgsConstructor
@NoArgsConstructor
@Setter
@Getter
@ToString
public class ApplicationException {

    private Instant timestamp;

    private int status;

    private String message;

}
