package com.andrade.shorten_url.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotEmpty;

public record UrlRequest(@NotBlank @NotEmpty String url) {

}
