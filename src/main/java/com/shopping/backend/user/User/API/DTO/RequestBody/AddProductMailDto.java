package com.shopping.backend.user.User.API.DTO.RequestBody;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class AddProductMailDto {
    String mailId;
    String mailMessage;
    String subjectLine;
    String userName;
}
