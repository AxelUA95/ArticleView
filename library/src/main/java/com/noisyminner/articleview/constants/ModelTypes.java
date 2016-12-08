package com.noisyminner.articleview.constants;

import java.util.Arrays;
import java.util.List;

/**
 * created by Alex Ivanov on 11/11/16.
 */

public interface ModelTypes {
    int TEXT = 0;
    int IMAGE = 1;
    int VIDEO = 2;

    int USER_TYPES = 100;

    List<Integer> DEFAULT_TYPES = Arrays.asList(TEXT, IMAGE, VIDEO);
}
