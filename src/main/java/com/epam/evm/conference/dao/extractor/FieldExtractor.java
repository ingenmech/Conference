package com.epam.evm.conference.dao.extractor;

import java.util.Map;

/**
 * The {@code FieldExtractor} interface represents method signature
 * for extract fields from entity for work with database.
 *
 * @param <T>
 * @version 1.0
 */
public interface FieldExtractor<T> {

    Map<String, Object> extract(T entity);

}
