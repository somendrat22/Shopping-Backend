package com.shoppingwebsite.mail.Mail.Service.dto.request;

import lombok.*;
import org.springframework.stereotype.Service;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@ToString
public class AddProductDto {
    String mailId;
    String mailMessage;
    String subjectLine;
    String userName;
}
