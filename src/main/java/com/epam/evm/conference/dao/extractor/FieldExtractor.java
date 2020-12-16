package com.epam.evm.conference.dao.extractor;

import java.util.Map;

public interface FieldExtractor<T> {

    Map<Integer, Object> extractForSave(T entity);

    Map<Integer, Object> extractForUpdate(T entity);
}
