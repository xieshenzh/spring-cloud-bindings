/*
 * Copyright 2019 the original author or authors.
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *      http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package org.springframework.cloud.cnb.boot;

import java.util.Map;

import org.springframework.cloud.cnb.core.CnbBinding;
import org.springframework.cloud.cnb.jdbc.JdbcBinding;


public class DataSourceCnbBindingProcessor implements CnbBindingProcessor {
    @Override
    public boolean accept(CnbBinding binding) {
        return JdbcBinding.isJDCBBinding(binding);
    }

    @Override
    public void process(CnbBinding binding, Map<String, Object> properties) {
        JdbcBinding jdbcBinding = new JdbcBinding(binding);
        properties.put("spring.datasource.url", jdbcBinding.getJdbcUrl());
        properties.put("spring.datasource.username", jdbcBinding.getUsername());
        properties.put("spring.datasource.password", jdbcBinding.getPassword());
        properties.put("spring.datasource.driver-class-name", jdbcBinding.getDriverClassName());
    }

    @Override
    public CnbBindingProcessorProperties getProperties() {
        return CnbBindingProcessorProperties.builder()
                .propertyPrefixes("spring.datasource")
                .build();
    }
}