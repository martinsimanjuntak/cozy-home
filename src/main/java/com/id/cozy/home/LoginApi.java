package com.id.cozy.home;

import com.id.cozy.home.annotation.CheckAccess;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author martin
 * Date 07/02/26
 */
//
@RestController
@RequestMapping("cozy-home")
public class LoginApi {

    @GetMapping
    @CheckAccess
    public String getInstance() {
        return "Success";
    }
}
