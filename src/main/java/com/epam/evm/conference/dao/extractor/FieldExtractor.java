package com.epam.evm.conference.dao.extractor;

import java.util.Map;

public interface FieldExtractor<T> {

    Map<String, Object> extract(T entity);

}
