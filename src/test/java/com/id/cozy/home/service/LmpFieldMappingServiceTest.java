package com.id.cozy.home.service;

import org.junit.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import static org.junit.Assert.assertNotNull;


/**
 * @author martin
 * Date 28/02/26
 */

@SpringBootTest
public class LmpFieldMappingUtilTest {

    @Autowired
    LmpFieldMappingUtil mappingService;

    @Test
    public void testSomething() {
        assertNotNull(mappingService);
    }

//    @Test
//    public void test() throws JsonProcessingException {
//        HashMap<String, String> sourceFields = new HashMap<>();
//        sourceFields.put("debitAccountNo", "123456");
//
//        FieldMapping mapping = new FieldMapping();
//        mapping.setSourceFieldNm("debitAccountNo");
//        mapping.setTargetFieldNm("TLBF01");
//        mapping.setSourceFieldType("String");
//        mapping.setTargetFieldType("String");
//
//        // ====== WHEN ======
//        String result = mappingService.mapSourceToTargetField(
//                sourceFields,
//                "1304",
//                "1304"
//        );
//
//        // ====== THEN ======
//        assertTrue(result.contains("TLBF01"));
//        assertTrue(result.contains("123456"));
//    }

}