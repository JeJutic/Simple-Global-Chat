package com.jejutic.web_service.ui.data_objects;

import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.Data;

@Data
public class UserInfo {

    @NotNull
    @Size(min=1, max=20)
    private String nickname;
}
