package com.app.utils;

import java.math.BigDecimal;

public record Statistics<T>(T min, T max, BigDecimal avg) {}

