package com.project.task.university.controller.filter;

import java.util.Collection;
import java.util.List;
import java.util.Map;

public interface Filter<T> {

    Map<Boolean, List<T>> splitFilter(List<T> list);

    List<T> filter(List<T> list);
}
