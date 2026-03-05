package com.id.cozy.home.service;


import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.id.cozy.home.entity.FieldMapping;
import com.id.cozy.home.repository.FieldMappingRepository;
import lombok.RequiredArgsConstructor;
import lombok.Setter;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.Month;
import java.time.format.DateTimeFormatter;
import java.util.Date;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.List;


@Service
@RequiredArgsConstructor
@Slf4j
public class LmpFieldMappingUtil {
    private final FieldMappingRepository fieldMappingRepository;


    public List<FieldMapping> getFieldMappingList(String setId, String fieldKey, String fieldCat) {
        return fieldMappingRepository.findAllByFieldKeyAndSetIdAndFieldCat(fieldKey, setId, fieldCat);
    }

    public String mapSourceToTargetField(HashMap<String, String> sourceFields, String setId, String fieldKey)
            throws JsonProcessingException {
        List<FieldMapping> fieldSettings = getFieldMappingList(setId, fieldKey, "I");
        ObjectMapper objectMapper = new ObjectMapper();
        HashMap<String, Object> targetFields = new HashMap<>();

        for (FieldMapping fieldSetting : fieldSettings) {
            if (fieldSetting.getTargetFieldNm() != null && !fieldSetting.getTargetFieldNm().isEmpty()) {
                String fieldValue = "";
                if (sourceFields.containsKey(fieldSetting.getSourceFieldNm())) {
                    if (sourceFields.get(fieldSetting.getSourceFieldNm()) != null) {
                        fieldValue = convertObjectToString(sourceFields.get(fieldSetting.getSourceFieldNm()),
                                fieldSetting.getSourceFieldType());
                    }
                } else if (fieldSetting.getDefaultValue() != null && !fieldSetting.getDefaultValue().isEmpty()) {
                    fieldValue = fieldSetting.getDefaultValue();
                }
                targetFields.put(fieldSetting.getTargetFieldNm(), convertTypeOfAString(fieldValue,
                        fieldSetting.getTargetFieldType()));
            }
        }
        log.info("target fields: {}", objectMapper.writeValueAsString(targetFields));
        return objectMapper.writeValueAsString(targetFields);
    }


    public Object convertTypeOfAString(String fieldValue, String targetFieldType) {
        if (BigDecimal.class.getName().contains(targetFieldType)) {
            return new BigDecimal(fieldValue);
        } else if (LocalDateTime.class.getName().contains(targetFieldType)) {
            return toLocalDateTime(fieldValue, "yyyy-MM-dd");
        } else {
            return fieldValue;
        }
    }


    public String convertObjectToString(Object fieldValue, String fromType) {
        if (BigDecimal.class.getName().contains(fromType)) {
            return new BigDecimal(fieldValue.toString()).setScale(2, BigDecimal.ROUND_HALF_UP).toString();
        } else if (LocalDateTime.class.getName().contains(fromType)) {
            LocalDateTime dateTime = null;
            try {
                LinkedHashMap<?, ?> realFieldVal = (LinkedHashMap<?, ?>) fieldValue;
                dateTime = LocalDateTime.of(
                        (Integer) realFieldVal.get("year"),
                        Month.of((Integer) realFieldVal.get("monthValue")),
                        (Integer) realFieldVal.get("dayOfMonth"),
                        (Integer) realFieldVal.get("hour"),
                        (Integer) realFieldVal.get("minute")
                );
            } catch (ClassCastException e) {
                // Local Date Time Map is Not Linked List Try Cast 2024-11-29T00:00:00
                throw new ClassCastException(fieldValue.toString());
            }

            return toStringFromDate(dateTime, "yyyy-MM-dd HH:mm:ss");
        } else if (Integer.class.getName().contains(fromType)) {
            return fieldValue.toString();
        } else if (Boolean.class.getName().contains(fromType)) {
            return String.valueOf(fieldValue);
        } else {
            return fieldValue.toString();
        }
    }
//
//    public static String mapTargetToSource(HashMap<String, String> targetFields, String setId, String fieldKey)
//            throws JsonProcessingException {
//
//        List<LmpFieldMappingResponseDto> fieldSettings = LmpFieldMappingUtil.getFieldMappingList(
//                setId, fieldKey, LmpConstants.FIELD_CATEGORY_OUTPUT);
//        ObjectMapper objectMapper = new ObjectMapper();
//        HashMap<String, Object> sourceFields = new HashMap<>();
//
//        for (LmpFieldMappingResponseDto fieldSetting: fieldSettings) {
//            if (fieldSetting.getSourceFieldName() != null && !fieldSetting.getSourceFieldName().isEmpty()) {
//                String fieldValue = LmpConstants.EMPTY_STRING;
//                if (targetFields.containsKey(fieldSetting.getTargetFieldName())) {
//                    if (targetFields.get(fieldSetting.getTargetFieldName()) != null) {
//                        fieldValue = convertObjectToString(targetFields.get(fieldSetting.getTargetFieldName()),
//                                fieldSetting.getTargetFieldType());
//                    }
//                }
//                else if (fieldSetting.getDefaultValue() != null && !fieldSetting.getDefaultValue().isEmpty()) {
//                    fieldValue = fieldSetting.getDefaultValue();
//                }
//
//                sourceFields.put(fieldSetting.getSourceFieldName(), convertTypeOfAString(fieldValue,
//                        fieldSetting.getSourceFieldType()));
//            }
//        }
//        return objectMapper.writeValueAsString(sourceFields);
//    }

    public String toStringFromDate(LocalDateTime dateTime, String pattern) {
        if (dateTime == null) {
            return null;
        }
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern(pattern);
        return dateTime.format(formatter);
    }

    // =========================
    // LocalDate → String
    // =========================
    public String toString(LocalDate date, String pattern) {
        if (date == null) {
            return null;
        }
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern(pattern);
        return date.format(formatter);
    }

    // =========================
    // java.util.Date → String
    // =========================
    public String toString(Date date, String pattern) {
        if (date == null) {
            return null;
        }
        SimpleDateFormat sdf = new SimpleDateFormat(pattern);
        return sdf.format(date);
    }

    public LocalDateTime toLocalDateTime(String dateTimeStr, String pattern) {
        if (dateTimeStr == null || dateTimeStr.trim().isEmpty()) {
            return null;
        }

        DateTimeFormatter formatter = DateTimeFormatter.ofPattern(pattern);
        return LocalDateTime.parse(dateTimeStr, formatter);
    }

}
