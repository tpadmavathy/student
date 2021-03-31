package com.padma.edu.student;


import com.padma.edu.exception.CustomException;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.time.LocalDateTime;


@RestController
public class DemoController {

    @RequestMapping("/welcome")
    public String greeting() {
        LocalDateTime now = LocalDateTime.now();
        //int hour = now.getHour();
        int hour = 26;
        try {
            if (hour < 1 || hour > 24)
            {
                throw new CustomException("Invalid Hour");
            }

        }
        catch(Exception e)
        {
            System.out.println("Exception caught : "+e);
        }
        if (hour < 12)
            return "Good Morning";
        else if (hour >= 12 && hour < 16)
            return "Good Afternoon";
        else
            return "Good Evening";
    }

}
