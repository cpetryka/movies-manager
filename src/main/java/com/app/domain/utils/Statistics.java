package com.app.domain.utils;

import java.math.BigDecimal;

public record Statistics<T>(T min, T max, BigDecimal avg) {}

